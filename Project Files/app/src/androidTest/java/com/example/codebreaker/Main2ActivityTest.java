package com.example.codebreaker;


import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.*;

public class Main2ActivityTest {
    @Rule
    public ActivityTestRule<Main2Activity> mActivityRule =
            new ActivityTestRule<>(Main2Activity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isDisplayed()));

        ViewInteraction appCompatScroll = onView(withId(R.id.textView8));
        appCompatScroll.perform(scrollTo());
    }

    @Test
    public void startGame() {
        onView(withId(R.id.button))
                .perform(click());
        boolean bool = true;
        try {
            onView(withId(R.id.button)).check(matches(isDisplayed()));
            bool = false;
        } catch (NoMatchingViewException ignored) {
            System.out.println("Match not found");
        }
        assertTrue(bool);
    }
}