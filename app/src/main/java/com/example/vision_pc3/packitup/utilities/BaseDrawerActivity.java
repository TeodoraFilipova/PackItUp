package com.example.vision_pc3.packitup.utilities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vision_pc3.packitup.views.AddNewItem.AddNewItemActivity;
import com.example.vision_pc3.packitup.views.CategoryList.MainListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public abstract class BaseDrawerActivity extends AppCompatActivity {

    public void setUpDrawer() {
        PrimaryDrawerItem addNewItemDrawerItem = new PrimaryDrawerItem()
                .withIdentifier(1).withName("Add New Item");
        PrimaryDrawerItem listAllDrawerItem = new PrimaryDrawerItem()
                .withIdentifier(2).withName("Full Checklist");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        addNewItemDrawerItem,

                        new DividerDrawerItem(),

                        listAllDrawerItem)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long id = drawerItem.getIdentifier();

                    if (getId() == id) { return false; }

                    Intent intent = getNextIntent(id);

                    if (intent == null) { return false; }

                    startActivity(intent);
                    return true;
                })
                .build();
    }

    protected abstract long getId();

    private Intent getNextIntent(long id) {
        if (id == MainListActivity.ID) {
            return new Intent(BaseDrawerActivity.this, MainListActivity.class);
        } else if (id == AddNewItemActivity.ID){
            return new Intent(BaseDrawerActivity.this, AddNewItemActivity.class);
        }
        return null;
    }

    protected abstract Toolbar getDrawerToolbar();
}
