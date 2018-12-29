package being;

import File.ShowBeing;
import position.Position;
import output.Output;
import javafx.scene.image.Image;

public abstract class Being implements ShowBeing {
    private Position position;
    private String name;
    private char identifier;
    private Image image;

    //true: justice; false: evil
    private boolean faction;

    public Being(){
        ;
    }

    //use lambda statement to initialize
    public Being(Position position, String name, char identifier, boolean faction, Image image){
        this.position = position;
        this.name=name;
        this.identifier=identifier;
        this.faction=faction;
        this.image=image;
    }

    public Position getPosition() {
        return position;
    }
    public char getIdentifier() {
        return identifier;
    }
    public String getName() {
        return name;
    }
    public boolean getFaction(){
        return faction;
    }
    public Image getImage(){
        return image;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public void setIdentifier(char identifier) {
        this.identifier = identifier;
    }

    public void moveToPosition(Position position){
        setPosition(position);
        Output.outputInformation(name + " has moved to location: ("+position.getX()+","+position.getY()+").");
    }
}
