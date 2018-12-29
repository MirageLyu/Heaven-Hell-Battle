package configurations;

public class Configuration {
    public static final int MinAttackRate = 2;

    public static final int InitialCalabashAttackRate = 10;
    public static final int InitialCalabashHealth = 120;
    public static final int InitialCalabashRange = 3;

    public static final int InitialOldPapaAttackRate = 4;
    public static final int InitialOldPapaHealth = 100;
    public static final int InitialOldPapaRange = 5;

    public static final int InitialSnakeAttackRate = 15;
    public static final int InitialSnakeHealth = 100;
    public static final int InitialSnakeRange = 2;

    public static final int InitialScorpionAttackRate = 8;
    public static final int InitialScorpionHealth = 250;
    public static final int InitialScorpionRange = 5;

    public static final int InitialGoblinAttackRate = 4;
    public static final int InitialGoblinHealth = 80;
    public static final int InitialGoblinRange = 4;

    public static final int ItemAttackValue = 5;
    public static final int ItemHealthValue = 40;

    public static int getItemValue(boolean whichvalue){
        if(whichvalue)
            return ItemHealthValue;
        return ItemAttackValue;
    }
}
