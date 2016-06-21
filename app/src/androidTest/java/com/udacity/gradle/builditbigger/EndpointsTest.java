package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.abhi.jokedisplay.JokeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Abhishek on 6/20/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndpointsTest {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityIntentTestRule
            = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testEndpointsAsyncTask() throws InterruptedException {
//
//        final android.app.Fragment fragment = mainActivityIntentTestRule.getActivity().getFragmentManager()
//                .findFragmentById(R.id.fragment);
//        Assert.assertNotNull(fragment);

//        onView(withId(R.id.superman))
//                .check(matches(withText("superman")));

//        onView(withId(R.id.instructions_text_view))
//                .check(matches(withText(R.string.instructions)));

        // Press the button the launch new activity
        onView(withId(R.id.tellJokeBtn))
                .perform(click());

        intended(allOf(
//                hasComponent("com.udacity.gradle.builditbigger/com.abhi.jokedisplay.JokeActivity"),
                hasExtra(
                        JokeActivity.EXTRA_JOKE_KEY,
                        "Joke Example"
//                        not(equalTo(""))//doesn't work!!!
//                        notNullValue()//doesn't work!!
//                        allOf(//doesn't work!
//                                not(equalTo(null)),
//                                not(equalTo(""))
//                        )
                )
        ));

    }
}
