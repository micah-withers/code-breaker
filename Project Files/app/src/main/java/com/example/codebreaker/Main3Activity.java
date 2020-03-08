package com.example.codebreaker;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.codebreaker.databinding.ActivityMain3Binding;
//import android.view.View;
//import android.widget.Button;

//import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }



}
