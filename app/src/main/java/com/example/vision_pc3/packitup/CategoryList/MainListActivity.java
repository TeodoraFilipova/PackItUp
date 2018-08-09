package com.example.vision_pc3.packitup.CategoryList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.vision_pc3.packitup.ItemList.ItemListActivity;
import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;
import com.example.vision_pc3.packitup.utilities.Navigator;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

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
