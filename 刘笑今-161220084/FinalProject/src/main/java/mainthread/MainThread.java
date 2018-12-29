package mainthread;

import File.Replay;
import File.StatusFile;
import GUI.BattleFieldController;
import battlefield.BattleField;
import being.creature.Creature;
import being.creature.evil.Goblin;
import being.creature.evil.Scorpion;
import being.creature.evil.Snake;
import being.creature.justice.Calabash;
import being.creature.justice.OldPapa;
import exceptions.CreateFailedException;

class RunningStatus{
    private boolean isRunning;
    RunningStatus(){
        isRunning = false;
    }
    public boolean getIsRunning(){
        return isRunning;
    }
    void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}

public class MainThread implements Runnable{
    public static final RunningStatus IsRunning = new RunningStatus();
    public static boolean getIsRunning(){
        return IsRunning.getIsRunning();
    }
    public static void setIsRunning(boolean bl){
        IsRunning.setIsRunning(bl);
    }

    public static final BattleField bf = new BattleField();

    private Thread thread;
    public void run() {
        mainThread();
    }
    public void start(){
        thread = new Thread(this, "thread" + "Main");
        thread.start();
    }

    Creature[] calabashbros = new Calabash[7];
    Creature[] calabashbrosexpred = new Calabash[6];
    Creature oldpapa = null;
    Creature snake = null;
    Creature scorpion = null;
    Creature[] goblins = new Goblin[9];

    public MainThread(){
        try {
            for (int i = 0; i < 7; i++) {
                calabashbros[i] = new Calabash(bf.AliveEvilQueue, bf.ItemsQueue, bf);
            }
        } catch (CreateFailedException e) {
            e.printErrorMessage();
        }

        for (int i = 0; i < 6; i++) {
            calabashbrosexpred[i] = calabashbros[i + 1];
        }
        try {
            oldpapa = new OldPapa(bf.AliveEvilQueue, bf.ItemsQueue, bf);
        }catch (CreateFailedException e){
            e.printErrorMessage();
        }
        try{
            snake = new Snake(bf.AliveJusticeQueue, bf.ItemsQueue, bf);
        } catch (CreateFailedException e){
            e.printErrorMessage();
        }

        try{
            scorpion = new Scorpion(bf.AliveJusticeQueue, bf.ItemsQueue, bf);
        } catch(CreateFailedException e){
            e.printErrorMessage();
        }
        try{
            for(int i=0; i<9;i++){
                goblins[i] = new Goblin(bf.AliveJusticeQueue, bf.ItemsQueue, bf);
            }
        } catch(CreateFailedException e){
            e.printErrorMessage();
        }
    }

    public void mainThread(){
        System.out.println("Called!!");
        setIsRunning(true);

        bf.deployChangSheZhen(calabashbros[0], calabashbrosexpred, false);
        bf.deployYuLinZhen(scorpion, goblins, true);


        for(int i=0; i<bf.AliveJusticeQueue.size(); i++){
            bf.AliveJusticeQueue.get(i).start();
        }
        for(int i=0; i<bf.AliveEvilQueue.size(); i++){
            bf.AliveEvilQueue.get(i).start();
        }
        while(!bf.isBattleReachedDestination()) {
            synchronized (bf) {
                Replay.addFrame(bf.getHolder());

                StatusFile.outHolderStatusToFile(bf);
                StatusFile.outCreatureStatusToFile(bf);
            }
            try{
                Thread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        Replay.addFrame(bf.getHolder());
        System.out.println("Reached destination!!!");
        Replay.outputToFile();
        Replay.flushOutputbuffer();
        setIsRunning(false);
    }
}
