package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
//import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;


import com.example.codebreaker.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    ArrayList<View> boxes;
    ArrayList<Button> checks;
    private int currentRow, nRows, boxesFilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boxes = new ArrayList<>();
        checks = new ArrayList<>();
        currentRow = 0;
        nRows = 2;
        boxesFilled = 0;

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        boxes.add(binding.box11);
        boxes.add(binding.box12);
        boxes.add(binding.box13);
        boxes.add(binding.box14);

        boxes.add(binding.box21);
        boxes.add(binding.box22);
        boxes.add(binding.box23);
        boxes.add(binding.box24);

        checks.add(binding.check1);
        checks.add(binding.check2);

        View check;

        for (int i = 0; i < nRows; ++i) {
            check = checks.get(i);
            check.setVisibility(View.INVISIBLE);
        }

        binding.taskBar1.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar2.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar3.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar4.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar5.setOnLongClickListener(new MyLongClickListener());
        binding.taskBar6.setOnLongClickListener(new MyLongClickListener());

//        binding.check1

        nextDragListeners(currentRow);
        nextCheckButton(currentRow, boxesFilled);

        setContentView(view);
    }

    private void nextCheckButton(final int row,final int boxesFilled) {
        if (row > 0) {
            Button oldCheck = checks.get(row-1);
            oldCheck.setOnClickListener(null);
            oldCheck.setVisibility(View.GONE);
        }
        if (row < nRows) {
            Button newCheck = checks.get(row);
            newCheck.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (boxesFilled == 4) {
                        nextDragListeners(row);
                    }
                }
            });
        }
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
        private boolean hasPeg = false;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();

            switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                v.setVisibility(View.VISIBLE);
                if (!hasPeg) {
                    v.setBackground(getDrawable(R.drawable.highlight));
                }
                break;
//            case DragEvent.ACTION_DRAG_ENTERED:
//                if (!hasPeg) {
//                    v.setBackground(getDrawable(R.drawable.highlight));
//                }
//                break;
            case DragEvent.ACTION_DROP:
                v.setBackground(view.getBackground());
                if (!hasPeg) {
                    hasPeg = true;
                    ++boxesFilled;
                    if (boxesFilled == 4) {
                        View check = checks.get(currentRow);
                        check.setVisibility(View.VISIBLE);
                        ++currentRow;
                        nextCheckButton(currentRow, boxesFilled);
                        boxesFilled = 0;
                    }
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if (!hasPeg) {
                    v.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
            }
            return true;
        }
    }

    private void nextDragListeners(int row) {
        if (row > 0) {
            for (int i = row*4; i < row*4 + 4; ++i) {
                View box = boxes.get(i);
                box.setOnDragListener(null);
            }
        }
        if (row < nRows) {
            for (int i = row*4; i < row*4 + 4; ++i) {
                View box = boxes.get(i);
                box.setOnDragListener(new MyDragListener());
            }
        }
    }
}

