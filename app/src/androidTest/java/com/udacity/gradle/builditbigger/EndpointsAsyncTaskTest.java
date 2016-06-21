package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Abhishek on 6/21/2016.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements JokeListener {

    private static final String LOG_TAG = EndpointsAsyncTaskTest.class.getSimpleName();

    @Test
    public void testEndpointsAsyncTask() {

        new EndpointsAsyncTask().execute(this);
    }

    @Override
    public void onJokeReceivedListener(String joke) {
//        Log.i(LOG_TAG, "onJokeReceivedListener: Joke = " + joke);
//        assertEquals(007, "batman"); to check that the test is actually running :)
        assertNotEquals(joke, "");
        assertNotEquals(joke, null);
    }
}