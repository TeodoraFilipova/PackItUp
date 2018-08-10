package com.example.vision_pc3.packitup.AddNewItem;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.base.FirebaseRepository;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewItemFragment extends Fragment {
    static List<String> categoriesToDisplay = new ArrayList<>();
    static Set<String> categories = new HashSet<>();

    private TextView mTitleTextView;
    private TextView mItemNameTextView;
    private TextView mCategoryNameTextView;
    private AppCompatSpinner mCategorySpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private FirebaseRepository<PackingItem> mPackingItemsRepository;
    private EditText mEnterNameEditTextView;
    private Button mAddItemButton;

    public AddNewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);

        mTitleTextView = view.findViewById(R.id.tv_title);
        mItemNameTextView = view.findViewById(R.id.item_name);
        mCategoryNameTextView = view.findViewById(R.id.category_name);

        mCategorySpinner = view.findViewById(R.id.category_spinner);
        mSpinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        mCategorySpinner.setAdapter(mSpinnerAdapter);

        mPackingItemsRepository = new FirebaseRepository<>(PackingItem.class);


        mPackingItemsRepository.getAll(items -> {
            for (PackingItem item : items) {
                categories.add(item.getCategory());
            }
            categoriesToDisplay.addAll(categories);
            mSpinnerAdapter.addAll(categoriesToDisplay);
        });

        mEnterNameEditTextView = view.findViewById(R.id.name_enter);

        mAddItemButton = view.findViewById(R.id.add_item_button);
        mAddItemButton.setOnClickListener(view1 -> {
            String itemName = mEnterNameEditTextView.getText().toString();
            String itemCategory = mCategorySpinner.getSelectedItem().toString();
            mPackingItemsRepository.add
                    (new PackingItem(itemName, itemCategory),
                            packingItem ->
                                    Toast.makeText(getContext(),
                                            itemName + " added to " + itemCategory + " category!",
                                            Toast.LENGTH_LONG).show());
        });

        return view;
    }

    public static AddNewItemFragment getInstance() {
        return new AddNewItemFragment();
    }
}
