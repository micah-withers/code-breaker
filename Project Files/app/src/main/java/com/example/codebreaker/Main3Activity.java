package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.codebreaker.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    private ArrayList<Integer> pegCode, userCode, feedback;
    private ArrayList<View> pegList, boxes, fbList;
    private ArrayList<Button> checks;
    private int currentRow, nRows, boxesFilled, boxesPerRow, nPegs;
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
        pegList = new ArrayList<>();
        pegCode = new ArrayList<>();
        userCode = new ArrayList<>();
        feedback = new ArrayList<>();
        dragValue = false;
        dropValue = false;
        boxes = new ArrayList<>();
        checks = new ArrayList<>();
        fbList = new ArrayList<>();
        currentRow = 0;
        nRows = 10;
        boxesPerRow = 4;
        nPegs = 6;
        boxesFilled = 0;
        boxStatuses = new boolean[boxesPerRow];

        for (int i = 0; i < boxesPerRow; ++i) {
            userCode.add(-1);
        }

        for (int i = 0; i < boxesPerRow; ++i) {
            feedback.add(-1);
        }

        for (int i = 0; i < boxesPerRow; ++i) {
            boxStatuses[i] = false;
        }

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.board.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                dragValue = false;
                pegCarrier = null;
                dropValue = false;
                toggleHighlight();
            }
        });

        bindBoxes();
        bindFeedback();
        generateCode();

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

    private void bindFeedback() {
        fbList.add(binding.fb11);
        fbList.add(binding.fb12);
        fbList.add(binding.fb13);
        fbList.add(binding.fb14);

        fbList.add(binding.fb21);
        fbList.add(binding.fb22);
        fbList.add(binding.fb23);
        fbList.add(binding.fb24);

        fbList.add(binding.fb31);
        fbList.add(binding.fb32);
        fbList.add(binding.fb33);
        fbList.add(binding.fb34);

        fbList.add(binding.fb41);
        fbList.add(binding.fb42);
        fbList.add(binding.fb43);
        fbList.add(binding.fb44);

        fbList.add(binding.fb51);
        fbList.add(binding.fb52);
        fbList.add(binding.fb53);
        fbList.add(binding.fb54);

        fbList.add(binding.fb61);
        fbList.add(binding.fb62);
        fbList.add(binding.fb63);
        fbList.add(binding.fb64);

        fbList.add(binding.fb71);
        fbList.add(binding.fb72);
        fbList.add(binding.fb73);
        fbList.add(binding.fb74);

        fbList.add(binding.fb81);
        fbList.add(binding.fb82);
        fbList.add(binding.fb83);
        fbList.add(binding.fb84);

        fbList.add(binding.fb91);
        fbList.add(binding.fb92);
        fbList.add(binding.fb93);
        fbList.add(binding.fb94);

        fbList.add(binding.fb101);
        fbList.add(binding.fb102);
        fbList.add(binding.fb103);
        fbList.add(binding.fb104);
    }

    private void generateCode() {
        // TODO : generate 4 random integers (0-5) to index in pegList and add to pegCode
        //peg list has all the colors, so the for loop, put i in the for loop and will be the index of the new list created
        pegList.add(binding.taskBar1);
        pegList.add(binding.taskBar2);
        pegList.add(binding.taskBar3);
        pegList.add(binding.taskBar4);
        pegList.add(binding.taskBar5);
        pegList.add(binding.taskBar6);

        for(int i = 0; i < boxesPerRow; i ++) {
//            int randomNum = (int)(Math.random() *6);
            pegCode.add((int)(Math.random() *6));
        }
        for (int i = 0; i < pegCode.size(); ++i) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(pegCode.get(i));
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getFeedback() {
        return (ArrayList<Integer>) feedback.clone();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getPegCode() {
        return (ArrayList<Integer>) pegCode.clone();
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

    private boolean checkUserCode() {
        for (int i = 0; i < boxesPerRow; ++i) {
            if (!userCode.get(i).equals(pegCode.get(i))) {
                return false;
            }
        }
        return true;
    }

    private int count(ArrayList<Integer> array, int value) {
        int count = 0;
        for (int i:
             array) {
            if (i == value) {
                ++count;
            }
        }
        return count;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getFeedback(int row) {
        int[] pegsUsed = new int[nPegs];
        int fbIndex = 0;
        for (int i = 0; i < nPegs; ++i) {
            pegsUsed[i] = count(pegCode, i);
        }
        for (int i = 0; i < boxesPerRow; ++i) {
            feedback.set(i, -1);
        }
        for (int i = 0; i < boxesPerRow; ++i) {
            int value = pegCode.get(i);
            if (value == userCode.get(i)) {
                feedback.set(fbIndex, 1);
                --pegsUsed[value];
                ++fbIndex;
            }
        }
        for (int i = 0; i < boxesPerRow; ++i) {
            int value = userCode.get(i);
            if (value != pegCode.get(i) && pegsUsed[value] > 0) {
                feedback.set(fbIndex, 0);
                --pegsUsed[value];
                ++fbIndex;
            }
        }
        for (int i = 0; i < feedback.size(); ++i) {
            if (i>0) {
                System.out.print(", ");
            }
            System.out.print(feedback.get(i));
        }
        System.out.println();
        int startIndex = boxesPerRow*row;
        for (int i = startIndex; i < startIndex+boxesPerRow; ++i) {
            switch (feedback.get(i % boxesPerRow)) {
                case 1:
                    fbList.get(i).setBackground(getDrawable(R.drawable.fb_red));
                    break;
                case 0:
                    fbList.get(i).setBackground(getDrawable(R.drawable.fb_white));
                    break;
            }
        }
    }

    private void setCheckButton(final int row) {
        if (row > 0) {
            nullCheckButton(row - 1);
        }
        final Button check = checks.get(row);
        check.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                pegCarrier = null;
                dragValue = false;
                if (boxesFilled == boxesPerRow) {
                    check.setVisibility(View.INVISIBLE);
                    getFeedback(row);
                    if (checkUserCode()) {
                        nullDropListeners(currentRow);
                        nullCheckButton(currentRow);
                        System.out.println("User guessed correctly");
                        return;
                    }
                    ++currentRow;
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
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            pegCarrier = new DragAndDrop(v.getBackground(), pegList.indexOf(v));
            toggleHighlight();
        }
    }

    private class DragAndDrop {
        private Drawable background;
        private int pegIndex;
        private DragAndDrop(Drawable background, int pegIndex) {
            dropValue = false;
            dragValue = true;
            this.background = background;
            System.out.println("DragAndDrop created");
            this.pegIndex = pegIndex;
            System.out.println("Peg selected: "+pegIndex);
        }
        private Drawable getBackground() {
            return background;
        }
        private int getPegIndex() {
            return pegIndex;
        }
    }

    private class MyDropListener implements View.OnClickListener {
        private boolean hasPeg = false;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            if (dragValue) {

                v.setBackground(pegCarrier.getBackground());
                dropValue = true;
                dragValue = false;
                boxStatuses[boxes.indexOf(v) % boxesPerRow] = true;
                userCode.set(boxes.indexOf(v) % boxesPerRow, pegCarrier.getPegIndex());    // Adds selected peg to userCode at index of box relative to current row
                if (!hasPeg) {
                    hasPeg = true;
                    ++boxesFilled;
                    if (boxesFilled == boxesPerRow) {
                        View check = checks.get(currentRow);
                        check.setVisibility(View.VISIBLE);
                    }
                }
                System.out.println("Drag drop. Boxes filled: " + boxesFilled + " Peg: " + pegCarrier.getPegIndex());
                pegCarrier = null;
            }
            else {
                toggleHighlight();
                System.out.println("false dragValue");
            }
        }
    }
}
