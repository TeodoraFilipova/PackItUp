package com.example.vision_pc3.packitup.views.AddNewItem;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;

public class AddNewItemActivity extends BaseDrawerActivity {
    public static final long ID = 1;
    private Toolbar mToolbar;
    private AddNewItemFragment mAddNewItemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);

        mAddNewItemFragment = AddNewItemFragment.getInstance();

        getFragmentManager().beginTransaction()
                .replace(R.id.content, mAddNewItemFragment)
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
