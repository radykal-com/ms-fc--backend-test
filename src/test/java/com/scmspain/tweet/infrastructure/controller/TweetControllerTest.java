package com.scmspain.tweet.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.infrastructure.configuration.TestConfiguration;
import com.scmspain.tweet.domain.exception.TweetNotFoundException;
import com.scmspain.tweet.domain.service.TweetService;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class TweetControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private TweetService tweetService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldReturn200WhenInsertingAValidTweet() throws Exception {
        mockMvc.perform(newTweet("Prospect", "Breaking the law"))
            .andExpect(status().is(201));
    }

    @Test
    public void shouldReturn400WhenInsertingAnInvalidTweet() throws Exception {
        doThrow(IllegalArgumentException.class).when(tweetService).publishTweet(any(), any());
        mockMvc.perform(newTweet("Schibsted Spain", "We are Schibsted Spain (look at our home page http://www.schibsted.es/), we own Vibbo, InfoJobs, fotocasa, habitaclia, coches.net and milanuncios. Welcome!"))
                .andExpect(status().is(400));
    }

    @Test
    public void shouldReturnAllPublishedTweets() throws Exception {
        when(tweetService.listAllPublishedTweets()).thenReturn(Arrays.asList(new Tweet()));

        MvcResult getResult = mockMvc.perform(get("/tweet"))
                .andExpect(status().is(200))
                .andReturn();

        String content = getResult.getResponse().getContentAsString();
        verify(tweetService, times(1)).listAllPublishedTweets();
        assertThat(new ObjectMapper().readValue(content, List.class).size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnAllDiscardedTweets() throws Exception {
        when(tweetService.listAllDiscardedTweets()).thenReturn(Arrays.asList(new Tweet()));

        MvcResult getResult = mockMvc.perform(get("/discarded"))
            .andExpect(status().is(200))
            .andReturn();

        String content = getResult.getResponse().getContentAsString();
        verify(tweetService, times(1)).listAllDiscardedTweets();
        assertThat(new ObjectMapper().readValue(content, List.class).size()).isEqualTo(1);
    }

    @Test
    public void shouldReturn204WhenDiscardingATweet() throws Exception {
        mockMvc.perform(discardTweet())
            .andExpect(status().is(204));
    }

    @Test
    public void shouldReturn404IfDiscardingNonExistantTweet() throws Exception {
        doThrow(TweetNotFoundException.class).when(tweetService).discardTweet(any());
        mockMvc.perform(discardTweet())
            .andExpect(status().is(404));
    }

    private MockHttpServletRequestBuilder newTweet(String publisher, String tweet) {
        return post("/tweet")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(format("{\"publisher\": \"%s\", \"tweet\": \"%s\"}", publisher, tweet));
    }

    private MockHttpServletRequestBuilder discardTweet() {
        return post("/discarded")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\"tweet\": \"1\"}");
    }

}
