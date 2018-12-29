package being.creature.justice;

import battlefield.BattleField;
import being.creature.Creature;
import being.item.Item;
import position.Position;
import exceptions.CreateFailedException;
import being.creature.dictionary.*;
import configurations.Configuration;

import java.util.ArrayList;

public class Calabash extends Creature {
    private static int globalnums = 0;

    public Calabash(ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue,  BattleField bf) throws CreateFailedException{
        super(new Position(-1, -1), CalabashDict.getNameFromRank(globalnums), IdentifierDict.getCalabashIdentifier(globalnums), true, Configuration.InitialCalabashHealth, Configuration.InitialCalabashAttackRate, Configuration.InitialCalabashRange, EnemyQueue, ItemQueue, PictureDict.switchIdentifierToImage(IdentifierDict.getCalabashIdentifier(globalnums)), bf);
        if (globalnums >= 7) {
            throw new CreateFailedException("Calabash");
        } else {
            globalnums++;
        }
    }
}
