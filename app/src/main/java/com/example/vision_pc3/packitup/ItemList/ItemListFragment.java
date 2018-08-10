package com.example.vision_pc3.packitup.ItemList;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.base.FirebaseRepository;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.example.vision_pc3.packitup.utilities.CustomAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {
    static List<String> itemsToDisplay = new ArrayList<>();

    private TextView mItemsTextView;
    private String mItemsCategory;
    private ListView mItemsListView;
    private CustomAdapter mItemsAdapter;
    private FirebaseRepository<PackingItem> mPackingItemsRepository;

    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mPackingItemsRepository = new FirebaseRepository<>(PackingItem.class);

        mItemsTextView = view.findViewById(R.id.category_name);
        mItemsTextView.setText(mItemsCategory);

        mItemsListView = view.findViewById(R.id.lv_items);
        mItemsAdapter = new CustomAdapter(getContext(), -1);

        mPackingItemsRepository.getAll(items -> {
            for (PackingItem item : items) {
                if (item.getCategory().equals(mItemsCategory)) {
                    itemsToDisplay.add(item.getName());
                }
            }
            mItemsAdapter.addAll(itemsToDisplay);
        });


        mItemsListView.setAdapter(mItemsAdapter);

        return view;
    }

    public static ItemListFragment getInstance() {
        return new ItemListFragment();
    }

    public void setItemsCategory(String itemsCategory) {
        this.mItemsCategory = itemsCategory;
        if (mItemsTextView == null) {
            return;
        }
        mItemsTextView.setText(itemsCategory);
    }
}
