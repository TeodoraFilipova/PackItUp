package com.example.vision_pc3.packitup.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.example.vision_pc3.packitup.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
//    LayoutInflater inflter;
    private String value;

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

//    public CustomAdapter(Context context, List<String> names) {
//        this.context = context;
//        this.names = names;
//        inflter = (LayoutInflater.from(context));
//    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
//        view = inflter.inflate(R.layout.list_items, parent);
        view = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);

        final CheckedTextView simpleCheckedTextView = view.findViewById(R.id.simpleCheckedTextView);
            simpleCheckedTextView.setText(getItem(position));
        simpleCheckedTextView.setOnClickListener(v -> {
            if (simpleCheckedTextView.isChecked()) {
                value = "Item not yet packed!";
                simpleCheckedTextView.setCheckMarkDrawable(null);
                simpleCheckedTextView.setChecked(false);
            } else {
                value = "Item packed!";
                simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                simpleCheckedTextView.setChecked(true);
            }
            Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}
