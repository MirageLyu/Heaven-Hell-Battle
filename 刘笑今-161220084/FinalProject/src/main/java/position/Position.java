package position;

public class Position {
    private int x;
    private int y;

    public Position(){
        x=y=-1;
    }
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Position(Position position){
        this.x=position.getX();
        this.y=position.getY();
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public static boolean isInitialPosition(Position position){
        return position.getX()==-1 && position.getY()==-1;
    }

    public static boolean outOfBoundChecker(Position position, Position lower, Position upper){
        if(position.getX()>=lower.getX() && position.getX() <= upper.getX() && position.getY()>=lower.getY() && position.getY()<=upper.getY())
            return false;
        return true;
    }
}
