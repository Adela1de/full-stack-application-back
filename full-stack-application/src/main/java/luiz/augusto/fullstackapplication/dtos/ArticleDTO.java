package luiz.augusto.fullstackapplication.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {

    private String author;
    private String title;
    private String text;

}
