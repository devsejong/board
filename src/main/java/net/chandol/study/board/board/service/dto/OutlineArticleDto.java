package net.chandol.study.board.board.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OutlineArticleDto {
    private Long id;
    private Long boardId;
    private String name;
    private String title;
    private LocalDateTime created;

    public OutlineArticleDto(Long id, Long boardId, String name, String title, LocalDateTime created) {
        this.id = id;
        this.boardId = boardId;
        this.name = name;
        this.title = title;
        this.created = created;
    }
}
