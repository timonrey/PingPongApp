package com.commercetools.pingpong.newModel;

import com.commercetools.pingpong.model.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewPlayerTest {

    @Test
    public void shouldCreateNewPlayer() {
        Player newPlayer = new Player("Nicola");
        assertThat("test", newPlayer.getName(), is("Nicola"));
    }

}
