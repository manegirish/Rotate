package com.example.girishm.rotate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<String> inputArray;

    public GridAdapter(ArrayList<String> inputArray, Context c) {
        mContext = c;
        this.inputArray = inputArray;
    }

    @Override
    public int getCount() {
        return inputArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        View view = convertView;
        if (view == null) {
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_item_layout, parent, false);
            viewHolder.inputBox = (EditText) view.findViewById(R.id.grid_item_input_box);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.inputBox.setTag(position);
        viewHolder.inputBox.setText(inputArray.get(position));
        return view;
    }

    private class ViewHolder {
        EditText inputBox;
    }
}
