package com.example.codebreaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private ArrayList<android.view.View> pegList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pegList.add(findViewById(R.id.masterPeg1));
        pegList.add(findViewById(R.id.masterPeg2));
        pegList.add(findViewById(R.id.masterPeg3));
        pegList.add(findViewById(R.id.masterPeg4));

        for (android.view.View peg: pegList) {
            peg.setVisibility(View.INVISIBLE);
        }

        setContentView(R.layout.activity_main3);

        configurePushButton();
    }

    private void configurePushButton(){
        Button pushButton = findViewById(R.id.playButton);
        pushButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                for (android.view.View peg: pegList) {
                    peg.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
