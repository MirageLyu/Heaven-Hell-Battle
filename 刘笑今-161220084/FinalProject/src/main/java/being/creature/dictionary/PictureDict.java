package being.creature.dictionary;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PictureDict {
    private static final Image imagecb1 = new Image("file:src/main/java/GUI/Pictures/cb1.jpg");
    private static final Image imagecb2 = new Image("file:src/main/java/GUI/Pictures/cb2.jpg");
    private static final Image imagecb3 = new Image("file:src/main/java/GUI/Pictures/cb3.jpg");
    private static final Image imagecb4 = new Image("file:src/main/java/GUI/Pictures/cb4.jpg");
    private static final Image imagecb5 = new Image("file:src/main/java/GUI/Pictures/cb5.jpg");
    private static final Image imagecb6 = new Image("file:src/main/java/GUI/Pictures/cb6.jpg");
    private static final Image imagecb7 = new Image("file:src/main/java/GUI/Pictures/cb7.jpg");
    private static final Image imagescorpion = new Image("file:src/main/java/GUI/Pictures/scorpion.jpg");
    private static final Image imagesnake = new Image("file:src/main/java/GUI/Pictures/snake.jpg");
    private static final Image imagepapa = new Image("file:src/main/java/GUI/Pictures/papa.jpg");
    private static final Image imagegoblin = new Image("file:src/main/java/GUI/Pictures/goblin.png");
    private static final Image imagejustice = new Image("file:src/main/java/GUI/Pictures/Justice.jpg");
    private static final Image imageevil = new Image("file:src/main/java/GUI/Pictures/Evil.jpg");

    public static Image switchIdentifierToImage(char identifier) {
        switch (identifier) {
            case '1':
                return imagecb1;
            case '2':
                return imagecb2;
            case '3':
                return imagecb3;
            case '4':
                return imagecb4;
            case '5':
                return imagecb5;
            case '6':
                return imagecb6;
            case '7':
                return imagecb7;
            case 'P':
                return imagepapa;
            case 'G':
                return imagegoblin;
            case 'C':
                return imagescorpion;
            case 'S':
                return imagesnake;
            case 'J':
                return imagejustice;
            case 'E':
                return imageevil;
        }
        return null;
    }
}
