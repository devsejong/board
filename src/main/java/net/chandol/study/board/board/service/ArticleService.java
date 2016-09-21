package net.chandol.study.board.board.service;

import net.chandol.study.board.board.service.dto.ArticleCreateRequest;
import net.chandol.study.board.board.service.dto.ArticleModifyRequest;
import net.chandol.study.board.board.service.dto.OutlineArticleDto;
import net.chandol.study.board.board.infra.repository.ArticleRepository;
import net.chandol.study.board.board.infra.repository.BoardRepository;
import net.chandol.study.board.board.model.Article;
import net.chandol.study.board.board.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final Integer DEFAULT_PAGE_SIZE = 20;

    @Autowired ArticleRepository articleRepository;
    @Autowired BoardRepository boardRepository;

    public Page<OutlineArticleDto> getArticles(Long boardId, int pageNumber) {
        return getArticles(boardId, pageNumber, DEFAULT_PAGE_SIZE);
    }

    public Page<OutlineArticleDto> getArticles(Long boardId, int pageNumber, int size) {
        Board board = boardRepository.findOne(boardId);

        PageRequest pageRequest = new PageRequest(pageNumber, size);
        Page<Article> articles = articleRepository.getArticles(board, new PageRequest(pageNumber, size));
        List<OutlineArticleDto> outlineArticles = convertToOutlineArticleDto(articles.getContent());

        return new PageImpl<>(outlineArticles, pageRequest, articles.getTotalElements());
    }

    private List<OutlineArticleDto> convertToOutlineArticleDto(List<Article> content) {
        return content.stream()
                .map(a->new OutlineArticleDto(a.getId(), a.getBoard().getId(), a.getName(), a.getTitle(), a.getCreated()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Board board = boardRepository.getOne(request.getBoardId());
        Article article = new Article(board, request.getName(), request.getTitle(), request.getBody());
        return articleRepository.save(article);
    }

    @Transactional
    public Article modifyArticle(Long articleId, ArticleModifyRequest request) {
        Article article = articleRepository.getOne(articleId);
        article.modifyArticle(request.getName(), request.getTitle(), request.getBody());
        return article;
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.delete(articleId);
    }
}
