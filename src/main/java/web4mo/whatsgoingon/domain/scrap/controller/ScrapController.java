package web4mo.whatsgoingon.domain.scrap.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web4mo.whatsgoingon.domain.scrap.dto.FolderResponseDto;
import web4mo.whatsgoingon.domain.scrap.dto.ScrapPageDto;
import web4mo.whatsgoingon.domain.scrap.entity.Folder;
import web4mo.whatsgoingon.domain.scrap.service.ScrapService;
import web4mo.whatsgoingon.response.Response;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static web4mo.whatsgoingon.response.Message.*;
import static web4mo.whatsgoingon.response.Response.success;

@RestController
@RequiredArgsConstructor
@Tag(name = "Scrap Controller")
@RequestMapping(value="/scraping")
public class ScrapController {
    private final ScrapService scrapService;

    @GetMapping("/clickScrap")
    @ResponseStatus(OK) // 연필 버튼 클릭했을때, 이름 순서대로 sorting해서 반환
    public Response clickScrap(){
        List<FolderResponseDto> folderList = scrapService.clicScrap();
        return success(CLICK_SCRAP, folderList);
    }

    @PostMapping("/addScrap")
    @ResponseStatus(OK) // 폴더 지정 해서 스크랩 진행했을때 (실질적인 저장 진행됨)
    public Response scrapMain(@RequestParam(value = "articleId")Long articleId,
                              @RequestParam(value = "folderId")Long folderId){
        scrapService.scrapMain(articleId, folderId);
        return success(SCRAP_MAIN);
    }

    @GetMapping("/scrapPage")
    @ResponseStatus(OK)
    public Response scrapPage(@RequestParam(value = "scrapId")Long scrapId){
        ScrapPageDto scrapPageDto = scrapService.scrapPage(scrapId);
        return success(SCRAP_PAGE, scrapPageDto);
    }

    @DeleteMapping("/deleteScrap")
    @ResponseStatus(OK)
    public Response deleteScrap(@RequestParam(value = "scrapId")Long scrapId){
        scrapService.deleteScrap(scrapId);
        return success(DELETE_SCRAP);
    }

    @PutMapping("/scrapMemo")
    @ResponseStatus(OK)
    @Transactional
    public Response scrapMemo(@RequestParam(value = "scrapId")Long scrapId,
                              @RequestParam(value = "memo")String memo){
        scrapService.scrapMemo(scrapId, memo);
        return success(SCRAP_MEMO);
    }
    @PostMapping("/scrapAi")
    @ResponseStatus(OK)
    @Transactional
    public Response scrapAI(@RequestParam(value = "scrapId")Long scrapId){
        String response = scrapService.scrapAI(scrapId);
        return success(SCRAP_AI, response);
    }

}
