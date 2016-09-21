package net.chandol.study.board.board.infra.repository;

import net.chandol.study.board.board.infra.repository.custom.CustomArticleRepository;
import net.chandol.study.board.board.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, CustomArticleRepository {
}
