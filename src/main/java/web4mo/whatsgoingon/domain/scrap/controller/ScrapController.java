package web4mo.whatsgoingon.domain.scrap.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.scrap.repository.FolderRepository;
import web4mo.whatsgoingon.domain.scrap.repository.ScrapRepository;
import web4mo.whatsgoingon.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@Tag(name = "Scrap Controller")
@RestController
@AllArgsConstructor
@RequestMapping(value="/mypage")
public class ScrapController {

    FolderRepository folderRepository;
    ScrapRepository scrapRepository;

    @GetMapping("/scrapList")
    @ResponseStatus(OK)
    public Response getFolderList(){
        return success(FOLDER_LIST);
    }
}
