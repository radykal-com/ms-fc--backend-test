package com.scmspain.tweet.infrastructure.repository;

import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.domain.model.Tweet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class InMemoryTweetRepositoryImpl implements TweetRepository {

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
        TypedQuery<Tweet> query = this.entityManager.createQuery("SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=false ORDER BY t.id DESC", Tweet.class);
        return query.getResultList();
    }

    @Override
    public List<Tweet> findAllDiscarded() {
        TypedQuery<Tweet> query = this.entityManager.createQuery("SELECT t FROM Tweet t WHERE t.pre2015MigrationStatus<>99 AND t.discarded=true ORDER BY t.discardedDate DESC", Tweet.class);
        return query.getResultList();
    }
}
