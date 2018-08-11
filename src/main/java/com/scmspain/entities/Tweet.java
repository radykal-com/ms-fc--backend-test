package com.scmspain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false, length = 140)
    private String tweet;
    @Column(nullable = true)
    private Long pre2015MigrationStatus = 0L;

    @Column(nullable = false)
    @JsonIgnore
    private Boolean discarded;
    @Column(nullable = true)
    @JsonIgnore
    private Date discardedDate;

    public Tweet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Long getPre2015MigrationStatus() {
        return pre2015MigrationStatus;
    }

    public void setPre2015MigrationStatus(Long pre2015MigrationStatus) {
        this.pre2015MigrationStatus = pre2015MigrationStatus;
    }

    public Boolean getDiscarded() {
        return this.discarded;
    }

    public void setDiscarded(Boolean discarded) {
        this.discarded = discarded;
    }

    public Date getDiscardedDate() {
        return this.discardedDate;
    }

    public void setDiscardedDate(Date discardedDate) {
        this.discardedDate = discardedDate;
    }
}
