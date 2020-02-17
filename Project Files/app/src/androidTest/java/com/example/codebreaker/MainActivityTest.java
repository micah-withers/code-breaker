package com.example.codebreaker;

import org.junit.Test;

//import static androidx.core.graphics.drawable.IconCompat.getResources;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.*;
//import android.view.View;


public class MainActivityTest {

    @Test
    public void onCreate() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }
}