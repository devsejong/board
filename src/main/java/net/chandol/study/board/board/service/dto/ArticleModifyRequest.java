package net.chandol.study.board.board.service.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleModifyRequest {
    private String name;
    private String title;
    private String body;

    protected ArticleModifyRequest() {
    }

    @Tolerate
    public ArticleModifyRequest(String name, String title, String body) {
        this.name = name;
        this.title = title;
        this.body = body;
    }
}
