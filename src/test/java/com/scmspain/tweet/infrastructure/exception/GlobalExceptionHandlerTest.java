package com.scmspain.tweet.infrastructure.exception;

import com.scmspain.tweet.domain.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Before
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void shouldReturnBadRequestWhenIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("illegal argument");

        ResponseEntity<Object> response = globalExceptionHandler.handleIllegalArgumentException(exception, webRequest);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void shouldReturnNotFoundWhenEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("not found");

        ResponseEntity<Object> response = globalExceptionHandler.handleNotFoundException(exception, webRequest);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
