package com.example.codebreaker;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
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

public class Main3ActivityTest {
    @Rule
    public ActivityTestRule<Main3Activity> mActivityRule =
            new ActivityTestRule<>(Main3Activity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.board)).check(matches(isDisplayed()));
    }

    @Test
    public void clickPegs() {
        Main3Activity main3ActivityClass = mActivityRule.getActivity();

        assertFalse(main3ActivityClass.dragValue());
        onView(withId(R.id.taskBar1))
                .perform(click());
        assertTrue(main3ActivityClass.dragValue());

        ViewInteraction appCompatScroll = onView(withId(R.id.box11));
        appCompatScroll.perform(scrollTo());

        assertFalse(main3ActivityClass.dropValue());
        onView(withId(R.id.box11))
                .perform(click());
        assertTrue(main3ActivityClass.dropValue());
    }

    @Test
    public void generateCode() {
        Main3Activity main3ActivityClass = mActivityRule.getActivity();
        assertEquals(main3ActivityClass.getPegCode().size(), 4);
        for (View v:
                main3ActivityClass.getPegCode()) {
            assertNotNull(v);
        }
    }

    @Test
    public void checkRow() {
        Main3Activity main3ActivityClass = mActivityRule.getActivity();
        ViewInteraction appCompatScroll = onView(withId(R.id.box11));
        appCompatScroll.perform(scrollTo());

        onView(withId(R.id.taskBar1))
                .perform(click());
        onView(withId(R.id.box11))
                .perform(click());
        onView(withId(R.id.taskBar3))
                .perform(click());
        onView(withId(R.id.box12))
                .perform(click());
        onView(withId(R.id.taskBar5))
                .perform(click());
        onView(withId(R.id.box13))
                .perform(click());
        onView(withId(R.id.taskBar2))
                .perform(click());
        onView(withId(R.id.box14))
                .perform(click());

        assertEquals(4, main3ActivityClass.getBoxesFilled());
        assertEquals(0, main3ActivityClass.getCurrentRow());


        onView(withId(R.id.check1))
                .perform(click());

        assertEquals(0, main3ActivityClass.getBoxesFilled());
        assertEquals(1, main3ActivityClass.getCurrentRow());

        onView(withId(R.id.taskBar1))
                .perform(click());
        onView(withId(R.id.box11))
                .perform(click());
        assertFalse(main3ActivityClass.dropValue());

        onView(withId(R.id.taskBar1))
                .perform(click());
        onView(withId(R.id.box21))
                .perform(click());
        assertTrue(main3ActivityClass.dropValue());

        assertEquals(1, main3ActivityClass.getBoxesFilled());
    }
}