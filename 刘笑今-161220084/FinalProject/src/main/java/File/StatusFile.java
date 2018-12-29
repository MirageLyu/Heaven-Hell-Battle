package File;

import being.Being;
import battlefield.BattleField;
import position.Position;

import java.io.*;

public class StatusFile {
    private static OutputStreamWriter outfile;
    static{
        try {
            outfile = new OutputStreamWriter(new FileOutputStream("battle.log"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void outHolderStatusToFile(BattleField bf) {
        for (int i = 0; i < bf.getHeight(); i++) {
            for (int j = 0; j < bf.getWidth(); j++) {
                try {
                    if (bf.isLocationEmpty(new Position(i, j))) {
                        outfile.write("- ");
                    } else {
                        outfile.write(bf.getHolder()[i][j].getIdentifier());
                        outfile.write(' ');
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                outfile.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            outfile.write("\n\r");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void outCreatureStatusToFile(BattleField bf){
        for(int i=0; i<bf.AliveJusticeQueue.size(); i++){
            try{
                outfile.write(bf.AliveJusticeQueue.get(i).getName() + " current health: " + bf.AliveJusticeQueue.get(i).getHealth() + "/" + bf.AliveJusticeQueue.get(i).getMaxhealth() + "\r\n");
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        for(int i=0; i<bf.AliveEvilQueue.size(); i++){
            try{
                outfile.write(bf.AliveEvilQueue.get(i).getName() + " current health: " + bf.AliveEvilQueue.get(i).getHealth() + "/" + bf.AliveEvilQueue.get(i).getMaxhealth() + "\r\n");
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
