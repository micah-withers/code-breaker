package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        binding.taskBar1.setOnLongClickListener(new MyLongClickListener());
        binding.box11.setOnDragListener(new MyDragListener());
    }

    private class MyLongClickListener implements View.OnLongClickListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
            return true;
        }
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

