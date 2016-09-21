package net.chandol.study.board.board.service;


import net.chandol.study.board.board.service.dto.BoardCreateRequest;
import net.chandol.study.board.board.infra.repository.BoardRepository;
import net.chandol.study.board.board.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardCreateRequest request) {
        return boardRepository.save(new Board(request.getName()));
    }
}
