package web4mo.whatsgoingon.domain.article.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.NaverApi.NaverApi;
import web4mo.whatsgoingon.domain.category.entity.UserCategoryKeyword;
import web4mo.whatsgoingon.domain.category.repository.MediaUserRepository;
import web4mo.whatsgoingon.domain.category.repository.UserCategoryKeywordRepository;
import web4mo.whatsgoingon.config.NaverApi.articleApiDto;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;
import web4mo.whatsgoingon.domain.user.entity.Member;
import web4mo.whatsgoingon.domain.user.service.UserService;
import web4mo.whatsgoingon.response.Response;

import java.time.*;

import java.io.IOException;
import java.time.format.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    private final UserService userService;
    private final UserCategoryKeywordRepository userCategoryKeywordRepository;

    @Autowired
    private NaverApi naverApi; // 수정 - 의존성

    public List<ArticleDto> getArticles(String query, int count, String sort) {
        List<articleApiDto> apiArticles = naverApi.naverApiResearch(query, count, sort);

        // API 데이터를 엔티티로 변환
        List<Article> articleEntities = apiArticles.stream()
                .map(this::convertToEntity)
                .filter(article -> article.getUrl().startsWith("https://n.news.naver.com"))
                .collect(Collectors.toList());

        articleRepository.saveAll(articleEntities);

        // DTO로 변환
        return articleEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private static final DateTimeFormatter API_DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");

    private ArticleDto convertToDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .url(article.getUrl())
                .img(article.getImg())
                .pubDate(article.getPubDate())
                .crawling(article.isCrawling())
                //.category(article.getKeyword())
                .build();
    }

    private Article convertToEntity(articleApiDto article) {
        LocalDate pubDate = LocalDate.parse("2024-08-02");
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(article.getPubDate(), API_DATE_FORMATTER);
            pubDate = zonedDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            e.getStackTrace();
        }
        
//        String link = article.getLink();
//        Pattern pattern = Pattern.compile("https://n\\.news\\.naver\\.com/mnews/article/(\\d{3})/(\\d{10})\\?sid=104");
//        Matcher matcher = pattern.matcher(link);
//
//        String pressCode = matcher.group(1);
//        String articleId = matcher.group(2);
//
//        // articleId를 int로 변환 후 String으로 변환
//        int DigitsInt = Integer.parseInt(articleId);
//        String formattedDigits = Integer.toString(DigitsInt);
//
//        link = "https://mimgnews.pstatic.net/image/origin/"+pressCode+"/2024/08/02/"+formattedDigits+".jpg?type=nf220_150";

        return Article.builder()
                .title(article.getTitle())
                .url(article.getLink())
                .img(article.getLink())
                //.keyword(findByMember(Member))
                .pubDate(pubDate)
                .build();
    }
}