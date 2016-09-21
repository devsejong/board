package net.chandol.study.board.board.infra.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import net.chandol.study.board.board.model.Article;
import net.chandol.study.board.board.model.Board;
import net.chandol.study.board.board.model.QArticle;
import net.chandol.study.board.board.model.QBoard;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.Collections;
import java.util.List;

public class ArticleRepositoryImpl extends QueryDslRepositorySupport implements CustomArticleRepository {
    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Page<Article> getArticles(Board board, Pageable pageable) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> articleJPQLQuery = from(article)
                .innerJoin(article.board, QBoard.board)
                .where(QBoard.board.eq(board));

        JPQLQuery<Article> query = getQuerydsl()
                .applyPagination(pageable, articleJPQLQuery)
                .orderBy(article.created.desc());

        long total = articleJPQLQuery.fetchCount();
        List<Article> content = total > pageable.getOffset() ? query.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageable, total);
    }

}
