package net.chandol.study.board.board.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Board board;
    private String name;
    private String title;
    private String body;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Article(Board board, String name, String title, String body) {
        this.board = board;
        this.name = name;
        this.title = title;
        this.body = body;
    }

    protected Article() {
    }

    public void modifyArticle(String name, String title, String body) {
        this.name = name;
        this.title = title;
        this.body = body;
    }

    @PrePersist
    void setCreated(){
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void setUpdated(){
        this.updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

