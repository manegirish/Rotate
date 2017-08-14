package com.example.girishm.rotate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class CustomGridAdapter extends BaseAdapter {

    private Context mContext;
    private int mNumColumns;
    private EditText[] editTexts;

    public CustomGridAdapter(EditText[] editTexts, int mNumColumns, Context c) {
        mContext = c;
        this.editTexts = editTexts;
        this.mNumColumns = mNumColumns;
    }

    @Override
    public int getCount() {
        return mNumColumns * mNumColumns;
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
        /*ViewHolder viewHolder;

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
        */
        EditText cell;
        if (editTexts[position] == null) {
            cell = editTexts[position] = new EditText(mContext);
        } else {
            cell = editTexts[position];
        }
        return cell;
    }

    private void getValue(ViewHolder viewHolder, int position) {

        viewHolder.inputBox.getText().toString();
    }

    public void getAllNumber(ViewHolder viewHolder) {
        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {

        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        EditText inputBox;
    }
}
