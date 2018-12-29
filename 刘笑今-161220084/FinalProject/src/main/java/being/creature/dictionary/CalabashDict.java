package being.creature.dictionary;

public class CalabashDict {
    private static final String[] map = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple"};
    public static String getNameFromRank(int rank){
        if(rank<7){
            return map[rank];
        }
        else{
            return "None";
        }
    }
}
