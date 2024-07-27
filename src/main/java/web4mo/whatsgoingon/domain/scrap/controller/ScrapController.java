package web4mo.whatsgoingon.domain.scrap.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.response.Response;

import static org.springframework.http.HttpStatus.OK;
import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@Tag(name = "Scrap Controller")
@RestController
@AllArgsConstructor
@RequestMapping(value="/mypage")
public class ScrapController {

    @GetMapping("/scrapList")
    @ResponseStatus(OK)
    public Response getFolderList(@RequestBody String sortingMethod){
        List<FolderResponseDto>
        return success(FOLDER_LIST);
    }
}
