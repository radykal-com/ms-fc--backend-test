package com.scmspain.tweet.infrastructure.repository;

import com.scmspain.tweet.domain.model.Tweet;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTweetRepositoryImplTest {

    private static String FIND_ALL_PUBLISHED = "SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=false ORDER BY t.id DESC";
    private static String FIND_ALL_DISCARDED = "SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=true ORDER BY t.discardedDate DESC";

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

    @Test
    public void shouldFindAllPublished() {
        TypedQuery query = mock(TypedQuery.class);
        when(query.getResultList()).thenReturn(Arrays.asList());
        when(entityManager.createQuery(FIND_ALL_PUBLISHED, Tweet.class)).thenReturn(query);

        repository.findAllPublished();

        verify(entityManager, times(1)).createQuery(FIND_ALL_PUBLISHED, Tweet.class);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void shouldFindAllDiscarded() {
        TypedQuery query = mock(TypedQuery.class);
        when(query.getResultList()).thenReturn(Arrays.asList());
        when(entityManager.createQuery(FIND_ALL_DISCARDED, Tweet.class)).thenReturn(query);

        repository.findAllDiscarded();

        verify(entityManager, times(1)).createQuery(FIND_ALL_DISCARDED, Tweet.class);
        verify(query, times(1)).getResultList();
    }
}
