package luiz.augusto.fullstackapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class NewArticleRequestBody {

    @NotEmpty(message = "Title is empty!")
    private String title;
    @NotEmpty(message = "Text is empty!")
    private String text;

}
