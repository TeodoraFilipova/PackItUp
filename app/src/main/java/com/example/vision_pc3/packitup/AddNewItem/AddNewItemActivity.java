package com.example.vision_pc3.packitup.AddNewItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.vision_pc3.packitup.R;
import com.example.vision_pc3.packitup.utilities.BaseDrawerActivity;

public class AddNewItemActivity extends BaseDrawerActivity {
    public static final long ID = 1;
    private Toolbar mToolbar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        mToolbar = findViewById(R.id.drawer_toolbar);
        mTextView = findViewById(R.id.tv);

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
