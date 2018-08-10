package com.example.vision_pc3.packitup.views.CategoryList;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vision_pc3.packitup.repositories.base.FirebaseRepository;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.CustomAdapter;
import com.example.vision_pc3.packitup.utilities.Navigator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment implements AdapterView.OnItemLongClickListener {
    static List<String> categoriesToDisplay = new ArrayList<>();
    static Set<String> categories = new HashSet<>();

    private ListView mCategoriesListView;
    private CustomAdapter mCategoriesAdapter;
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

        if (mCategoriesAdapter != null) {
            return null;
        }

        mCategoriesListView = view.findViewById(R.id.lv_items);
        mCategoriesAdapter = new CustomAdapter(getContext(), -1);

        mPackingItemsRepository.getAll(items -> {
            for (PackingItem item : items) {
                categories.add(item.getCategory());
            }
            categoriesToDisplay.addAll(categories);
            mCategoriesAdapter.addAll(categories);
        });

        mCategoriesListView.setAdapter(mCategoriesAdapter);
        mCategoriesListView.setOnItemLongClickListener(this);

        return view;
    }

    public static MainListFragment getInstance() {
        return new MainListFragment();
    }

    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        String category = mCategoriesAdapter.getItem(i);
        mNavigator.navigateTo(category);
        return true;
    }
}
