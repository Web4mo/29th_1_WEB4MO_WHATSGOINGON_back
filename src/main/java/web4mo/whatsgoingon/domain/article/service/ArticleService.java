package web4mo.whatsgoingon.domain.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.config.NaverApi.NaverApi;
import web4mo.whatsgoingon.config.NaverApi.articleApiDto;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.entity.Article;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;
import web4mo.whatsgoingon.domain.scrap.dto.FolderResponseDto;
import web4mo.whatsgoingon.domain.scrap.entity.Folder;
import web4mo.whatsgoingon.domain.user.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private NaverApi naverApi;

    public List<ArticleDto> getArticles(String query, int count, String sort) {
        List<articleApiDto> articles = naverApi.naverApiResearch(query, count, sort);
        return articles.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void saveArticles(String query, int count, String sort) {
        NaverApi test = new NaverApi();

        List<articleApiDto> articles = test.naverApiResearch("트럽프", 2, "date");
        for (articleApiDto article : articles) {
            System.out.println(article.getTitle()); // 기사 제목들만 출력되는중
        }
        articleRepository.saveAll(articleEntities);
    }

    public List<articleApiDto> getFolderList(String sortingMethod){
        Member member = userService.getCurrentMember();
        List<Folder> folderList;
        List<FolderResponseDto> response = new ArrayList<>();

        if(sortingMethod.equals("키워드추천")){
            List<Article> ArticleList = ArticleRepository.findByMemberOrderByKeyword(member);
        }

        for(Article i : ArticleList){
            response.add(FolderResponseDto.builder()
                    .ArticleId(i.getId())
                    .folderName(i.getTitle())
                    .lastModifiedAt(i.getModifiedAt())
                    .build());
        }
        return response;
    }
}
