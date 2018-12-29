package being.creature.evil;

import battlefield.BattleField;
import being.creature.Creature;
import being.creature.dictionary.*;
import being.item.Item;
import configurations.Configuration;
import exceptions.CreateFailedException;
import position.Position;

import java.util.ArrayList;

public class Scorpion extends Creature{
    private static int globalnums = 0;

    public Scorpion(ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue, BattleField bf) throws CreateFailedException {
        super(new Position(-1, -1), "Scorpion", IdentifierDict.getScorpionIdentifier(), false, Configuration.InitialScorpionHealth, Configuration.InitialScorpionAttackRate, Configuration.InitialScorpionRange, EnemyQueue, ItemQueue, PictureDict.switchIdentifierToImage(IdentifierDict.getScorpionIdentifier()), bf);
        if (globalnums >= 1) {
            throw new CreateFailedException("Scorpion");
        } else {
            globalnums++;
        }
    }
}
