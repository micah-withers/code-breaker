package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.codebreaker.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    ArrayList<View> boxes;
    ArrayList<Button> checks;
    private int currentRow, nRows, boxesFilled, boxesPerRow;
    private DragAndDrop pegCarrier;
    private boolean dragValue, dropValue;
    private boolean[] boxStatuses;

    public int getCurrentRow() {
        return currentRow;
    }

    public int getBoxesFilled() {
        return boxesFilled;
    }

    public boolean dragValue() {
        return dragValue;
    }

    public boolean dropValue() {
        return dropValue;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dragValue = false;
        dropValue = false;
        boxes = new ArrayList<>();
        checks = new ArrayList<>();
        currentRow = 0;
        nRows = 10;
        boxesPerRow = 4;
        boxesFilled = 0;
        boxStatuses = new boolean[boxesPerRow];

        for (boolean val : boxStatuses) {
            val = false;
        }

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragValue = false;
                pegCarrier = null;
                dropValue = false;
            }
        });

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
            check.setVisibility(View.GONE);
        }

        binding.taskBar1.setOnClickListener(new MyClickListener());
        binding.taskBar2.setOnClickListener(new MyClickListener());
        binding.taskBar3.setOnClickListener(new MyClickListener());
        binding.taskBar4.setOnClickListener(new MyClickListener());
        binding.taskBar5.setOnClickListener(new MyClickListener());
        binding.taskBar6.setOnClickListener(new MyClickListener());

        setDropListeners(currentRow);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void toggleHighlight() {
        int boxIndex = currentRow*boxesPerRow;
        for (int i = 0; i < boxesPerRow; ++i) {
            if (!boxStatuses[i]) {
                if (dragValue) {
                    boxes.get(boxIndex+i).setBackground(getDrawable(R.drawable.highlight));
                }
                else {
                    boxes.get(boxIndex+i).setBackground(null);
                }
            }
        }
    }

    private void setCheckButton(final int row) {
        if (row > 0) {
            nullCheckButton(row - 1);
        }
        final Button check = checks.get(row);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegCarrier = null;
                dragValue = false;
                if (boxesFilled == boxesPerRow) {
                    check.setVisibility(View.INVISIBLE);
                    boxesFilled = 0;
                    for (int i = 0; i < boxesPerRow; ++i) {
                        boxStatuses[i] = false;
                    }
                    setDropListeners(row+1);
                }
            }
        });
    }

    private void nullCheckButton(int row) {
        Button oldCheck = checks.get(row);
        oldCheck.setOnClickListener(null);
        System.out.println("Check removed row=" + row);
    }

    private void setDropListeners(int row) {
        if (row > 0) {
            nullDropListeners(row-1);
        }
        if (row < nRows) {
            int startIndex = row*boxesPerRow;
            for (int i = startIndex; i < startIndex+boxesPerRow; ++i) {
                View box = boxes.get(i);
                box.setOnClickListener(new MyDropListener());
//                box.setOnDragListener(new MyDragListener());
                box.setVisibility(View.VISIBLE);
            }
            System.out.println("Drag listeners set. row=" + row);
            setCheckButton(row);
        }
        else {
            System.out.println("row exceeds nRows");
        }
    }

    private void nullDropListeners(int row) {
        int startIndex = row*boxesPerRow;
        for (int i = startIndex; i < startIndex+boxesPerRow; ++i) {
            View box = boxes.get(i);
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dragValue = false;
                    pegCarrier = null;
                    dropValue = false;
                }
            });
        }
        System.out.println("Drag listeners null. row=" + row);
    }

    private class MyClickListener implements View.OnClickListener {
//        @RequiresApi(api = Build.VERSION_CODES.N)
        public void onClick(View v) {

            pegCarrier = new DragAndDrop(v, v.getBackground());
//            ClipData data = ClipData.newPlainText("", "");
//            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
//            v.startDragAndDrop(data, shadowBuilder, v, 0);
        }
    }

    private class DragAndDrop {
        private Drawable background;
        private View view;
        private DragAndDrop(View view, Drawable background) {
            dragValue = true;
            this.view = view;
            this.background = background;
            System.out.println("DragAndDrop created");
        }
        public View getView() {
            return view;
        }
        private Drawable getBackground() {
            return background;
        }
    }

    private class MyDropListener implements View.OnClickListener {


//        @RequiresApi(api = Build.VERSION_CODES.N)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void onClick(View v) {
            if (dragValue) {
                v.setBackground(pegCarrier.getBackground());
                dropValue = true;
                pegCarrier = null;
                dragValue = false;
                boxStatuses[boxes.indexOf(v) % boxesPerRow] = true;
                ++boxesFilled;
                if (boxesFilled == boxesPerRow) {
                    View check = checks.get(currentRow);
                    ++currentRow;
                    check.setVisibility(View.VISIBLE);
                }
                System.out.println("Drag drop. Boxes filled: " + boxesFilled);
            }
            else {
                System.out.println("false dragValue");
            }
//            ClipData data = ClipData.newPlainText("", "");
//            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
//            v.startDragAndDrop(data, shadowBuilder, v, 0);
        }
    }

//    private class MyDragListener implements View.OnDragListener {
//        private boolean hasPeg = false;
//
//        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public boolean onDrag(View v, DragEvent event) {
//            int action = event.getAction();
//            View view = (View) event.getLocalState();
//            Rect bounds = view.getBackground().getBounds();
//            System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
//                    " Right="+bounds.right+" Left="+bounds.left);
//
//            switch (action) {
//            case DragEvent.ACTION_DRAG_STARTED:
//                if (!hasPeg) {
//                    v.setBackground(getDrawable(R.drawable.highlight));
//                }
//                System.out.println("Drag started. hasPeg="+hasPeg);
//                break;
//            case DragEvent.ACTION_DROP:
//                v.setBackground(view.getBackground());
//                bounds = view.getBackground().getBounds();
//                System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
//                        " Right="+bounds.right+" Left="+bounds.left);
//                if (!hasPeg) {
//                    hasPeg = true;
//                    ++boxesFilled;
//                    if (boxesFilled == 4) {
//                        View check = checks.get(currentRow);
//                        ++currentRow;
//                        check.setVisibility(View.VISIBLE);
//                    }
//                    System.out.println("Drag drop. Boxes filled: " + boxesFilled);
//                }
//                break;
//            case DragEvent.ACTION_DRAG_ENDED:
//                bounds = view.getBackground().getBounds();
//                System.out.println("Top="+bounds.top+" Bottom="+bounds.bottom+
//                        " Right="+bounds.right+" Left="+bounds.left);
//                if (!hasPeg) {
//                    v.setBackground(null);
//                }
//                System.out.println("Drag ended");
//                break;
//            default:
//                break;
//            }
//            return true;
//        }
//    }
}

