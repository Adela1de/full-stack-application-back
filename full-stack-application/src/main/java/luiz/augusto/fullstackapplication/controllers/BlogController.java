package luiz.augusto.fullstackapplication.controllers;

import luiz.augusto.fullstackapplication.dtos.ArticleDTO;
import luiz.augusto.fullstackapplication.mappers.ArticleMapper;
import luiz.augusto.fullstackapplication.requests.NewArticleRequestBody;
import luiz.augusto.fullstackapplication.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ArticleMapper articleMapper;


    @PostMapping("/articles/new")
    public ResponseEntity<ArticleDTO> addNewArticle(
            @RequestParam("userId") Long userId,
            @RequestBody @Valid NewArticleRequestBody newArticleRequestBody
    )
    {
        var article = blogService.addNewArticle(
                userId,
                newArticleRequestBody.getTitle(),
                newArticleRequestBody.getText()
        );
        var articleDTO = articleMapper.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }
}
