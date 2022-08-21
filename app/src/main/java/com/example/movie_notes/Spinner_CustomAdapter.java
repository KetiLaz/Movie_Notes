package com.example.movie_notes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Spinner_CustomAdapter extends BaseAdapter {

    ArrayList<Category> categories;
    Context context;

    public Spinner_CustomAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView ==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_item, null);
        }

        TextView txtSpinner_id= convertView.findViewById(R.id.txtSpinner_id);
        TextView txtSpinner_title = convertView.findViewById(R.id.txtSpinner_title);

        Category category_position = categories.get(position);

        txtSpinner_id.setText(String.valueOf(category_position.getId()));
        txtSpinner_title.setText(category_position.getTitle());

        return convertView;
    }
}
