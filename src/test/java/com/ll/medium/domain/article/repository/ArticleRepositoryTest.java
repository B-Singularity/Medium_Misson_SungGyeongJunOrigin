package com.ll.medium.domain.article.repository;

import com.ll.medium.domain.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("postTest")
    public void postArticle() {
        final Article article = Article.builder()
                .id(1L)
                .title("testTitle")
                .content("for test")
                .build();
        //when
        final Article result = articleRepository.save(article);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("testTitle");
        assertThat(result.getContent()).isEqualTo("for test");
    }
}
