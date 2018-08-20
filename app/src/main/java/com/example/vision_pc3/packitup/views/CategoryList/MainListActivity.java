package com.example.vision_pc3.packitup.views.CategoryList;

import android.os.Bundle;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;

public class MainListActivity extends BaseDrawerActivity {

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

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMainListFragment)
                .commit();

        setUpDrawer();
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
