package com.example.codebreaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.codebreaker.databinding.ActivityMain3Binding;

import java.util.ArrayList;
import java.util.Objects;

public class Main3Activity extends AppCompatActivity {

    com.example.codebreaker.databinding.ActivityMain3Binding binding;
    private ArrayList<Integer> pegCode, userCode, feedback;
    private ArrayList<View> pegList, boxes, fbList, codeBoxes;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        codeBoxes = new ArrayList<>();
        currentRow = 0;
        nRows = 10;
        boxesPerRow = 4;
        nPegs = 6;
        boxesFilled = 0;
        boxStatuses = new boolean[boxesPerRow];

        for (int i = 0; i < boxesPerRow; ++i) { //  Initializes ArrayList of user-chosen pegs
            userCode.add(-1);                   //      0:red, 1:pink, 2:orange, 3:yellow,
        }                                       //      4:green, 5:blue

        for (int i = 0; i < boxesPerRow; ++i) { //  Initializes ArrayList of feedback pegs (-1 is none, 0 is white, 1 is red)
            feedback.add(-1);
        }

        for (int i = 0; i < boxesPerRow; ++i) { //  Initializes array of box status (false:peg not set, true:peg set)
            boxStatuses[i] = false;
        }

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.board.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (dragValue) {
                    dragValue = false;
                    pegCarrier = null;
                    dropValue = false;
                    toggleHighlight();
                }
            }
        });

        bindBoxes();
        bindFeedback();
        generateCode();

        for (View v: boxes) {       //  Makes inactive boxes untouchable by user
            v.setBackground(null);
            v.setVisibility(View.GONE);
        }

        checks.add(binding.check1); // Adds check-mark views to ArrayList
        checks.add(binding.check2);
        checks.add(binding.check3);
        checks.add(binding.check4);
        checks.add(binding.check5);
        checks.add(binding.check6);
        checks.add(binding.check7);
        checks.add(binding.check8);
        checks.add(binding.check9);
        checks.add(binding.check10);

        for (Button check : checks) {   //  Makes all checks untouchable
            check.setVisibility(View.GONE);
        }

        binding.button2.setOnClickListener(new View.OnClickListener() {     //  Setup Play Again button
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main2Activity.class));
            }
        });

        setDropListeners(currentRow);
        setContentView(view);
    }

    private void bindBoxes() {      //  Adds all boxes to ArrayList (including code boxes)
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

        codeBoxes.add(binding.code1);
        codeBoxes.add(binding.code2);
        codeBoxes.add(binding.code3);
        codeBoxes.add(binding.code4);
    }

    private void bindFeedback() {   //  Adds all feedback boxes to ArrayList
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

    //  Generates 4 random integers (range 0-5) to index in pegList and add to pegCode
    //  Adds pegs to ArrayList and initializes Click Listeners
    //  Assigns colored pegs to code boxes and makes them invisible
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void generateCode() {
        pegList.add(binding.taskBar1);      //  Adds all pegs in taskbar to ArrayList
        pegList.add(binding.taskBar2);
        pegList.add(binding.taskBar3);
        pegList.add(binding.taskBar4);
        pegList.add(binding.taskBar5);
        pegList.add(binding.taskBar6);

        for (View v :                       //  Sets up Click Listeners for pegs in taskbar
                pegList) {
            v.setOnClickListener(new MyClickListener());
        }

        for (View v :                       // Sets code pegs invisible
                codeBoxes) {
            v.setVisibility(View.INVISIBLE);
        }

        for(int i = 0; i < boxesPerRow; i ++) {     //  Generates random code and sets code peg colors
            int randomNum = (int)(Math.random() *6);
            pegCode.add(randomNum);
            switch (randomNum) {
                case 0:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.red_in));
                    break;
                case 1:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.pink_in));
                    break;
                case 2:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.orange_in));
                    break;
                case 3:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.yellow_in));
                    break;
                case 4:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.green_in));
                    break;
                case 5:
                    codeBoxes.get(i).setBackground(getDrawable(R.drawable.blue_in));
                    break;
            }
        }
        for (int i = 0; i < pegCode.size(); ++i) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(pegCode.get(i));
        }
        System.out.println();
    }

    //  Returns ArrayList of feedback values (integers)
    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getFeedback() {
        return (ArrayList<Integer>) feedback.clone();
    }

    //  Returns ArrayList of peg code values (integers)
    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getPegCode() {
        return (ArrayList<Integer>) pegCode.clone();
    }

    //  Turns highlight on and off for active row
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

    //  Returns true if user code is equivalent to game code
    private boolean checkUserCode() {
        for (int i = 0; i < boxesPerRow; ++i) {
            if (!userCode.get(i).equals(pegCode.get(i))) {
                return false;
            }
        }
        return true;
    }

    //  Returns the number of instances of the passed integer value exist in the passed ArrayList
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

    //  Give appropriate feedback to user:
    //      Red: user places correct colored peg in the correct position
    //      White: user places correct colored peg in an incorrect position
    //      None: user places an incorrect colored peg (not part of the code)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getFeedback(int row) {
        int[] pegsUsed = new int[nPegs];    //  To contain number of each colored peg used
        int fbIndex = 0;
        for (int i = 0; i < nPegs; ++i) {   //  Counts number of times each peg is used
            pegsUsed[i] = count(pegCode, i);
        }
        for (int i = 0; i < boxesPerRow; ++i) { //  Resets feedback to -1 (no correct pegs)
            feedback.set(i, -1);
        }
        for (int i = 0; i < boxesPerRow; ++i) { //  Checks for correct color in correct position
            int value = pegCode.get(i);         //      subtracts 1 from the corresponding peg's
            if (value == userCode.get(i)) {     //      count in pegsUsed if correct
                feedback.set(fbIndex, 1);
                --pegsUsed[value];
                ++fbIndex;
            }
        }
        for (int i = 0; i < boxesPerRow; ++i) {                     //  Checks for correct color in incorrect position
            int value = userCode.get(i);                            //      subtracts 1 from the corresponding peg's
            if (value != pegCode.get(i) && pegsUsed[value] > 0) {   //      count in pegsUsed if correct
                feedback.set(fbIndex, 0);
                --pegsUsed[value];
                ++fbIndex;
            }
        }
        for (int i = 0; i < feedback.size(); ++i) {     //  Prints feedback results to console
            if (i>0) {
                System.out.print(", ");
            }
            System.out.print(feedback.get(i));
        }
        System.out.println();
        int startIndex = boxesPerRow*row;
        for (int i = startIndex; i < startIndex+boxesPerRow; ++i) {     //  Applies colored pegs to feedback boxes
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

    //  Activates the check button if the passed row and deactivates and removes that of the
    //      previous row. It remains invisible until all boxes in the row are filled.
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
                        for (View codeBox :
                                codeBoxes) {
                            codeBox.setVisibility(View.VISIBLE);
                        }
                        binding.button2.setVisibility(View.VISIBLE);
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

    //  Removes the Click Listener from the passed row
    private void nullCheckButton(int row) {
        Button oldCheck = checks.get(row);
        oldCheck.setOnClickListener(null);
        System.out.println("Check removed row=" + row);
    }

    //  Activates boxes of passed row and deactivates those of the previous row
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
            for (View codeBox :
                    codeBoxes) {
                codeBox.setVisibility(View.VISIBLE);
            }
            binding.button2.setVisibility(View.VISIBLE);
            System.out.println("row exceeds nRows");
            System.out.println("User failed to guess code");
        }
    }

    //  Removes Click (Drop) Listeners of passed row
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

    //  Click Listener for pegs in taskbar for user to choose from
    private class MyClickListener implements View.OnClickListener {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            pegCarrier = new DragAndDrop(pegList.indexOf(v));
            toggleHighlight();
        }
    }

    //  Makeshift class to contain a drawable as selected by user and sets the background
    //      of drop boxes when they are clicked
    private class DragAndDrop {
        private int pegIndex;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private DragAndDrop(int pegIndex) {
            dropValue = false;
            dragValue = true;

            System.out.println("DragAndDrop created");
            this.pegIndex = pegIndex;
            System.out.println("Peg selected: "+pegIndex);
        }
        private int getPegIndex() {
            return pegIndex;
        }
    }

    //  Class to listen for user clicks. Background is set to the background data member in pegCarrier
    private class MyDropListener implements View.OnClickListener {
        private boolean hasPeg = false;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            if (dragValue) {                            //  If a peg has been clicked on the taskbar:
                switch(pegCarrier.getPegIndex()) {      //  Background is set, dropped is true, drag is false
                    case 0:
                        v.setBackground(getDrawable(R.drawable.red_in));
                        break;
                    case 1:
                        v.setBackground(getDrawable(R.drawable.pink_in));
                        break;
                    case 2:
                        v.setBackground(getDrawable(R.drawable.orange_in));
                        break;
                    case 3:
                        v.setBackground(getDrawable(R.drawable.yellow_in));
                        break;
                    case 4:
                        v.setBackground(getDrawable(R.drawable.green_in));
                        break;
                    case 5:
                        v.setBackground(getDrawable(R.drawable.blue_in));
                        break;
                }
                dropValue = true;
                dragValue = false;
                boxStatuses[boxes.indexOf(v) % boxesPerRow] = true; //  Status of box in position relative to current row is true (has a peg)
                userCode.set(boxes.indexOf(v) % boxesPerRow, pegCarrier.getPegIndex());    // Adds selected peg to userCode at index of box relative to current row
                if (!hasPeg) {          //  If box did not previously hold a peg, adds to number of boxes in the row filled
                    hasPeg = true;
                    ++boxesFilled;
                    if (boxesFilled == boxesPerRow) {           //  Check box for current row is revealed if all four boxes have pegs
                        View check = checks.get(currentRow);
                        check.setVisibility(View.VISIBLE);
                    }
                }
                System.out.println("Drag drop. Boxes filled: " + boxesFilled + " Peg: " + pegCarrier.getPegIndex());
                pegCarrier = null;
            }
            else {
                System.out.println("false dragValue");
            }
            toggleHighlight();
        }
    }
}
