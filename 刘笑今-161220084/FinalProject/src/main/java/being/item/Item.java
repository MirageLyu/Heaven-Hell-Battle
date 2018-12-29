package being.item;

import battlefield.BattleField;
import being.Being;
import being.creature.Creature;
import being.creature.dictionary.IdentifierDict;
import being.creature.dictionary.PictureDict;
import configurations.Configuration;
import position.Position;

public class Item extends Being {
    //true: health, false:attack
    private boolean whichvalue;
    private int value;
    private BattleField BfDelegate;

    public Item(){
        ;
    }

    @Override
    public char reportIdentifier() {
        return getIdentifier();
    }

    @Override
    public int reportMaxHealth() {
        System.out.println("Item Cannot report health");
        return 0;
    }

    @Override
    public int reportHealth() {
        System.out.println("Item Cannot report health");
        return 0;
    }

    @Override
    public void modifyMaxHealth(int maxhealth) {
        return;
    }

    @Override
    public void modifyIdentifier(char identifier) {
        setIdentifier(identifier);
    }

    @Override
    public void modifyHealth(int health) {
        return;
    }


    public Item(Position position, boolean faction, BattleField bf){
        super(position, "Item", IdentifierDict.getItemIdentifier(faction), faction, PictureDict.switchIdentifierToImage(IdentifierDict.getItemIdentifier(faction)));

        whichvalue = valueWhichValue();
        if(whichvalue){
            value = Configuration.ItemHealthValue;
        }
        else{
            value = Configuration.ItemAttackValue;
        }

        BfDelegate = bf;

        bf.setBeingToProperLocation(this, position);
        bf.addToItemQueue(this);
    }

    //Central Method
    public void beUsed(Creature creature){
        if(this.getFaction() == creature.getFaction()){
            if(whichvalue){
                creature.increaseHealth(this.getValue(), this.getName());
            }
            else{
                creature.increaseAttackrate(this.getValue(), this.getName());
            }
        }else{
            if(whichvalue){
                creature.reduceHealth(this.getValue(), this.getName());
            }
            else{
                creature.reduceAttackrate(this.getValue(), this.getName());
            }
        }
        BfDelegate.removeFromItemQueue(this);
    }

    public int getValue() {
        return value;
    }

    public boolean getWhichvalue() {
        return whichvalue;
    }

    public static boolean valueWhichValue(){
        int random = (int)(1+Math.random()*(10));
        if(random<=5)
            return true;
        return false;
    }
}
