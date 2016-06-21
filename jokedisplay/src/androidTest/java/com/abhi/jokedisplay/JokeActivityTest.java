package com.abhi.jokedisplay;

import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Abhishek on 6/20/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokeActivityTest {

    private final String TEST_JOKE = "test joke";

    @Rule
    public ActivityTestRule<JokeActivity> mActivityTestRule
            = new ActivityTestRule<JokeActivity>(JokeActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent i = super.getActivityIntent();
            i.putExtra(JokeActivity.EXTRA_JOKE_KEY, TEST_JOKE);
            return i;
        }
    };


    @Test
    public void jokeIsDisplayed() {
        onView(withId(R.id.jokeTxtV)).check(matches(withText(TEST_JOKE)));
//        onView(withId(R.id.jokeTxtV)).check(matches(withText("batman")));
    }
}
