package being.creature.evil;

import battlefield.BattleField;
import being.creature.Creature;
import being.creature.dictionary.*;
import being.item.Item;
import configurations.Configuration;
import exceptions.CreateFailedException;
import position.Position;

import java.util.ArrayList;

public class Goblin extends Creature{
    private static int globalnums = 0;

    public Goblin(ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue, BattleField bf) throws CreateFailedException {
        super(new Position(-1, -1), "Goblin", IdentifierDict.getGoblinIdentifier(), false, Configuration.InitialGoblinHealth, Configuration.InitialGoblinAttackRate, Configuration.InitialGoblinRange, EnemyQueue, ItemQueue, PictureDict.switchIdentifierToImage(IdentifierDict.getGoblinIdentifier()), bf);
        if (globalnums >= 20) {
            throw new CreateFailedException("Goblin");
        } else {
            globalnums++;
        }
    }
}
