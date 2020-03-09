package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;


import com.example.codebreaker.databinding.ActivityMain3Binding;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.codebreaker.databinding.ActivityMain3Binding binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.taskBar1.setOnLongClickListener(createLongClickListener());
        binding.box11.setOnDragListener(new MyDragListener());
    }

    private View.OnLongClickListener createLongClickListener() {
        return new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                return true;
            }
        };
    }


    private static class MyDragListener implements View.OnDragListener {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // nada
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                // wait
                break;
            case DragEvent.ACTION_DROP:
                View view = (View) event.getLocalState();
                view.setBackground(v.getBackground());
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                // nothing
                break;
            default:
                break;
            }
            return true;
        }
    }
}

