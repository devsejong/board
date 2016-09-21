package net.chandol.study.board.board.model;

import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @Tolerate
    protected Board() {
    }

    public Board(String name) {
        this.name = name;
    }
}
