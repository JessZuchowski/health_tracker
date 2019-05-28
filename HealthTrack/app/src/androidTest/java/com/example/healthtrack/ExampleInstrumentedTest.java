package com.example.healthtrack;

import android.app.Activity;
import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ExerciseActivity> activityTestRule = new ActivityTestRule<>(ExerciseActivity.class);

    @Test
    public void addToClickCounter() {
        onView(withId(R.id.text_counter))
                .perform(clearText())
                .perform(typeText("10"));

        for (int x = 0; x < 11; x++) {
            onView(withId(R.id.count_button))
                    .perform(click());
        }
        onView(allOf(withId(R.id.text_counter), withText("10")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_counter))
                .check(matches(withText("10")));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.healthtrack", appContext.getPackageName());
    }
}
