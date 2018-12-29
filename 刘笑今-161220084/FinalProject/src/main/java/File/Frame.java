package File;

import being.Being;
import being.creature.Creature;
import mainthread.MainThread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Frame implements Serializable{
    private static final long serialVersionUID = -2961822175327548687L;
    private int width, height;
    private char[][] identifier_s;
    private int[][] health_s;
    private int[][] maxhealth_s;
    private ArrayList<String> information_s;

    public void printFrame(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                System.out.print(identifier_s[i][j] + " ");
            }
            System.out.println();
        }
    }


    public Frame(){
        this.width = MainThread.bf.getWidth();
        this.height = MainThread.bf.getHeight();

        identifier_s = new char[height][width];
        health_s = new int[height][width];
        maxhealth_s = new int[height][width];

        information_s= new ArrayList<>();
    }
    public void setUpFrameMatrix(ShowBeing[][] beings){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(beings[i][j]==null){
                    identifier_s[i][j]='-';
                    health_s[i][j]=-1;
                    maxhealth_s[i][j]=-1;
                }
                else{
                    identifier_s[i][j] = beings[i][j].reportIdentifier();
                    health_s[i][j] = -1;
                    maxhealth_s[i][j] = -1;
                    if(beings[i][j] instanceof Creature){
                        health_s[i][j] = beings[i][j].reportHealth();
                        maxhealth_s[i][j] = beings[i][j].reportMaxHealth();
                    }
                }
            }
        }
    }

    public void setUpStrings(List<String> arrayList){
        information_s.addAll(arrayList);
    }

    public ArrayList<String> getInformation_s() {
        return information_s;
    }

    public char[][] getIdentifier_s() {
        return identifier_s;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getHealth_s() {
        return health_s;
    }

    public int[][] getMaxhealth_s() {
        return maxhealth_s;
    }
}
