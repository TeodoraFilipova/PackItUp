package com.example.vision_pc3.packitup.utilities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vision_pc3.packitup.AddNewItem.AddNewItemActivity;
import com.example.vision_pc3.packitup.CategoryList.MainListActivity;
import com.example.vision_pc3.packitup.ItemList.ItemListActivity;
import com.example.vision_pc3.packitup.models.PackingItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDrawerActivity extends AppCompatActivity {

    static Map<Long, String> categoriesById = new HashMap<>();
    private static final long COUNT_START_FOR_SECONDARY_DRAWER_ID = 10;
    private FirebaseFirestore mDb;

    public void setUpDrawer() {

        Set<String> categories = getCategories();
        long initialId = COUNT_START_FOR_SECONDARY_DRAWER_ID;
        List<SecondaryDrawerItem> list = new ArrayList<>();

        for (String category : categories) {
            categoriesById.put(initialId, category);
            ++initialId;
        }

        PrimaryDrawerItem addNewItemDrawerItem = new PrimaryDrawerItem().withIdentifier(1).withName("Add New Item");
        PrimaryDrawerItem listAllDrawerItem = new PrimaryDrawerItem().withIdentifier(2).withName("Full Checklist");
        PrimaryDrawerItem listByCategoryDrawerItem = new PrimaryDrawerItem().withIdentifier(3).withName("Checklist by Category");
        SecondaryDrawerItem item1 = new SecondaryDrawerItem()
                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID)
                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID));
        SecondaryDrawerItem item2 = new SecondaryDrawerItem()
                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID + 1)
                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID + 1));


        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        addNewItemDrawerItem,
                        new DividerDrawerItem(),

                        listAllDrawerItem,
                        new DividerDrawerItem(),

                        listByCategoryDrawerItem,
                        new DividerDrawerItem(),

                        item1,
                        new DividerDrawerItem(),

                        item2
//                        new DividerDrawerItem(),
//
//                        new SecondaryDrawerItem()
//                                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID + 2)
//                                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID + 2)),
//                        new DividerDrawerItem(),
//
//                        new SecondaryDrawerItem()
//                                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID + 3)
//                                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID) + 3),
//                        new DividerDrawerItem(),
//
//                        new SecondaryDrawerItem()
//                                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID + 4)
//                                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID) + 4),
//                        new DividerDrawerItem(),
//
//                        new SecondaryDrawerItem()
//                                .withIdentifier(COUNT_START_FOR_SECONDARY_DRAWER_ID + 5)
//                                .withName(categoriesById.get(COUNT_START_FOR_SECONDARY_DRAWER_ID) + 5)
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long id = drawerItem.getIdentifier();

                    if (getId() == id) { return false; }

                    Intent intent = getNextIntent(id);

                    if (intent == null) { return false; }

                    startActivity(intent);
                    return true;
                })
                .build();

//        for (long i = COUNT_START_FOR_SECONDARY_DRAWER_ID;
//             i < COUNT_START_FOR_SECONDARY_DRAWER_ID + categoriesById.size() - 1; i++) {
//            SecondaryDrawerItem secondaryDrawerItem = new SecondaryDrawerItem()
//                    .withIdentifier(i)
//                    .withName(categoriesById.get(i));
//            list.add(secondaryDrawerItem);
//            result.addDrawerItems(secondaryDrawerItem);
//        }

//        for (String category : categories) {
//            SecondaryDrawerItem catDrawerItem = new SecondaryDrawerItem()
//                    .withIdentifier()
//                    .withName(category);
//            result.addDrawerItems(catDrawerItem);
//        }

//        for (int i = 0; i < list.size(); i++) {
//            result.addDrawerItems(list.get(i));
//        }
    }

    private Set<String> getCategories() {
        Set<String> categories = new HashSet<>();

        mDb = FirebaseFirestore.getInstance();
        mDb.collection("packingitems")
                .get()
                .addOnCompleteListener(task -> {
                    List<PackingItem> packingItems = task.getResult().toObjects(PackingItem.class);
                    for (PackingItem item : packingItems) {
                        categories.add(item.getCategory());
                    }
                });
        return categories;
    }

    protected abstract long getId();

    private Intent getNextIntent(long id) {
        if (id == MainListActivity.ID) {
            return new Intent(BaseDrawerActivity.this, MainListActivity.class);
        } else if (id == ItemListActivity.ID) {
            return null;
        } else if (id == AddNewItemActivity.ID){
            return new Intent(BaseDrawerActivity.this, AddNewItemActivity.class);
        } else {
            Intent intent = new Intent(BaseDrawerActivity.this, ItemListActivity.class);
            intent.putExtra("CATEGORY_NAME", categoriesById.get(id));
            return intent;
        }
    }

    protected abstract Toolbar getDrawerToolbar();
}
