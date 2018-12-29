package output;

import File.Replay;

public class Output {
    public static void outputInformation(String string){
        System.out.println("output: "+string);
    }

    public static void outputToReplayArray(String string){
        Replay.addString(string);
    }

    public static void outputScriptsOnScreen(String string){

    }

    public static void outputCreateFailedMessage(String name){
        System.out.println(name + " created failed! The number has reached upper bound.");
    }

    public static void outputAtInitialPositionMessage(String name){
        System.out.println(name + " is at INITIAL position! (-1, -1)");
    }

    public static void outputOutOfBoundMessage(String name){
        System.out.println(name + " is out of BattleField!");
    }
}
