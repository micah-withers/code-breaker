package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
//import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;


import com.example.codebreaker.databinding.ActivityMain3Binding;

public class Main3Activity extends AppCompatActivity {
    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.taskBar1.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar2.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar3.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar4.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar5.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar6.setOnLongClickListener(new MyLongClickListener());
        binding.box11.setOnDragListener(new MyDragListener());
        binding.box12.setOnDragListener(new MyDragListener());
        binding.box13.setOnDragListener(new MyDragListener());
        binding.box14.setOnDragListener(new MyDragListener());
    }

    private static class MyLongClickListener implements View.OnLongClickListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
            return true;
        }
    }

    private class MyDragListener implements View.OnDragListener {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();
//            Drawable original = v.getBackground();
            switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // nada
//                original = v.getBackground();
                v.setVisibility(View.VISIBLE);
//                v.setBackground(getDrawable(R.drawable.highlight));
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                // something
//                v.setBackground(getDrawable(R.drawable.highlight));
                break;
            case DragEvent.ACTION_DROP:
                v.setBackground(view.getBackground());
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                // nothing
//                v.setBackground(original);
//                if (v.getBackground() == getDrawable(R.drawable.highlight)) {
//                    v.setVisibility(View.INVISIBLE);
//                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                // idk
//                if (v.getBackground() == getDrawable(R.drawable.highlight)) {
//                    v.setVisibility(View.INVISIBLE);
//                }
            default:
                break;
            }
            return true;
        }
    }
}

