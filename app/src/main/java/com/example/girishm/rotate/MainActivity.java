package com.example.girishm.rotate;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText numberBox;
    private GridView boxGrid;
    private LinearLayout footerLayout;

    private int gridSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        numberBox = (TextInputEditText) findViewById(R.id.activity_main_grid_size);

        Button submitButton = (Button) findViewById(R.id.activity_main_enter_button);
        submitButton.setOnClickListener(this);

        AppCompatImageView clockWiseButton = (AppCompatImageView) findViewById(R.id.activity_main_clockwise_button);
        clockWiseButton.setOnClickListener(this);

        AppCompatImageView antiClockWiseButton = (AppCompatImageView) findViewById(R.id.activity_main_anti_clockwise_button);
        antiClockWiseButton.setOnClickListener(this);

        footerLayout = (LinearLayout) findViewById(R.id.activity_main_footer);

    }

    @Override
    protected void onResume() {
        super.onResume();
        toggleFooter();
    }

    private void toggleFooter() {
        if (gridSize <= 0) {
            footerLayout.setVisibility(View.GONE);
        } else {
            footerLayout.setVisibility(View.VISIBLE);
        }
    }

    private ArrayList<String> initGrid() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < (gridSize * gridSize); i++) {
            list.add(String.valueOf(getRandomChar()));
        }
        return list;
    }

    private char getRandomChar() {
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }

    private void drawGrid(ArrayList<String> list) {
        boxGrid = (GridView) findViewById(R.id.activity_main_grid_view);
        boxGrid.setNumColumns(gridSize);

        GridAdapter gridAdapter = new GridAdapter(list, getApplicationContext());
        boxGrid.setAdapter(gridAdapter);
    }

    public ArrayList<String> toArrayList(String[][] twoDArray) {
        ArrayList<String> list = new ArrayList<>();
        for (String[] aTwoDArray : twoDArray) {
            Collections.addAll(list, aTwoDArray);
        }
        // Log.e(MainActivity.class.getSimpleName(), "toArrayList: " + list.toString());
        return list;
    }

    private boolean validate(String gridSize) {
        if (gridSize == null || gridSize.length() <= 0) {
            numberBox.setError("Enter Valid Number");
            return false;
        }
        if (Integer.parseInt(gridSize) <= 0 || Integer.parseInt(gridSize) > 10) {
            numberBox.setError("Enter Number Between 1-10");
            return false;
        }
        return true;
    }

    private String[][] getValues() {
        int location = 0;
        String[][] inputMatrix = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            // Log.e(MainActivity.class.getSimpleName(), "i: " + i);
            for (int k = 0; k < gridSize; k++) {
                if (location < (gridSize * gridSize)) {
                    LinearLayout linearLayout = (LinearLayout) boxGrid.getChildAt(location);
                    EditText editText = (EditText) linearLayout.findViewById(R.id.grid_item_input_box);
                    inputMatrix[i][k] = editText.getText().toString();
                }
                location++;
            }
        }
        // System.out.println(Arrays.deepToString(inputMatrix));
        return inputMatrix;
    }

    public static String[][] rotateClockwise(String[][] matrix) {
        String[][] rotated = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                rotated[i][j] = matrix[matrix.length - j - 1][i];
            }
        }
        return rotated;
    }

    public static String[][] rotateAntiClockwise(String[][] matrix) {
        String[][] rotated = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                rotated[i][j] = matrix[j][matrix[0].length - i - 1];
            }
        }
        return rotated;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_enter_button:
                String size = numberBox.getText().toString().trim();
                if (validate(size)) {
                    gridSize = Integer.parseInt(size);
                    drawGrid(initGrid());
                    toggleFooter();
                }
                break;
            case R.id.activity_main_anti_clockwise_button:
                drawGrid(toArrayList(rotateAntiClockwise(getValues())));
                break;
            case R.id.activity_main_clockwise_button:
                drawGrid(toArrayList(rotateClockwise(getValues())));
                break;
        }
    }
}
