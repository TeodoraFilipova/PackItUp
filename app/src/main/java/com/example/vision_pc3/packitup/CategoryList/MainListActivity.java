package com.example.vision_pc3.packitup.CategoryList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vision_pc3.packitup.ItemList.ItemListActivity;
import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.Navigator;

public class MainListActivity extends AppCompatActivity implements Navigator {

    private MainListFragment mMainListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mMainListFragment = MainListFragment.getInstance();
        mMainListFragment.setNavigator(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMainListFragment)
                .commit();
    }

    @Override
    public void navigateTo(String category) {
        Intent intent = new Intent(this, ItemListActivity.class);
        intent.putExtra("CATEGORY_NAME", category);
        startActivity(intent);
    }
}
