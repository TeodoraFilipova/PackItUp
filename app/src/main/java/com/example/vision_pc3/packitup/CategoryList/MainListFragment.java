package com.example.vision_pc3.packitup.CategoryList;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vision_pc3.packitup.base.FirebaseRepository;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.Navigator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mCategoriesListView;
    private ArrayAdapter<String> mCategoriesAdapter;
    private Navigator mNavigator;
    private FirebaseRepository<PackingItem> mPackingItemsRepository;

    public MainListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        mPackingItemsRepository = new FirebaseRepository<>(PackingItem.class);

        mCategoriesListView = view.findViewById(R.id.lv_items);
        mCategoriesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        mCategoriesListView.setAdapter(mCategoriesAdapter);
        mCategoriesListView.setOnItemClickListener(this);

        mPackingItemsRepository.getAll(packingItems -> {
            Set<String> categories = new HashSet<>();
            for (PackingItem item : packingItems) {
                categories.add(item.getCategory());
            }
            mCategoriesAdapter.addAll(categories);
        });

        return view;
    }

    public static MainListFragment getInstance() {
        return new MainListFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String category = mCategoriesAdapter.getItem(i);
        mNavigator.navigateTo(category);
    }

    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }
}
