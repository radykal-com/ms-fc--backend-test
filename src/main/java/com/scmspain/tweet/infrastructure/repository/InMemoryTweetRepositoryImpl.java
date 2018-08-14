package com.scmspain.tweet.infrastructure.repository;

import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.domain.model.Tweet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class InMemoryTweetRepositoryImpl implements TweetRepository {

    private static String FIND_ALL_PUBLISHED_QUERY = "SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=false ORDER BY t.id DESC";
    private static String FIND_ALL_DISCARDED_QUERY = "SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=true ORDER BY t.discardedDate DESC";

    private EntityManager entityManager;

    public InMemoryTweetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Tweet tweet) {
        entityManager.persist(tweet);
    }

    @Override
    public Tweet findById(Long id) {
        return this.entityManager.find(Tweet.class, id);
    }

    @Override
    public List<Tweet> findAllPublished() {
        return findTweetsByQuery(FIND_ALL_PUBLISHED_QUERY);
    }

    @Override
    public List<Tweet> findAllDiscarded() {
        return findTweetsByQuery(FIND_ALL_DISCARDED_QUERY);
    }

    private List<Tweet> findTweetsByQuery(String query) {
        TypedQuery<Tweet> typedQuery = entityManager.createQuery(query, Tweet.class);
        return typedQuery.getResultList();
    }
}
