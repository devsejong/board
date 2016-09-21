package net.chandol.study.board.board;

import net.chandol.study.board.board.model.Article;
import net.chandol.study.board.board.model.Board;
import net.chandol.study.board.board.infra.repository.BoardRepository;
import net.chandol.study.board.board.service.ArticleService;
import net.chandol.study.board.board.service.BoardService;
import net.chandol.study.board.board.service.dto.ArticleCreateRequest;
import net.chandol.study.board.board.service.dto.ArticleModifyRequest;
import net.chandol.study.board.board.service.dto.BoardCreateRequest;
import net.chandol.study.board.board.service.dto.OutlineArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    ArticleService articleService;

    @GetMapping("/boards")
    ResponseEntity<List<Board>> getBoards() {
        return ResponseEntity.ok(
                boardRepository.findAll());
    }

    @GetMapping("/boards/{boardId}")
    ResponseEntity<Page<OutlineArticleDto>> getBoardArticles(
            @PathVariable Long boardId,
            @RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(
                articleService.getArticles(boardId, page));
    }

    @GetMapping("/articles/{articleId}")
    ResponseEntity<Article> getArticle(@PathVariable("articleId") Article article){
        return ResponseEntity.ok(article);
    }

    @PostMapping("/articles")
    ResponseEntity<Article> createArticle(ArticleCreateRequest request){
        Article article = articleService.createArticle(request);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/articles/{id}")
    ResponseEntity<Article> modifyArticle(@PathVariable Long id, ArticleModifyRequest request){
        Article article = articleService.modifyArticle(id, request);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/articles/{id}")
    void deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
    }

    @GetMapping("/createDummy")
    void createDummy() {
        boardService.createBoard(new BoardCreateRequest("test1"));
        Board board2 = boardService.createBoard(new BoardCreateRequest("test2"));
        Board board3 = boardService.createBoard(new BoardCreateRequest("test3"));

        for (int idx = 0; idx < 100; idx++) {
            articleService.createArticle(new ArticleCreateRequest(board2.getId(), "name" + idx, "title" + idx, "body" + idx));
            articleService.createArticle(new ArticleCreateRequest(board3.getId(), "name" + idx, "title" + idx, "body" + idx));
        }
    }

}
