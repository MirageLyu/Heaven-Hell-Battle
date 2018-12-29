package File;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MultiFrame implements Serializable {
    private static final long serialVersionUID = -1926420096324170625L;
    private Queue<Frame> frames = new LinkedList<>();
    public Queue<Frame> getFrames(){
        return frames;
    }

    public void outputFrames(){
        List<Frame> list = (LinkedList<Frame>)frames;
        for(Frame f:list){
            f.printFrame();
        }
    }
}
