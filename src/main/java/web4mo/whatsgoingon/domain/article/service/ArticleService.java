package web4mo.whatsgoingon.domain.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.CrawlingService;
import web4mo.whatsgoingon.config.NaverApi.NaverApi;
import web4mo.whatsgoingon.config.NaverApi.articleApiDto;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;

import java.io.IOException;
import java.time.LocalDate;
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
        List<articleApiDto> articles = naverApi.naverApiResearch(query, count, sort);
        return articles.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void saveArticles(String query, int count, String sort) {
        List<articleApiDto> articles = naverApi.naverApiResearch(query, count, sort);
        List<Article> articleEntities = articles.stream().map(this::convertToEntity).collect(Collectors.toList());
        articleRepository.saveAll(articleEntities);
    }

    private ArticleDto convertToDto(articleApiDto article) {
        return ArticleDto.builder()
                .title(article.getTitle())
                .url(article.getOriginallink())
                .img(article.getLink())
                .pubDate(LocalDate.parse(article.getPubDate()))
                .build();
    }

    private Article convertToEntity(articleApiDto article) {
        return Article.builder()
                .title(article.getTitle())
                .url(article.getOriginallink())
                .img(article.getLink())
                .pubDate(LocalDate.parse(article.getPubDate().substring(0, 10))) // 문자열에서 날짜 부분만 추출
                .build();
    }

    public String fetchArticleContent(Long articleId) throws IOException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + articleId));
        return crawlingService.fetchArticleContent(article.getUrl());
    }
}
