package com.example.vision_pc3.packitup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainListActivity extends AppCompatActivity {

    private MainListFragment mMainListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mMainListFragment = MainListFragment.getInstance();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMainListFragment)
                .commit();
    }
}
