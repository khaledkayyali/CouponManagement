package com.beans;

public enum Category {
    Breakfast(1),Dinner(2),Launch(3);

    private final int id;

    public static int getCategory(int category_id) {
        return category_id;
    }

    public int getId() {
        return id;
    }

    Category(int id) {
        this.id = id;
    }


}
