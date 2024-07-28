package web4mo.whatsgoingon.domain.scrap.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web4mo.whatsgoingon.domain.scrap.service.ScrapService;
import web4mo.whatsgoingon.response.Response;

import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@RestController
@RequiredArgsConstructor
@Tag(name = "Scrap Controller")
public class ScrapController {
    private final ScrapService scrapService;
    @PostMapping()
    public Response scraping(Long articleId, Long folderId){
        scrapService.scraping(articleId, folderId);
        return success(SCRAPING);
    }

}
