package web4mo.whatsgoingon.domain.article.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.scrap.service.ScrapService;
import web4mo.whatsgoingon.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@RestController
@RequiredArgsConstructor
@Tag(name = "Article Controller")
@RequestMapping(value="/article")
public class ContentController {
    private final ScrapService scrapService;

    @GetMapping("/content")
    @ResponseStatus(OK)
    @Transactional // 메인 페이지에서 기사 눌렀을때
    public Response scrapMain(@RequestParam(value = "articleId") Long articleId) {
        String articleContent = scrapService.scrapMain(articleId);
        return success(CONTENT_MAIN, articleContent);
    }
}
