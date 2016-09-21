package net.chandol.study.board.board.infra.repository.custom;

import net.chandol.study.board.board.model.Article;
import net.chandol.study.board.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomArticleRepository {
    Page<Article> getArticles(Board board, Pageable pageable);
}
