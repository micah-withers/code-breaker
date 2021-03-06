package com.example.codebreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codebreaker.databinding.ActivityMain2Binding;


public class Main2Activity extends AppCompatActivity {
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        configurePushButton();
    }
    private void configurePushButton(){
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        Button pushButton = findViewById(R.id.button);
        pushButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
            }
        });
    }
}
