package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
//import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;


import com.example.codebreaker.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private String pegList[] = {"red", "pink", "orange", "yellow", "green", "blue"};
    private ArrayList<String> pegCode = new ArrayList<String>();

    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    ArrayList<View> boxes;
    ArrayList<Button> checks;
    private int currentRow, nRows, boxesFilled;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boxes = new ArrayList<>();
        checks = new ArrayList<>();
        currentRow = 0;
        nRows = 10;
        boxesFilled = 0;

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        bindBoxes();

        for (View v: boxes) {
            v.setBackground(null);
            v.setVisibility(View.GONE);
        }

        checks.add(binding.check1);
        checks.add(binding.check2);
        checks.add(binding.check3);
        checks.add(binding.check4);
        checks.add(binding.check5);
        checks.add(binding.check6);
        checks.add(binding.check7);
        checks.add(binding.check8);
        checks.add(binding.check9);
        checks.add(binding.check10);

        for (Button check : checks) {
            check.setVisibility(View.INVISIBLE);
        }

        binding.taskBar1.setOnClickListener(new MyClickListener());
        binding.taskBar2.setOnClickListener(new MyClickListener());
        binding.taskBar3.setOnClickListener(new MyClickListener());
        binding.taskBar4.setOnClickListener(new MyClickListener());
        binding.taskBar5.setOnClickListener(new MyClickListener());
        binding.taskBar6.setOnClickListener(new MyClickListener());

        setDragListeners(currentRow);
        setContentView(view);
    }

    private void bindBoxes() {
        boxes.add(binding.box11);
        boxes.add(binding.box12);
        boxes.add(binding.box13);
        boxes.add(binding.box14);

        boxes.add(binding.box21);
        boxes.add(binding.box22);
        boxes.add(binding.box23);
        boxes.add(binding.box24);

        boxes.add(binding.box31);
        boxes.add(binding.box32);
        boxes.add(binding.box33);
        boxes.add(binding.box34);

        boxes.add(binding.box41);
        boxes.add(binding.box42);
        boxes.add(binding.box43);
        boxes.add(binding.box44);

        boxes.add(binding.box51);
        boxes.add(binding.box52);
        boxes.add(binding.box53);
        boxes.add(binding.box54);

        boxes.add(binding.box61);
        boxes.add(binding.box62);
        boxes.add(binding.box63);
        boxes.add(binding.box64);

        boxes.add(binding.box71);
        boxes.add(binding.box72);
        boxes.add(binding.box73);
        boxes.add(binding.box74);

        boxes.add(binding.box81);
        boxes.add(binding.box82);
        boxes.add(binding.box83);
        boxes.add(binding.box84);

        boxes.add(binding.box91);
        boxes.add(binding.box92);
        boxes.add(binding.box93);
        boxes.add(binding.box94);

        boxes.add(binding.box101);
        boxes.add(binding.box102);
        boxes.add(binding.box103);
        boxes.add(binding.box104);
    }

    private void setCheckButton(final int row) {
        if (row > 0) {
            nullCheckButton(row - 1);
        }
        final Button check = checks.get(row);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxesFilled == 4) {
                    check.setVisibility(View.INVISIBLE);
                    boxesFilled = 0;
                    setDragListeners(row+1);
                }
            }
        });
    }

    void generateCode() {
        // TODO : generate 4 random integers (0-5) to index in pegList and add to pegCode
        function() {
            pegCode.add(pegList[integer]);
            System.out.println(pegCode[i]);
        }
    }

    private void nullCheckButton(int row) {
        Button oldCheck = checks.get(row);
        oldCheck.setOnClickListener(null);
        System.out.println("Check removed row=" + row);
    }

    private void setDragListeners(int row) {
        if (row > 0) {
            nullDragListeners(row-1);
        }
        if (row < nRows) {
            int startIndex = row*4;
            for (int i = startIndex; i < startIndex+4; ++i) {
                View box = boxes.get(i);
                box.setOnDragListener(new MyDragListener());
                box.setVisibility(View.VISIBLE);
            }
            System.out.println("Drag listeners set. row=" + row);
            setCheckButton(row);
        }
        else {
            System.out.println("row exceeds nRows");
        }
    }

    private void nullDragListeners(int row) {
        int startIndex = row*4;
        for (int i = startIndex; i < startIndex+4; ++i) {
            View box = boxes.get(i);
            box.setOnDragListener(null);
        }
        System.out.println("Drag listeners null. row=" + row);
    }

    private static class MyClickListener implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void onClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadowBuilder, v, 0);
        }
    }

    private class MyDragListener implements View.OnDragListener {
        private boolean hasPeg = false;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();
            Rect bounds = view.getBackground().getBounds();
            System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
                    " Right="+bounds.right+" Left="+bounds.left);

            switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (!hasPeg) {
                    v.setBackground(getDrawable(R.drawable.highlight));
                }
                System.out.println("Drag started. hasPeg="+hasPeg);
                break;
            case DragEvent.ACTION_DROP:
                v.setBackground(view.getBackground());
                bounds = view.getBackground().getBounds();
                System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
                        " Right="+bounds.right+" Left="+bounds.left);
                if (!hasPeg) {
                    hasPeg = true;
                    ++boxesFilled;
                    if (boxesFilled == 4) {
                        View check = checks.get(currentRow);
                        ++currentRow;
                        check.setVisibility(View.VISIBLE);
                    }
                    System.out.println("Drag drop. Boxes filled: " + boxesFilled);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                bounds = view.getBackground().getBounds();
                System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
                        " Right="+bounds.right+" Left="+bounds.left);
                if (!hasPeg) {
                    v.setBackground(null);
                }
                System.out.println("Drag ended");
                break;
            default:
                break;
            }
            return true;
        }
    }
}
