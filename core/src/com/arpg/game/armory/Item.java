package com.arpg.game.armory;

public interface Item {
    enum Type {
        POTION, GOLD, WEAPON, ARMOR, BAG
    }

    Type getItemType();
    String getTitle();
    boolean isUsable();
    boolean isWearable();
    boolean isStackable();
}
