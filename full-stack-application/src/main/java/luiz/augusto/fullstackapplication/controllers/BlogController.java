package luiz.augusto.fullstackapplication.controllers;

import luiz.augusto.fullstackapplication.dtos.ArticleDTO;
import luiz.augusto.fullstackapplication.dtos.BasicUserDTO;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.exceptions.IllegalActionException;
import luiz.augusto.fullstackapplication.mappers.ArticleMapper;
import luiz.augusto.fullstackapplication.mappers.UserMapper;
import luiz.augusto.fullstackapplication.requests.NewArticleRequestBody;
import luiz.augusto.fullstackapplication.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;


    @PostMapping("/articles/new")
    public ResponseEntity<ArticleDTO> addNewArticle(
            @RequestParam("userId") Long userId,
            @RequestBody @Valid NewArticleRequestBody newArticleRequestBody
    )
    {
        var article = blogService.addNewArticle(
                userId,
                newArticleRequestBody.getTitle(),
                newArticleRequestBody.getText(),
                newArticleRequestBody.getTags()
        );
        var articleDTO = articleMapper.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }

    @PostMapping("/articles/{action}/{userId}/{articleId}")
    public ResponseEntity<BasicUserDTO> likeOrFavoriteArticle(@PathVariable("userId") Long userId,
                                                            @PathVariable("articleId") Long articleId,
                                                            @PathVariable("action") String action)
    {
        BasicUser basicUser;

        if(action.equalsIgnoreCase("like")) basicUser = blogService.likeArticle(userId, articleId);
        else if(action.equalsIgnoreCase("favorite"))
            basicUser = blogService.favoriteArticle(userId, articleId);
        else throw new IllegalActionException("Action not '"+ action +"' permitted");

        var basicUserDTO = userMapper.toBasicUserDTO(basicUser);
        return ResponseEntity.ok().body(basicUserDTO);
    }

    @PostMapping("/articles/edit")
    public ResponseEntity<ArticleDTO> editArticle(@RequestParam("articleId") Long articleId,
                                                  @RequestBody NewArticleRequestBody newArticleRequestBody)
    {
        var article = blogService.editExistingArticle(
                articleId,
                newArticleRequestBody.getTitle(),
                newArticleRequestBody.getText(),
                newArticleRequestBody.getTags());
        var articleDTO = articleMapper.toArticleDTO(article);

        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping("/articles/get")
    public ResponseEntity<List<ArticleDTO>> getArticlesByTag(@RequestParam("tag") String tag)
    {
        var articles = blogService.getArticlesByTag(tag);
        var articlesDTO = articles.stream().map(articleMapper::toArticleDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(articlesDTO);
    }

}
