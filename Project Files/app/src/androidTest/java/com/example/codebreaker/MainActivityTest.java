package com.example.codebreaker;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;


//import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//import static org.junit.Assert.*;



public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }
}