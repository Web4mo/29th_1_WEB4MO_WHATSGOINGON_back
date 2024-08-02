package web4mo.whatsgoingon.domain.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.service.ArticleService;
import web4mo.whatsgoingon.response.Response;

import java.io.IOException;
import java.util.List;

import static web4mo.whatsgoingon.response.Response.success;

@Tag(name = "Article Controller", description = "기사 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping(value = "/article")
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "기사를 가져옵니다.")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Response getArticles(@RequestParam(required = false) String keyword,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String sort) {
        try {
            articleService.saveArticles(keyword, page, sort);
            List<ArticleDto> articles = articleService.getArticles(keyword, page, sort);

            return success("기사를 성공적으로 가져왔습니다.", articles);
        } catch (Exception e) {
            log.error("기사 가져오기에 실패했습니다.", e);
            return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR, "기사 가져오기에 실패했습니다.");
        }
    }

    @Operation(summary = "기사 전문을 띄웁니다.")
    @GetMapping("/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getArticleContent(@PathVariable Long id) {
        try {
            String content = articleService.fetchArticleContent(id);
            return success("기사 전문을 성공적으로 가져왔습니다.", content);
        } catch (IOException e) {
            log.error("기사 전문 가져오기에 실패했습니다.", e);
            return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch article content");
        } catch (Exception e) {
            log.error("Error", e);
            return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
        }
    }
}
