package luiz.augusto.fullstackapplication.mappers;


import luiz.augusto.fullstackapplication.dtos.ArticleDTO;
import luiz.augusto.fullstackapplication.entities.Article;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public static final ModelMapper modelMapper = new ModelMapper();

    public ArticleDTO toArticleDTO(Article article)
    {
        var articleDTO = new ArticleDTO();
        articleDTO = modelMapper.map(article, ArticleDTO.class);
        return articleDTO;
    }
}
