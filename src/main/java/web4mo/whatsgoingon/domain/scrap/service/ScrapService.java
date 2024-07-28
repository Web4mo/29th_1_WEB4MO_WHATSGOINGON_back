package web4mo.whatsgoingon.domain.scrap.service;

import org.springframework.stereotype.Service;
import web4mo.whatsgoingon.domain.article.repository.ArticleRepository;
import web4mo.whatsgoingon.domain.scrap.entity.Scrap;
import web4mo.whatsgoingon.domain.scrap.repository.FolderRepository;
import web4mo.whatsgoingon.domain.scrap.repository.ScrapRepository;

@Service
public class ScrapService {
    ScrapRepository scrapRepository;
    FolderRepository folderRepository;
    ArticleRepository articleRepository;
    public void scraping(Long articleId, Long folderId) {
        articleRepository.findById(articleId);
        folderRepository.findById(folderId);
        Scrap scrap = Scrap.builder()
                .article(articleRepository.findAById(articleId))
                .folder(folderRepository.findAById(folderId)).build();
        scrapRepository.save(scrap);
    }
}
