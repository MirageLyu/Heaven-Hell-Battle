package being.creature.evil;

import battlefield.BattleField;
import being.creature.Creature;
import being.creature.dictionary.*;
import being.item.Item;
import configurations.Configuration;
import exceptions.CreateFailedException;
import position.Position;

import java.util.ArrayList;

public class Snake extends Creature{
    private static int globalnums = 0;

    public Snake(ArrayList<Creature> EnemyQueue, ArrayList<Item> ItemQueue, BattleField bf) throws CreateFailedException {
        super(new Position(-1, -1), "Snake", IdentifierDict.getSnakeIdentifier(), true, Configuration.InitialSnakeHealth, Configuration.InitialSnakeAttackRate, Configuration.InitialSnakeRange, EnemyQueue, ItemQueue, PictureDict.switchIdentifierToImage(IdentifierDict.getSnakeIdentifier()), bf);
        if (globalnums >= 1) {
            throw new CreateFailedException("Snake");
        } else {
            globalnums++;
        }
    }
}
