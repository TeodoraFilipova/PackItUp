package com.example.vision_pc3.packitup;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment {
    private FirebaseFirestore mDb;
    private ListView mItemsListView;
    private ArrayAdapter<String> mItemsAdapter;

    public MainListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        mDb = FirebaseFirestore.getInstance();

        mItemsListView = view.findViewById(R.id.lv_items);
        mItemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        mItemsListView.setAdapter(mItemsAdapter);

        mDb.collection("packingitems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<PackingItem> packingItems = task.getResult().toObjects(PackingItem.class);
                        for (PackingItem item : packingItems) {
                            mItemsAdapter.add(item.name);
                        }
                    }
                });

        return view;
    }

    public static MainListFragment getInstance() {
        return new MainListFragment();
    }
}
