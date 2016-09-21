package net.chandol.study.board.board.infra.repository.custom;

import net.chandol.study.board.board.model.Board;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import javax.persistence.EntityManager;

public class BoardRepositoryCustomImpl extends QueryDslJpaRepository<Board, Long> {
    public BoardRepositoryCustomImpl(JpaEntityInformation<Board, Long> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

}
