package net.chandol.study.board.board.service.dto;

import lombok.Data;

@Data
public class ArticleCreateRequest {
    private Long boardId;
    private String name;
    private String title;
    private String body;

    protected ArticleCreateRequest() {
    }

    public ArticleCreateRequest(Long boardId, String name, String title, String body) {
        this.boardId = boardId;
        this.name = name;
        this.title = title;
        this.body = body;
    }
}
