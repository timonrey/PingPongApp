package com.commercetools.pingpong.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GameTest {

    @Test
    public void shouldReturnGivenButtonId() {
        Message message = new Message("1", 1);
        assertThat("Button ID must be 1", message.getButtonId(), is("1"));
    }

    @Test
    public void shouldReturnGivenActionType() {
        Message message = new Message("1", 1);
        assertThat("Action Type must be 1", message.getActionType(), is(1));
    }

}
