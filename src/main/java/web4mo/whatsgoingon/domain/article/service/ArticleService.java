package web4mo.whatsgoingon.domain.article.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.NaverApi.NaverApi;
import web4mo.whatsgoingon.domain.category.repository.UserCategoryKeywordRepository;
import web4mo.whatsgoingon.config.NaverApi.ArticleApiDto;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;
import web4mo.whatsgoingon.domain.user.service.UserService;

import java.time.*;

import java.time.format.*;
import java.util.List;
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
        List<ArticleApiDto> apiArticles = naverApi.naverApiResearch(query, count, sort);

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

    private Article convertToEntity(ArticleApiDto article) {

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
        LocalDate pubDate = convertStringToLocalDate(article.getPubDate());
        String img = convertURL(article.getLink(), pubDate);
        return Article.builder()
                .title(article.getTitle())
                .url(article.getLink())
                .img(img)
                //.keyword(findByMember(Member))
                .pubDate(pubDate)
                .build();
    }

    public static LocalDate convertStringToLocalDate(String dateTimeString) {
        // Define the formatter according to the input string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);

        // Parse the string to a ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString, formatter);

        // Convert the ZonedDateTime to LocalDate
        LocalDate localDate = zonedDateTime.toLocalDate();

        return localDate;
    }

    public static String convertURL(String inputUrl, LocalDate localDate) {
        // Extract media and articleId from the input URL
        String[] parts = inputUrl.split("/");
        String media = parts[parts.length - 2];
        String articleIdWithZeroes = parts[parts.length - 1].split("\\?")[0];

        // Remove leading zeros from articleId
        String articleId = articleIdWithZeroes.replaceFirst("^0+(?!$)", "");

        // Get date information
        String year = String.valueOf(localDate.getYear());
        String month = String.format("%02d", localDate.getMonthValue());
        String day = String.format("%02d", localDate.getDayOfMonth());

        // Format the output URL
        String outputUrl = String.format(
                "https://mimgnews.pstatic.net/image/origin/%s/%s/%s/%s/%s.jpg?type=nf220_150",
                media, year, month, day, articleId
        );

        return outputUrl;
    }

}