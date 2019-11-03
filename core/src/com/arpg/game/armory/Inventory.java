package com.arpg.game.armory;

import com.arpg.game.units.Hero;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public class InventoryEntry {
        private Item item;
        private int quantity;

        public Item getItem() {
            return item;
        }

        public InventoryEntry(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }

    private Hero hero;
    private List<InventoryEntry> items;
    private int coins;
    private int selected;

    ////////////////////////////////////////////////////
    //2. размер рюкзака по умолчанию
    private int bagSize;
    private final int maxBagSize = 27;

    public int getBagSize(){
        return this.bagSize;
    }

    public int getMaxBagSize(){
        return this.maxBagSize;
    }

    public void addSizeBag(int addSize){
        if ((this.bagSize + addSize) <= maxBagSize) {
            this.bagSize += addSize;
        }
    }
    //
    ////////////////////////////////////////////////////

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void selectPrev() {
        selected--;
        if (selected < 0) {
            selected = items.size() - 1;
        }
    }

    public void selectNext() {
        selected++;
        if (selected >= items.size()) {
            selected = 0;
        }
    }

    public InventoryEntry getCurrentInventoryEntry() {
        return items.get(selected);
    }

    public void destroyCurrentItem() {
        if (!items.get(selected).item.isStackable()) {
            items.remove(selected);
        } else {
            items.get(selected).quantity--;
            if (items.get(selected).quantity <= 0) {
                items.remove(selected);
            }
        }
        if (selected >= items.size()) {
            selected = 0;
        }
    }

    public void takeCurrentWeapon() {
        if (!(getCurrentInventoryEntry().item instanceof Weapon)) {
            return;
        }
        Weapon tmp = hero.getWeapon();
        hero.setWeapon((Weapon) getCurrentInventoryEntry().item);
        items.get(selected).item = tmp;
    }

    public Inventory(Hero hero) {
        this.hero = hero;
        this.items = new ArrayList<>();
        this.bagSize = 9;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i == selected) {
                builder.append("> ");
            }
            builder.append(items.get(i).item.getTitle()).append("...").append(items.get(i).quantity).append("\n");
        }
        font.draw(batch, builder, 1000, 700);
    }

    public void setBagSize(int delta){
        this.bagSize += delta;
    }

    public void add(Item item) {
        if (!item.isStackable()) {
            /////////////////////////////
            //2.
            if( (this.bagSize + items.size()) <= this.maxBagSize) {
                items.add(new InventoryEntry(item, 1));
            }
        } else {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).item.getTitle().equals(item.getTitle())) {
                    items.get(i).quantity++;
                    return;
                }
            }
            //2.
            if (items.size() <= this.bagSize) {
                items.add(new InventoryEntry(item, 1));
            }
        }
    }
}
