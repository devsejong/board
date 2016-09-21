package net.chandol.study.board.board.service;

import net.chandol.study.board.board.service.dto.BoardCreateRequest;
import net.chandol.study.board.board.infra.repository.BoardRepository;
import net.chandol.study.board.board.model.Board;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;



    @Test
    public void createBoardTest() {
        //given
        BoardCreateRequest request1 = new BoardCreateRequest("test1");
        BoardCreateRequest request2 = new BoardCreateRequest("test2");
        BoardCreateRequest request3 = new BoardCreateRequest("test3");

        //when
        boardService.createBoard(request1);
        boardService.createBoard(request2);
        boardService.createBoard(request3);

        //then
        List<Board> boards = boardRepository.findAll();
        Assertions.assertThat(boards)
                .hasSize(3)
                .extracting("name")
                .contains("test1", "test2", "test3");
    }

}