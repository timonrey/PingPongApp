package com.commercetools.pingpong.newModel;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewPlayerTest {

    @Test
    public void shouldCreateNewPlayer() {
        NewPlayer newPlayer = new NewPlayer("Nicola");
        assertThat("test", newPlayer.getName(), is("Nicola"));
    }

}
