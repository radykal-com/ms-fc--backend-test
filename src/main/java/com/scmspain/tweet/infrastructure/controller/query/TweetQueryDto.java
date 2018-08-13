package com.scmspain.tweet.infrastructure.controller.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class TweetQueryDto {

    @JsonIgnore
    private Long id;

    @JsonProperty
    private String publisher;

    @JsonProperty(value = "tweet")
    private String content;

    @JsonIgnore
    private Long pre2015MigrationStatus;

    @JsonIgnore
    private Boolean discarded;

    @JsonIgnore
    private Date discardedDate;

    public TweetQueryDto() {
    }

    @JsonProperty
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty
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

