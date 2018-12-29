package being.creature;

import File.ShowBeing;
import battlefield.BattleField;
import being.Being;
import being.item.Item;
import mainthread.MainThread;
import output.Output;
import position.Position;
import configurations.Configuration;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Creature extends Being implements Runnable{
    private int maxhealth;
    private int health;
    private int attackrate;
    private int range;
    private Thread thread;
    private BattleField bf;

    //Delegate
    private ArrayList<Creature> EnemyQueue;
    private ArrayList<Item> ItemQueue;

    @Override
    public char reportIdentifier() {
        return getIdentifier();
    }
    @Override
    public int reportHealth() {
        return getHealth();
    }
    @Override
    public int reportMaxHealth() {
        return getMaxhealth();
    }
    @Override
    public void modifyHealth(int health) {
        setHealth(health);
    }
    @Override
    public void modifyIdentifier(char identifier) {
        setIdentifier(identifier);
    }
    @Override
    public void modifyMaxHealth(int maxhealth) {
        setMaxhealth(maxhealth);
    }

    public Creature(){
        ;
    }

    public Creature(Position position, String name, char identifier, boolean faction, int health, int attackrate, int range, ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue, Image image, BattleField bf){
        super(position, name, identifier, faction, image);
        this.health=health;
        this.maxhealth = health;
        this.attackrate=attackrate;
        this.range=range;
        this.EnemyQueue = EnemyQueue;
        this.ItemQueue = ItemQueue;
        this.bf = bf;
    }

    public int getAttackrate() {
        return attackrate;
    }
    public int getHealth() {
        return health;
    }
    private void setHealth(int health){
        this.health=health;
    }
    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
    }
    private void setAttackrate(int attackrate){
        this.attackrate=attackrate;
    }
    public int getRange(){
        return range;
    }
    public int getMaxhealth(){
        return maxhealth;
    }



    public void reduceHealth(int value, String sourcename){
        if(value>health)
            value=health;
        setHealth(health-value);
        Output.outputInformation(getName()+" has been attacked by "+sourcename+", current hp is "+health);
    }
    public void increaseHealth(int value, String sourcename){
        int tmp = Math.min(health+value, maxhealth);
        setHealth(tmp);
        Output.outputInformation(getName()+" used "+sourcename+", health increased to "+health);
    }
    public void reduceAttackrate(int value, String sourcename){
        setHealth(attackrate+value);
        Output.outputInformation(getName()+" used "+sourcename+", attack rating increased to "+health);
    }
    public void increaseAttackrate(int value, String sourcename){
        int tmp=attackrate-value;
        if(tmp < Configuration.MinAttackRate)
            tmp=Configuration.MinAttackRate;
        setHealth(tmp);
        Output.outputInformation(getName()+" used "+sourcename+", attack rating decreased to "+attackrate);
    }

    private Creature withinAttackRange() {
        for(int i=0; i<EnemyQueue.size();i++){
            if(getDistanceFromOther(EnemyQueue.get(i)) <= this.getRange()){
                return EnemyQueue.get(i);
            }
        }
        return null;
    }

    public void underAttack(Being source, int value){
        reduceHealth(value, source.getName());
    }
    public void Attacking(Creature target){
        target.underAttack(this, this.getAttackrate());
    }


    private double getDistanceFromOther(Being other){

        double value=0;

        double disX = this.getPosition().getX()-other.getPosition().getX();
        double disY = this.getPosition().getY()-other.getPosition().getY();

        value = Math.sqrt(disX*disX +  disY*disY);

        return value;
    }
    private Position seekNextPosition() {
        if(getPosition().getX()==-1 && getPosition().getY()==-1){
            return null;
        }
        if (EnemyQueue.isEmpty()) {
            return null;
        } else {
            Position nearestposition = null;
            Position nextposition = new Position(this.getPosition());
            if (!ItemQueue.isEmpty() && health < maxhealth / 2) {
                Item nearest = ItemQueue.get(0);
                for(int i=1; i<ItemQueue.size();i++){
                    if(getDistanceFromOther(nearest) > getDistanceFromOther(ItemQueue.get(i))){
                        nearest = ItemQueue.get(i);
                    }
                }
                nearestposition = nearest.getPosition();
            } else if(!EnemyQueue.isEmpty() && health >= maxhealth / 2){
                Creature nearest = EnemyQueue.get(0);
                for (int i = 1; i < EnemyQueue.size(); i++) {
                    if (getDistanceFromOther(nearest) > getDistanceFromOther(EnemyQueue.get(i))) {
                        nearest = EnemyQueue.get(i);
                    }
                }
                nearestposition = nearest.getPosition();
            }
            else
                return null;
            if(nearestposition.getX() > this.getPosition().getX() && nearestposition.getY() > this.getPosition().getY()){
                nextposition.setX(nextposition.getX()+1);
                if(!bf.isLocationEmpty(nextposition)){
                    nextposition.setX(nextposition.getX()-1);
                    nextposition.setY(nextposition.getY()+1);
                }
            }
            else if(nearestposition.getX() == this.getPosition().getX() && nearestposition.getY() > this.getPosition().getY()){
                //nextposition.setX(nextposition.getX()+1);
                nextposition.setY(nextposition.getY()+1);
            }
            else if(nearestposition.getX() > this.getPosition().getX() && nearestposition.getY() == this.getPosition().getY()){
                nextposition.setX(nextposition.getX()+1);
                //nextposition.setY(nextposition.getY()+1);
            }
            else if(nearestposition.getX() < this.getPosition().getX() && nearestposition.getY() < this.getPosition().getY()){
                nextposition.setX(nextposition.getX()-1);
                if(!bf.isLocationEmpty(nextposition)){
                    nextposition.setX(nextposition.getX()+1);
                    nextposition.setY(nextposition.getY()-1);
                }
            }
            else if(nearestposition.getX() < this.getPosition().getX() && nearestposition.getY() == this.getPosition().getY()){
                nextposition.setX(nextposition.getX()-1);
                //nextposition.setY(nextposition.getY()+1);
            }
            else if(nearestposition.getX() == this.getPosition().getX() && nearestposition.getY() < this.getPosition().getY()){
                //nextposition.setX(nextposition.getX()+1);
                nextposition.setY(nextposition.getY()-1);
            }
            else if(nearestposition.getX() > this.getPosition().getX() && nearestposition.getY() < this.getPosition().getY()){
                nextposition.setX(nextposition.getX()+1);
                if(!bf.isLocationEmpty(nextposition)){
                    nextposition.setX(nextposition.getX()-1);
                    nextposition.setY(nextposition.getY()-1);
                }
            }
            else if(nearestposition.getX() < this.getPosition().getX() && nearestposition.getY() > this.getPosition().getY()) {
                nextposition.setX(nextposition.getX() - 1);
                if(!bf.isLocationEmpty(nextposition)){
                    nextposition.setX(nextposition.getX()+1);
                    nextposition.setY(nextposition.getY() + 1);
                }
            }
            else
                return null;
            //System.out.println("The Next Position is :("+nextposition.getX()+","+nextposition.getY()+").");
            return nextposition;
        }
    }

    public void Moving(){
        Position nextposition = seekNextPosition();
        if(nextposition!=null) {
            if (bf.isHolderPositionEmpty(nextposition)) {
                if (bf.getHolder()[nextposition.getX()][nextposition.getY()] instanceof Item) {
                    ((Item) bf.getHolder()[nextposition.getX()][nextposition.getY()]).beUsed(this);
                }
                bf.setCreatureToProperLocation(this, nextposition);
            }
        }
    }
    public boolean isDead(){
        return this.health <= 0;
    }
    public void removeFromAliveQueue(){
        bf.removeFromQueue(this);
    }

    public void run(){
        while(!this.isDead() && MainThread.getIsRunning()) {
            //if(this.getPosition().getY()==-1 && this.getPosition().getX()==-1){
            //    break;
            //}
            //TODO: Implement the Thread!
            synchronized (bf) {
                Creature target = withinAttackRange();
                if (target == null) {
                    Moving();
                } else {
                    Attacking(target);
                    Moving();
                }
            }
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        if(this.isDead()) {
            Position tmp = new Position(this.getPosition());
            synchronized (bf) {
                this.removeFromAliveQueue();
                Item item = new Item(tmp, this.getFaction(), this.bf);
            }
        }
        System.out.println("Creature Leave Thread!!");
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, "thread" + this.getName());
            thread.start();
        }
    }
}
