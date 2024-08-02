package web4mo.whatsgoingon.domain.article.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.article.service.ArticleScrapService;
import web4mo.whatsgoingon.response.Response;

import static web4mo.whatsgoingon.response.Message.SCRAPING;
import static web4mo.whatsgoingon.response.Response.success;

@RequiredArgsConstructor
@Tag(name = "ArticleScrap Controller")
@RestController
@Slf4j
@ResponseBody
@RequestMapping(value="/main")
public class ArticleScrapController {
    private final ArticleScrapService articleScrapService;

    @Transactional
    @PostMapping()
    public Response scraping(@RequestParam(value = "articleId") Long articleId, @RequestParam(value = "folderId") Long folderId){
        articleScrapService.scraping(articleId, folderId);
        return success(SCRAPING);
    }
}
