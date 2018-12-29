package File;

import being.creature.Creature;
import being.item.Item;
import output.Output;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Replay {
    private static final List<String> GlobalStringBuffer = new ArrayList<>();
    private static MultiFrame frames = new MultiFrame();


    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private static final String filename = "battle-" + df.format(new Date()) + ".slog";

    private static ObjectOutputStream os;
    private static ObjectInputStream is;

    public static void setInfilename(String string){
        try{
            is = new ObjectInputStream(new FileInputStream(string));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static{
        try{
            os = new ObjectOutputStream(new FileOutputStream(filename));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void addString(String string){
        GlobalStringBuffer.add(string);
    }
    public static void addFrame(ShowBeing[][] showBeings){
        Frame frame = new Frame();
        frame.setUpFrameMatrix(showBeings);
        frame.setUpStrings(GlobalStringBuffer);
        GlobalStringBuffer.clear();

        frames.getFrames().add(frame);
    }

    public static void outputToFile(){
        try{
            os.writeObject(frames);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void loadFromFile(){
        try{
            frames = (MultiFrame) is.readObject();
            //frames.outputFrames();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void flushOutputbuffer(){
        try{
            os.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static boolean nextFrameLoadLogFileToBackGroundScreen(ShowBeing[][] showBeings){
        if(frames.getFrames().isEmpty())
            return false;

        Frame cur = frames.getFrames().remove();
        //cur.printFrame();
        List<String> stringList = cur.getInformation_s();
        for(String s : stringList){
            Output.outputInformation(s);
        }

        int width = cur.getWidth();
        int height = cur.getHeight();

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(cur.getIdentifier_s()[i][j] == '-'){
                    showBeings[i][j] = null;
                }
                else if(cur.getIdentifier_s()[i][j] == 'J' || cur.getIdentifier_s()[i][j]=='E'){
                    showBeings[i][j] = new Item();
                    showBeings[i][j].modifyIdentifier(cur.getIdentifier_s()[i][j]);
                }
                else {
                    showBeings[i][j] = new Creature();
                    showBeings[i][j].modifyHealth(cur.getHealth_s()[i][j]);
                    showBeings[i][j].modifyIdentifier(cur.getIdentifier_s()[i][j]);
                    showBeings[i][j].modifyMaxHealth(cur.getMaxhealth_s()[i][j]);
                }
            }
        }

        return true;
    }
}
