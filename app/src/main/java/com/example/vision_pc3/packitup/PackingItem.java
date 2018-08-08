package com.example.vision_pc3.packitup;

public class PackingItem {
    public String name;
    public String category;

    public PackingItem() {
        //default empty constructor
    }

    public PackingItem(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
