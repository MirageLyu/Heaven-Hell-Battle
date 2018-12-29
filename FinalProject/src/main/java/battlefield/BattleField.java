package battlefield;

import being.Being;
import being.creature.Creature;
import being.item.Item;
import output.Output;
import position.Position;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleField {
    private final int height = 10;
    private final int width = 20;
    private Being[][] holder;
    public ArrayList<Creature> AliveJusticeQueue;
    public ArrayList<Creature> AliveEvilQueue;
    public ArrayList<Item> ItemsQueue;

    public BattleField(){
        holder = new Being[height][width];
        AliveJusticeQueue = new ArrayList<Creature>();
        AliveEvilQueue = new ArrayList<Creature>();
        ItemsQueue = new ArrayList<Item>();
    }

    public Being[][] getHolder(){
        return holder;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    private void addToEvilQueue(Creature creature){
        AliveEvilQueue.add(creature);
    }
    private void addToEvilQueue(Creature[] creatures){
        AliveEvilQueue.addAll(Arrays.asList(creatures));
    }
    private void clearEvilQueue(){
        AliveEvilQueue.clear();
    }

    public void addToItemQueue(Item item){
        ItemsQueue.add(item);
    }
    public void addToItemQueue(Item[] items){
        ItemsQueue.addAll(Arrays.asList(items));
    }
    public void clearItemQueue(){
        ItemsQueue.clear();
    }

    public void removeFromQueue(Creature creature){
        if(creature.getFaction()){
            for(int i=0; i<AliveJusticeQueue.size(); i++){
                if(AliveJusticeQueue.get(i) == creature){
                    AliveJusticeQueue.remove(i);
                    moveBeingOutOfBattleField(creature);
                }
            }
        }
        else{
            for(int i=0; i<AliveEvilQueue.size(); i++){
                if(AliveEvilQueue.get(i) == creature){
                    AliveEvilQueue.remove(i);
                    moveBeingOutOfBattleField(creature);
                }
            }
        }
    }
    public void removeFromItemQueue(Item item){
        for(int i=0; i<ItemsQueue.size(); i++){
            if(ItemsQueue.get(i) == item){
                ItemsQueue.remove(i);
                moveBeingOutOfBattleField(item);
            }
        }
    }

    private void addToJusticeQueue(Creature creature){
        AliveJusticeQueue.add(creature);
    }
    private void addToJusticeQueue(Creature[] creatures){
        AliveJusticeQueue.addAll(Arrays.asList(creatures));
    }
    private void clearJusticeQueue(){
        AliveJusticeQueue.clear();
    }

    public void printBattleFieldStatus(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(isLocationEmpty(new Position(i, j))){
                    System.out.print("- ");
                }
                else
                    System.out.print(holder[i][j].getIdentifier() + " ");
            }
            System.out.println();
        }
    }
    public void printCreaturesStatus(){
        System.out.println("Justice:");
        for(int i=0; i<AliveJusticeQueue.size(); i++){
            System.out.println(AliveJusticeQueue.get(i).getName() + "-- Health: " +AliveJusticeQueue.get(i).getHealth() + "/"+AliveJusticeQueue.get(i).getMaxhealth());
        }
        System.out.println("Evil:");
        for(int i=0; i<AliveEvilQueue.size(); i++){
            System.out.println(AliveEvilQueue.get(i).getName() + "-- Health: " +AliveEvilQueue.get(i).getHealth() + "/" + AliveEvilQueue.get(i).getMaxhealth());
        }
    }

    private boolean checkLocationOutOfBound(Being being){
        if(Position.isInitialPosition(being.getPosition())){
            Output.outputAtInitialPositionMessage(being.getName());
            return false;
        }
        else if(!Position.outOfBoundChecker(being.getPosition(), new Position(0,0), new Position(height-1, width-1))){
            Output.outputOutOfBoundMessage(being.getName());
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isLocationEmpty(Position position){
        return holder[position.getX()][position.getY()] == null;
    }
    private void moveBeingOutOfBattleField(Being being){
        //The order of following two instructions MUST be like this.
        //"ct" can be reference of creature[][], and also can be "enumeration" in main.
        if(being != null && !Position.isInitialPosition(being.getPosition())) {
            int x=being.getPosition().getX();
            int y=being.getPosition().getY();
            being.moveToPosition(new Position(-1, -1));
            holder[x][y] = null;
        }
    }
    public void clearBattleField(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                moveBeingOutOfBattleField(holder[i][j]);
            }
        }
    }
    public void clearLeftBattleField(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width/2; j++){
                moveBeingOutOfBattleField(holder[i][j]);
            }
        }
    }
    public void clearRightBattleField(){
        for(int i=0; i<height; i++){
            for(int j=width/2+1; j<width; j++){
                moveBeingOutOfBattleField(holder[i][j]);
            }
        }
    }

    public boolean isHolderPositionEmpty(Position position){
        if(holder[position.getX()][position.getY()] instanceof Item)
            return true;
        return holder[position.getX()][position.getY()] == null;
    }

    public void removeBeingsFromBattleField(ArrayList<Being> beings){
        for(int i=0; i<beings.size(); i++)
            moveBeingOutOfBattleField(beings.get(i));
    }
    public void removeBeingFromBattleField(Being being){
        moveBeingOutOfBattleField(being);
    }
    public void setBeingToProperLocation(Being being, Position position){
        if(!Position.outOfBoundChecker(position, new Position(0,0), new Position(height-1, width-1))){
            moveBeingOutOfBattleField(being);
            moveBeingOutOfBattleField(holder[position.getX()][position.getY()]);
            holder[position.getX()][position.getY()]=being;
            being.moveToPosition(position);
        }
    }
    public void setCreatureToProperLocation(Creature ct, Position position){
        setBeingToProperLocation(ct, position);
    }
    private void setCreatureToProperLocation(Creature ct, int x, int y){
        setCreatureToProperLocation(ct, new Position(x, y));
    }

    public void setCheeringCreatureLocation(Creature ct, boolean onright){
        int random = (int)(1+Math.random()*(10));
        if(onright){
            if(random<=5){
                setBeingToProperLocation(ct, new Position(0, 3*width/4));
            }
            else{
                setBeingToProperLocation(ct, new Position(height-1, 3*width/4));
            }
        }
        else{
            if(random<=5){
                setBeingToProperLocation(ct, new Position(0, width/4));
            }
            else{
                setBeingToProperLocation(ct, new Position(height-1, width/4));
            }
        }
    }

    //onright==true: deploy the force in the right part of the battlefield.
    private  Position getLeaderLocation(boolean onright){
        Position position = new Position();
        if(onright){
            position.setY(width/2+2);
            position.setX(height/2);
        }
        else{
            position.setY(width/2-2);
            position.setX(height/2);
        }
        return position;
    }

    boolean isPeopleNumberLessThanDeployLowerLimit(int limit, Creature[] member){
        if(member.length<limit){
            System.out.println("DeployLessLimitRepot: member[] number less than lower limitation of the deployment.");
            return true;
        }
        return false;
    }
    //Followings are Deployments
    private void initialQueues(Creature leader, Creature[] member, boolean onright){
        if(onright){
            clearEvilQueue();
            addToEvilQueue(leader);
            addToEvilQueue(member);
        }
        else{
            clearJusticeQueue();
            addToJusticeQueue(leader);
            addToJusticeQueue(member);
        }
    }
    public void deployHeYiZhen(Creature leader, Creature[] member, boolean onright) {
        initialQueues(leader, member, onright);
        Position position = getLeaderLocation(onright);
        setBeingToProperLocation(leader, position);
        int x=position.getX();
        int y=position.getY();
        if(onright){
            for(int i=0; i<3; i++){
                setBeingToProperLocation(member[2*i], new Position(x-i-1, y+i+1));
                setBeingToProperLocation(member[2*i+1], new Position(x+i+1, y+i+1));
            }
        }
        else{
            for(int i=0; i<3; i++){
                setBeingToProperLocation(member[2*i], new Position(x-i-1, y-i-1));
                setBeingToProperLocation(member[2*i+1], new Position(x+i+1, y-i-1));
            }
        }
    }
    public void deployYanXingZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position = getLeaderLocation(onright);
        int x = position.getX(), y=position.getY();
        setBeingToProperLocation(leader, position);
        if(onright){
            for(int i=0;i<4;i++){
                setBeingToProperLocation(member[i], new Position(x-1-i, y+1+i));
            }
        }
        else{
            for(int i=0;i<4;i++){
                setBeingToProperLocation(member[i], new Position(x+1+i, y-1-i));
            }
        }
    }
    public void deployHengEZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position = getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], new Position(x-2, y));
            setCreatureToProperLocation(member[i++], x+2, y);
            setCreatureToProperLocation(member[i++], x-3, y+1);
            setCreatureToProperLocation(member[i++], x-1, y+1);
            setCreatureToProperLocation(member[i++], x+1, y+1);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x-2, y);
            setCreatureToProperLocation(member[i++], x+2, y);
            setCreatureToProperLocation(member[i++], x+3, y-1);
            setCreatureToProperLocation(member[i++], x-1, y-1);
            setCreatureToProperLocation(member[i++], x+1, y-1);
        }
    }
    public void deployChangSheZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position = getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], x+1, y);
            setCreatureToProperLocation(member[i++], x+2, y);
            setCreatureToProperLocation(member[i++], x+3, y);
            setCreatureToProperLocation(member[i++], x-1, y);
            setCreatureToProperLocation(member[i++], x-2, y);
            setCreatureToProperLocation(member[i++], x-3, y);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x+1, y);
            setCreatureToProperLocation(member[i++], x+2, y);
            setCreatureToProperLocation(member[i++], x-3, y);
            setCreatureToProperLocation(member[i++], x-1, y);
            setCreatureToProperLocation(member[i++], x-2, y);
            setCreatureToProperLocation(member[i++], x+3, y);
        }
    }
    public void deployYuLinZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position=getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], x, y+2);
            setCreatureToProperLocation(member[i++], x, y+4);
            setCreatureToProperLocation(member[i++], x, y+6);
            setCreatureToProperLocation(member[i++], x-1, y+1);
            setCreatureToProperLocation(member[i++], x-1, y+3);
            setCreatureToProperLocation(member[i++], x-1, y+5);
            setCreatureToProperLocation(member[i++], x+1, y+3);
            setCreatureToProperLocation(member[i++], x-2, y+4);
            setCreatureToProperLocation(member[i++], x-3, y+3);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x, y-2);
            setCreatureToProperLocation(member[i++], x, y-4);
            setCreatureToProperLocation(member[i++], x, y-6);
            setCreatureToProperLocation(member[i++], x-1, y-1);
            setCreatureToProperLocation(member[i++], x-1, y-3);
            setCreatureToProperLocation(member[i++], x-1, y-5);
            setCreatureToProperLocation(member[i++], x+1, y-3);
            setCreatureToProperLocation(member[i++], x-2, y-4);
            setCreatureToProperLocation(member[i++], x-3, y-3);
        }
    }
    public void deployFangYuanZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position=getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], x+1, y+1);
            setCreatureToProperLocation(member[i++], x+2, y+2);
            setCreatureToProperLocation(member[i++], x-1, y+1);
            setCreatureToProperLocation(member[i++], x-2, y+2);
            setCreatureToProperLocation(member[i++], x-1, y+3);
            setCreatureToProperLocation(member[i++], x+1, y+3);
            setCreatureToProperLocation(member[i++], x, y+4);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x+1, y-1);
            setCreatureToProperLocation(member[i++], x+2, y-2);
            setCreatureToProperLocation(member[i++], x-1, y-1);
            setCreatureToProperLocation(member[i++], x-2, y-2);
            setCreatureToProperLocation(member[i++], x-1, y-3);
            setCreatureToProperLocation(member[i++], x+1, y-3);
            setCreatureToProperLocation(member[i++], x, y-4);
        }
    }
    public void deployYanYueZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position=getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], x, y+1);
            setCreatureToProperLocation(member[i++], x, y+2);
            setCreatureToProperLocation(member[i++], x+1, y);
            setCreatureToProperLocation(member[i++], x+1, y+1);
            setCreatureToProperLocation(member[i++], x+1, y+3);
            setCreatureToProperLocation(member[i++], x-1, y);
            setCreatureToProperLocation(member[i++], x-1, y+1);
            setCreatureToProperLocation(member[i++], x-1, y+3);
            setCreatureToProperLocation(member[i++], x-2, y+2);
            setCreatureToProperLocation(member[i++], x-2, y+4);
            setCreatureToProperLocation(member[i++], x+2, y+4);
            setCreatureToProperLocation(member[i++], x+2, y+4);
            setCreatureToProperLocation(member[i++], x-3, y+4);
            setCreatureToProperLocation(member[i++], x-3, y+5);
            setCreatureToProperLocation(member[i++], x+3, y+4);
            setCreatureToProperLocation(member[i++], x+3, y+5);
            setCreatureToProperLocation(member[i++], x-4, y+6);
            setCreatureToProperLocation(member[i++], x+4, y+6);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x, y-1);
            setCreatureToProperLocation(member[i++], x, y-2);
            setCreatureToProperLocation(member[i++], x+1, y);
            setCreatureToProperLocation(member[i++], x+1, y-1);
            setCreatureToProperLocation(member[i++], x+1, y-3);
            setCreatureToProperLocation(member[i++], x-1, y);
            setCreatureToProperLocation(member[i++], x-1, y-1);
            setCreatureToProperLocation(member[i++], x-1, y-3);
            setCreatureToProperLocation(member[i++], x-2, y-2);
            setCreatureToProperLocation(member[i++], x-2, y-4);
            setCreatureToProperLocation(member[i++], x+2, y-4);
            setCreatureToProperLocation(member[i++], x+2, y-4);
            setCreatureToProperLocation(member[i++], x-3, y-4);
            setCreatureToProperLocation(member[i++], x-3, y-5);
            setCreatureToProperLocation(member[i++], x+3, y-4);
            setCreatureToProperLocation(member[i++], x+3, y-5);
            setCreatureToProperLocation(member[i++], x-4, y-6);
            setCreatureToProperLocation(member[i++], x+4, y-6);
        }
    }
    public void deployFengShiZhen(Creature leader, Creature[] member, boolean onright){
        initialQueues(leader, member, onright);
        Position position=getLeaderLocation(onright);
        int x=position.getX(), y=position.getY();
        setCreatureToProperLocation(leader, x, y);
        if(onright){
            int i=0;
            setCreatureToProperLocation(member[i++], x, y+1);
            setCreatureToProperLocation(member[i++], x, y+2);
            setCreatureToProperLocation(member[i++], x, y+3);
            setCreatureToProperLocation(member[i++], x, y+4);
            setCreatureToProperLocation(member[i++], x, y+5);
            setCreatureToProperLocation(member[i++], x+1, y+1);
            setCreatureToProperLocation(member[i++], x-1, y+1);
            setCreatureToProperLocation(member[i++], x+2, y+2);
            setCreatureToProperLocation(member[i++], x-2, y+2);
            setCreatureToProperLocation(member[i++], x+3, y+3);
            setCreatureToProperLocation(member[i++], x-3, y+3);
        }
        else{
            int i=0;
            setCreatureToProperLocation(member[i++], x, y-1);
            setCreatureToProperLocation(member[i++], x, y-2);
            setCreatureToProperLocation(member[i++], x, y-3);
            setCreatureToProperLocation(member[i++], x, y-4);
            setCreatureToProperLocation(member[i++], x, y-5);
            setCreatureToProperLocation(member[i++], x+1, y-1);
            setCreatureToProperLocation(member[i++], x-1, y-1);
            setCreatureToProperLocation(member[i++], x+2, y-2);
            setCreatureToProperLocation(member[i++], x-2, y-2);
            setCreatureToProperLocation(member[i++], x+3, y-3);
            setCreatureToProperLocation(member[i++], x-3, y-3);
        }
    }
    //Developments end...

    public boolean isBattleReachedDestination(){
        if(AliveEvilQueue.isEmpty() || AliveJusticeQueue.isEmpty())
            return true;
        return false;
    }
}
