package com.example.vision_pc3.packitup.views.CategoryList;

import android.content.Intent;
import android.os.Bundle;

import com.example.vision_pc3.packitup.views.ItemList.ItemListActivity;
import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;
import com.example.vision_pc3.packitup.utilities.Navigator;

public class MainListActivity extends BaseDrawerActivity implements Navigator {

    public static final long ID = 2;
    private MainListFragment mMainListFragment;
    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);

        mMainListFragment = MainListFragment.getInstance();
        mMainListFragment.setNavigator(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMainListFragment)
                .commit();

        setUpDrawer();
    }

    @Override
    public void navigateTo(String category) {
        Intent intent = new Intent(this, ItemListActivity.class);
        intent.putExtra("CATEGORY_NAME", category);
        startActivity(intent);
    }

    @Override
    protected long getId() {
        return ID;
    }

    @Override
    protected android.support.v7.widget.Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}
