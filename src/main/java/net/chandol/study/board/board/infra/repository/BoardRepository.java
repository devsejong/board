package net.chandol.study.board.board.infra.repository;

import net.chandol.study.board.board.model.Board;
import net.chandol.study.board.board.infra.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{

}
