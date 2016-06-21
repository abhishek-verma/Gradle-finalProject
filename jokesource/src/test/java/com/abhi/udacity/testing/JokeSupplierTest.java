package com.abhi.udacity.testing;

import com.abhi.udacity.JokeSupplier;

import org.junit.Test;

/**
 * Created by Abhishek on 6/20/2016.
 */
public class JokeSupplierTest {

    @Test
    public void getJokeShouldReturnNonEmptyString() {
        JokeSupplier js = new JokeSupplier();
        String joke = js.getJoke();
        assert joke.length() > 0;
    }
}
