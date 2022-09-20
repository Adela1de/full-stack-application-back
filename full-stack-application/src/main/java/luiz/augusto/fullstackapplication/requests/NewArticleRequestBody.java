package luiz.augusto.fullstackapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import luiz.augusto.fullstackapplication.entities.Tag;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewArticleRequestBody {

    @NotEmpty(message = "Title is empty!")
    private String title;
    @NotEmpty(message = "Text is empty!")
    private String text;
    private List<Tag> tags;
}
