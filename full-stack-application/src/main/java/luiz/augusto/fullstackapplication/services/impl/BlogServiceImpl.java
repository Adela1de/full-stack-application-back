package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.repositories.ArticleRepository;
import luiz.augusto.fullstackapplication.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ArticleRepository articleRepository;
}
