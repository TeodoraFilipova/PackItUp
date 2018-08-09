package com.example.vision_pc3.packitup.ItemList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;

public class ItemListActivity extends BaseDrawerActivity {
    public static final long ID = 3;
    private ItemListFragment mItemListFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY_NAME");

        mItemListFragment = ItemListFragment.getInstance();
        mItemListFragment.setItemsCategory(category);

        getFragmentManager().beginTransaction()
                .replace(R.id.content, mItemListFragment)
                .commit();

        setUpDrawer();
    }

    @Override
    protected long getId() {
        return ID;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}
