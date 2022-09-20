package luiz.augusto.fullstackapplication.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luiz.augusto.fullstackapplication.entities.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {

    private String author;
    private String title;
    private String text;
    private List<Tag> tags = new ArrayList<>();
}
