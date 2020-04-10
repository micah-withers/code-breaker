package com.example.codebreaker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

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
        for (int i:
                main3ActivityClass.getPegCode()) {
            assertTrue(i > -1 && i < 6);
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

    @Test
    public void checkFeedback() {
        Main3Activity main3ActivityClass = mActivityRule.getActivity();
        ViewInteraction appCompatScroll = onView(withId(R.id.box11));
        appCompatScroll.perform(scrollTo());

        ArrayList<Integer> pegCode = main3ActivityClass.getPegCode();
        ArrayList<Integer> feedback;

        for (int i = 0; i < pegCode.size(); ++i) {
            switch (pegCode.get(i)) {
                case 0:
                    onView(withId(R.id.taskBar2))
                            .perform(click());
                    break;
                case 1:
                    onView(withId(R.id.taskBar3))
                            .perform(click());
                    break;
                case 2:
                    onView(withId(R.id.taskBar4))
                            .perform(click());
                    break;
                case 3:
                    onView(withId(R.id.taskBar5))
                            .perform(click());
                    break;
                case 4:
                    onView(withId(R.id.taskBar6))
                            .perform(click());
                    break;
                case 5:
                    onView(withId(R.id.taskBar1))
                            .perform(click());
                    break;
            }
            switch (i) {
                case 0:
                    onView(withId(R.id.box11))
                            .perform(click());
                    break;
                case 1:
                    onView(withId(R.id.box12))
                            .perform(click());
                    break;
                case 2:
                    onView(withId(R.id.box13))
                            .perform(click());
                    break;
                case 3:
                    onView(withId(R.id.box14))
                            .perform(click());
                    break;
            }
        }
        onView(withId(R.id.check1))
                .perform(click());

        feedback = main3ActivityClass.getFeedback();
        for (int i = 0; i < feedback.size(); ++i) {
            assertTrue(feedback.get(i) < 1);
        }

        for (int i = 0; i < pegCode.size(); ++i) {
            switch (pegCode.get(i)) {
                case 0:
                    onView(withId(R.id.taskBar1))
                            .perform(click());
                    break;
                case 1:
                    onView(withId(R.id.taskBar2))
                            .perform(click());
                    break;
                case 2:
                    onView(withId(R.id.taskBar3))
                            .perform(click());
                    break;
                case 3:
                    onView(withId(R.id.taskBar4))
                            .perform(click());
                    break;
                case 4:
                    onView(withId(R.id.taskBar5))
                            .perform(click());
                    break;
                case 5:
                    onView(withId(R.id.taskBar6))
                            .perform(click());
                    break;
            }
            switch (i) {
                case 0:
                    onView(withId(R.id.box21))
                            .perform(click());
                    break;
                case 1:
                    onView(withId(R.id.box22))
                            .perform(click());
                    break;
                case 2:
                    onView(withId(R.id.box23))
                            .perform(click());
                    break;
                case 3:
                    onView(withId(R.id.box24))
                            .perform(click());
                    break;
            }
        }
        onView(withId(R.id.check2))
                .perform(click());
        feedback = main3ActivityClass.getFeedback();
        for (int i = 0; i < feedback.size(); ++i) {
            assertEquals(1, (int) feedback.get(i));
        }
    }

}