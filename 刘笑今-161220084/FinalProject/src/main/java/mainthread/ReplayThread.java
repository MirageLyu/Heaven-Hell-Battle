package mainthread;

import File.Replay;
import GUI.BattleFieldController;

public class ReplayThread implements Runnable {
    @Override
    public void run() {
        Replay.loadFromFile();
        while (Replay.nextFrameLoadLogFileToBackGroundScreen(BattleFieldController.showHolder)) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Thread thread;
    public void start(){
        if(thread == null){
            thread = new Thread(this, "Replay Thread");
        }
        thread.start();
    }
}
