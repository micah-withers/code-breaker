package com.example.codebreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codebreaker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        configurePushButton();
    }

    private void configurePushButton(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        Button pushButton = binding.play1Button;
        Button pushButton = findViewById(R.id.play1Button);
        pushButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });
    }
}
