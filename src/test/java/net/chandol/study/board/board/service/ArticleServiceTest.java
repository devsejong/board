package net.chandol.study.board.board.service;

import net.chandol.study.board.board.service.dto.ArticleCreateRequest;
import net.chandol.study.board.board.service.dto.ArticleModifyRequest;
import net.chandol.study.board.board.service.dto.BoardCreateRequest;
import net.chandol.study.board.board.infra.repository.ArticleRepository;
import net.chandol.study.board.board.model.Article;
import net.chandol.study.board.board.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    BoardService boardService;

    public Board getFixtureBoard(){
        return boardService.createBoard(new BoardCreateRequest("test"));
    }

    @Test
    public void createAndGetArticles() throws Exception {
        //given
        Board board = getFixtureBoard();
        ArticleCreateRequest request = new ArticleCreateRequest(board.getId(), "name", "title", "body");

        //when
        Article article = articleService.createArticle(request);

        //then
        assertThat(article.getBody()).isEqualTo(request.getBody());
        assertThat(article.getTitle()).isEqualTo(request.getTitle());
        assertThat(article.getName()).isEqualTo(request.getName());
        assertThat(article.getCreated()).isNotNull();
        assertThat(article.getUpdated()).isNull();
    }

    @Test
    @Transactional
    public void modifyArticle() throws Exception {
        //given
        Board board = getFixtureBoard();
        ArticleCreateRequest request = new ArticleCreateRequest(board.getId(), "name", "title", "body");
        Article article = articleService.createArticle(request);

        //when
        articleService.modifyArticle(article.getId(), new ArticleModifyRequest("new name", "new title", "new body"));

        // 테스트시에는 트랜잭션이 제대로 종료되지 않아 날짜값이 설정되지 않는다.
        // 강제로 flush를 호출하여 값이 옳바르게 들어가는지 확인한다.
        articleRepository.flush();

        //then
        Article quriedArticle = articleRepository.getOne(article.getId());
        assertThat(quriedArticle.getUpdated()).isNotNull();
        assertThat(quriedArticle.getBody()).isEqualTo("new body");
        assertThat(quriedArticle.getTitle()).isEqualTo("new title");
        assertThat(quriedArticle.getName()).isEqualTo("new name");
    }

    @Test
    public void deleteArticle() throws Exception {
        //given
        Board board = getFixtureBoard();
        ArticleCreateRequest request = new ArticleCreateRequest(board.getId(), "name", "title", "body");
        articleService.createArticle(request);
        Article article = articleService.createArticle(request);

        assertThat(articleRepository.findAll()).hasSize(2);

        //when
        articleService.deleteArticle(article.getId());

        //then
        assertThat(articleRepository.findAll()).hasSize(1);
    }

}