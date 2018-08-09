package com.example.vision_pc3.packitup.ItemList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vision_pc3.packitup.R;

public class ItemListActivity extends AppCompatActivity {

    private ItemListFragment mItemListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY_NAME");

        mItemListFragment = ItemListFragment.getInstance();
        mItemListFragment.setItemsCategory(category);

        getFragmentManager().beginTransaction()
                .replace(R.id.content, mItemListFragment)
                .commit();
    }
}
