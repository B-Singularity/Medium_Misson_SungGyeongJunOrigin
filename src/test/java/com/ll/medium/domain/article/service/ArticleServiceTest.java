package com.ll.medium.domain.article.service;

import com.ll.medium.domain.article.dto.ArticleForm;
import com.ll.medium.domain.article.entity.Article;
import com.ll.medium.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    @Test
    @DisplayName("findById")
    public void findByIdTest() {
        //Given
        Long articleId = 1L;
        Article expectedArticle = Article.builder()
                .id(1L)
                .title("title1")
                .content("content1")
                .build();

        when(articleRepository.findById(articleId)).thenReturn(Optional.of(expectedArticle));

        //when
        Article result = articleService.show(articleId);

        //then
        assertNotNull(result);
        assertEquals(expectedArticle.getId(), result.getId());
        assertEquals(expectedArticle.getTitle(), result.getTitle());
        assertEquals(expectedArticle.getContent(), result.getContent());
    }
    @Test
    @DisplayName("creat article")
    public void creatArticleTest() {
        //Given
        ArticleForm articleForm = new ArticleForm().builder()
                .id(1L)
                .title("title1")
                .content("content")
                .build();

        Article articleToSave = Article.builder()
                .title(articleForm.getTitle())
                .content(articleForm.getContent())
                .build();
        // Stubbing - Mocking repository behavior
        when(articleRepository.save(any(Article.class))).thenReturn(articleToSave);

        // When
        Article createdArticle = articleService.create(articleForm);

        // Then
        assertNotNull(createdArticle);
        assertNull(createdArticle.getId()); // ID should be null for a newly created article

        // Verify that repository.save() method was called with the expected entity
        verify(articleRepository, times(1)).save(any(Article.class));
    }
    @Test
    @DisplayName("Update Article")
    public void updateArticleTest() {
        //Given
        Long id = 1L;
        ArticleForm articleForm = ArticleForm.builder()
                .id(id)
                .title("title1")
                .content("content1")
                .build();

        Article existingArticle = Article.builder()
                .id(id)
                .title("expected title")
                .content("expected content")
                .build();

        when(articleRepository.findById(id)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Article updatedArticle = articleService.update(id, articleForm);

        //then
        assertNotNull(updatedArticle);
        assertEquals("title1", updatedArticle.getTitle());
        assertEquals("content1", updatedArticle.getContent());

        // Verify that repository methods were called
        verify(articleRepository, times(1)).findById(id);
        verify(articleRepository, times(1)).save(any());
    }
    @Test
    @DisplayName("Delete Test")
    public void deleteArticleTest() {
        //Given
        Long id = 1L;
        Article existingArticle = Article.builder()
                        .id(id)
                        .title("Existing Title")
                        .content("Existing Content")
                        .build();

        when(articleRepository.findById(id)).thenReturn(Optional.of(existingArticle));

        //when
        Article deletedArticle = articleService.delete(id);

        //then
        assertNotNull(deletedArticle);
        assertEquals(existingArticle, deletedArticle);

        // Verify that delete method of repository was called with the expected entity
        verify(articleRepository, times(1)).findById(id);
        verify(articleRepository, times(1)).delete(existingArticle);
    }
}
