package being.creature.dictionary;

public class IdentifierDict {
    public static char getCalabashIdentifier(int rank){
        return (char)((int)'1' + rank);
    }
    public static char getOldPapaIdentifier(){
        return 'P';
    }
    public static char getSnakeIdentifier(){
        return 'S';
    }
    public static char getScorpionIdentifier(){
        return 'C';
    }
    public static char getGoblinIdentifier(){
        return 'G';
    }
    public static char getItemIdentifier(boolean faction){
        if(faction)
            return 'J';
        return 'E';
    }
}
