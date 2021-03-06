package being.creature.justice;

import battlefield.BattleField;
import being.creature.Creature;
import being.creature.dictionary.*;
import being.item.Item;
import configurations.Configuration;
import exceptions.CreateFailedException;
import position.Position;

import java.util.ArrayList;

public class OldPapa extends Creature {
    private static int globalnums = 0;

    public OldPapa(ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue, BattleField bf) throws CreateFailedException {
        super(new Position(-1, -1), "OldPapa", IdentifierDict.getOldPapaIdentifier(), true, Configuration.InitialOldPapaHealth, Configuration.InitialOldPapaAttackRate, Configuration.InitialOldPapaRange, EnemyQueue, ItemQueue, PictureDict.switchIdentifierToImage(IdentifierDict.getOldPapaIdentifier()), bf);
        if (globalnums >= 1) {
            throw new CreateFailedException("OldPapa");
        } else {
            globalnums++;
        }
    }
}
