package luiz.augusto.fullstackapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewArticleRequestBody {

    private String title;
    private String text;

}
