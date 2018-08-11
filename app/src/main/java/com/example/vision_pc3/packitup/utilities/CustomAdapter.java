package com.example.vision_pc3.packitup.utilities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.views.ItemList.ItemListActivity;

public class CustomAdapter extends ArrayAdapter<String> {
    private String message;

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);

        final CheckedTextView simpleCheckedTextView = view.findViewById(R.id.simpleCheckedTextView);
        simpleCheckedTextView.setText(getItem(position));

        simpleCheckedTextView.setOnLongClickListener(view1 -> {
            if (simpleCheckedTextView.isChecked()) {
                message = "Item not yet packed!";
                simpleCheckedTextView.setCheckMarkDrawable(null);
                simpleCheckedTextView.setChecked(false);
            } else {
                message = "Item packed!";
                simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                simpleCheckedTextView.setChecked(true);
            }
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            return true;
        });

        simpleCheckedTextView.setOnClickListener(view1 -> {
            String name = getItem(position);
            Intent intent = new Intent(getContext(), ItemListActivity.class);
            intent.putExtra("CATEGORY_NAME", name);
            getContext().startActivity(intent);
        });

        return view;
    }

}
