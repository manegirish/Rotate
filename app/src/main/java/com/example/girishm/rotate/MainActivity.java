package com.example.girishm.rotate;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText numberBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberBox = (TextInputEditText) findViewById(R.id.activity_main_grid_size);

        Button submitButton = (Button) findViewById(R.id.activity_main_enter_button);
        submitButton.setOnClickListener(this);
    }

    private void drawGrid(int gridSize) {
        GridView boxGrid = (GridView) findViewById(R.id.activity_main_grid_view);
        boxGrid.setNumColumns(gridSize);
        CustomGridAdapter customGrid = new CustomGridAdapter(get(gridSize), gridSize, getApplicationContext());
        boxGrid.setAdapter(customGrid);

        //get(boxGrid);
    }

    private EditText[] get(int gridSize) {
        EditText[] editTexts = new EditText[gridSize * gridSize];
        for (int i = 0; i < editTexts.length; i++) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(R.layout.grid_item_layout, null);
            editTexts[i] = (EditText) view.findViewById(R.id.grid_item_input_box);
        }
        /*
        final int size = boxGrid.getChildCount();
        Log.e(MainActivity.class.getSimpleName(), "size: " + size);
        for (int i = 0; i < size; i++) {
            ViewGroup gridChild = (ViewGroup) boxGrid.getChildAt(i);
            Log.e(MainActivity.class.getSimpleName(), "i: " + i);
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                Log.e(MainActivity.class.getSimpleName(), "k: " + k);
                EditText input = (EditText) gridChild.getChildAt(k);
                Log.e(MainActivity.class.getSimpleName(), "" + input.getText().toString());
            }
        }*/

        return editTexts;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_enter_button:
                String gridSize = numberBox.getText().toString().trim();
                if (validate(gridSize)) {
                    drawGrid(Integer.parseInt(gridSize));
                }
                break;
        }
    }
}
