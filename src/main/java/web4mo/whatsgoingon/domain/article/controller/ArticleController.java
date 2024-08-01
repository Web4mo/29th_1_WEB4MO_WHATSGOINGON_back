package web4mo.whatsgoingon.domain.article.controller;

package web4mo.whatsgoingon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web4mo.whatsgoingon.domain.article.dto.ArticleDto;
import web4mo.whatsgoingon.domain.article.service.ArticleService;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        List<ArticleDto> articles = articleService.getArticles("트럼프", 5, "date");
        model.addAttribute("articles", articles);
        return "index";
    }
}
