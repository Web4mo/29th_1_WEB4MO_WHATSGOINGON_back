package web4mo.whatsgoingon.domain.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.CrawlingService;
import web4mo.whatsgoingon.config.NaverApi.NaverApi;
import web4mo.whatsgoingon.config.NaverApi.articleApiDto;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;

import java.time.*;

import java.io.IOException;
import java.time.format.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private NaverApi naverApi; // 수정 - 의존성

    @Autowired
    private CrawlingService crawlingService;

    public List<ArticleDto> getArticles(String query, int count, String sort) {
        List<articleApiDto> apiArticles = naverApi.naverApiResearch(query, count, sort);

        // API 데이터를 엔티티로 변환
        List<Article> articleEntities = apiArticles.stream()
                .map(this::convertToEntity)
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
                //.keyword(article.getKeyword())
                .build();
    }

    private Article convertToEntity(articleApiDto article) {
        LocalDate pubDate = null;
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(article.getPubDate(), API_DATE_FORMATTER);
            pubDate = zonedDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            e.getStackTrace();
        }

        return Article.builder()
                .title(article.getTitle())
                .url(article.getOriginallink())
                .img(article.getLink())
                .pubDate(pubDate)
                .build();
    }

    public String fetchArticleContent(Long articleId) throws IOException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + articleId));
        return crawlingService.fetchArticleContent(article.getUrl());
    }
}