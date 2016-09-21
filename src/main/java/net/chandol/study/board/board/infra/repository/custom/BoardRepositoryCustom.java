package net.chandol.study.board.board.infra.repository.custom;

import net.chandol.study.board.board.model.Board;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BoardRepositoryCustom extends QueryDslPredicateExecutor<Board> {
}
