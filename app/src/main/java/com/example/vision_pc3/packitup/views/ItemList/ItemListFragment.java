package com.example.vision_pc3.packitup.views.ItemList;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.repositories.base.FirebaseRepository;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.example.vision_pc3.packitup.utilities.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {
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
                    mItemsAdapter.add(item.getName());
                }
            }
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
