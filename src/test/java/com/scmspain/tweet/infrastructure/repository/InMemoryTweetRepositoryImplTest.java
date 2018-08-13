package com.scmspain.tweet.infrastructure.repository;

import com.scmspain.tweet.domain.model.Tweet;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTweetRepositoryImplTest {

    private InMemoryTweetRepositoryImpl repository;

    @Mock
    private EntityManager entityManager;

    @Before
    public void setUp() {
        this.repository = new InMemoryTweetRepositoryImpl(entityManager);
    }

    @Test
    public void shouldPersist() {
        Tweet tweet = new Tweet();
        repository.persist(tweet);
        verify(entityManager, times(1)).persist(tweet);
    }

    @Test
    public void shouldFindById() {
        Long id = 1L;
        Tweet tweet = new Tweet();
        tweet.setId(id);
        when(entityManager.find(Tweet.class, id)).thenReturn(tweet);
        Tweet result = repository.findById(id);
        verify(entityManager, times(1)).find(Tweet.class, id);
        assertThat(result).isEqualTo(tweet);
    }
}
