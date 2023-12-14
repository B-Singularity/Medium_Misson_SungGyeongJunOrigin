package com.ll.medium.domain.article.api;

import com.ll.medium.domain.article.dto.ArticleForm;
import com.ll.medium.domain.article.entity.Article;
import com.ll.medium.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleApiController {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public ModelAndView getArticlePage() {
        ModelAndView modelAndView = new ModelAndView("articles");
        List<Article> articles = articleService.index();
        modelAndView.addObject("articleList", articles);
        return modelAndView;
    }

    @GetMapping("/articles/{id}")
    public Article show(@PathVariable Long id) {return articleService.show(id); }

    @GetMapping("/articles/new")
    public ModelAndView getNewArticle() {
        ModelAndView modelAndView = new ModelAndView("new");
        return modelAndView;
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
