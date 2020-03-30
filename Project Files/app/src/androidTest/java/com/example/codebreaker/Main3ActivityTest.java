package com.example.codebreaker;

//import android.content.Intent;

import android.app.ActivityManager;
import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;

import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.codebreaker.databinding.ActivityMainBinding;

import org.junit.Rule;
import org.junit.Test;


//import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;

//import static org.junit.Assert.*;



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
        onView(withId(R.id.taskBar1))
                .perform(click());
        ViewInteraction appCompatScroll = onView(withId(R.id.box11);
        appCompatScroll.perform(scrollTo());
//        onView(withId(R.id.box11))
//                .perform(click());
//        onView(withId(R.id.taskBar3))
//                .perform(click());
//        onView(withId(R.id.taskBar5))
//                .perform(click());
    }

    @Test
    public void dragAndDrop() {
        return;
    }
}