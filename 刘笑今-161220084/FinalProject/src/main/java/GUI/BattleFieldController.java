package GUI;

import File.Replay;
import File.ShowBeing;
import battlefield.BattleField;
import being.Being;
import being.creature.Creature;
import being.creature.dictionary.PictureDict;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import mainthread.MainThread;
import mainthread.ReplayThread;
import position.Position;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleFieldController implements Initializable, Runnable{

    public void initialize(URL url, ResourceBundle resourceBundle){
        for(int i=0; i<10; i++){
            for(int j=0; j<20; j++){
                ImageMatrix[i][j] = new ImageView();
                ImageMatrix[i][j].setFitWidth(60);
                ImageMatrix[i][j].setFitHeight(60);
                GridPane.setRowIndex(ImageMatrix[i][j], i);
                GridPane.setColumnIndex(ImageMatrix[i][j], j);
            }
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<20; j++){
                BattleGridPane.getChildren().add(ImageMatrix[i][j]);
            }
        }
        BattleGridPane.setGridLinesVisible(true);
        MainThread MT = new MainThread();

        StartButton.setOnAction((ActionEvent e)-> {
            System.out.println("Pressed!!");
            MT.start();
        });
        LoadButton.setOnAction((ActionEvent e)->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SLOG files (*.slog)", "*.slog");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.stageDelegate);


            MainThread.setIsRunning(true);
            ReplayThread rt = new ReplayThread();

            Replay.setInfilename(file.getAbsolutePath());

            rt.start();
        });

        this.start();
    }

    private ImageView[][] ImageMatrix = new ImageView[10][20];

    private void syncBackgroundBFToUI(Being[][] beings){
        if(beings == null){
            return;
        }

        Main.canvas.getGraphicsContext2D().clearRect(0, 0, 1200, 600);
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(beings[i][j]==null)
                    ImageMatrix[i][j].setImage(null);
                else{
                    ImageMatrix[i][j].setImage(PictureDict.switchIdentifierToImage(beings[i][j].reportIdentifier()));
                    if(beings[i][j].reportIdentifier() != 'J' && beings[i][j].reportIdentifier() != 'E'){
                        drawHealthRectangles(beings[i][j], i, j);
                    }
                }
            }
        }
    }

    private void drawHealthRectangles(ShowBeing creature, int y, int x){
        GraphicsContext graphicsContext2D = Main.canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.RED);

        y = y*60 + 55;
        x = x*60;

        graphicsContext2D.fillRect(x,y, 60, 5);

        graphicsContext2D.setFill(Color.GREEN);

        double length = (double)creature.reportHealth()/(double)creature.reportMaxHealth() * 60;
        graphicsContext2D.fillRect(x, y, length, 5);
}

    public static Being[][] showHolder = MainThread.bf.getHolder();
    public void run(){
        while(true){
            if(MainThread.getIsRunning()){
                StartButton.setDisable(true);
                LoadButton.setDisable(true);
            }else{
                LoadButton.setDisable(false);
            }
            synchronized (MainThread.bf.getHolder()) {
                syncBackgroundBFToUI(showHolder);
            }
            try{
                Thread.sleep(40);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private Thread thread;
    public void start(){
        if(thread == null){
            thread = new Thread(this, "threadGUI");
            thread.start();
        }
    }


    @FXML
    GridPane BattleGridPane;
    @FXML
    Button StartButton;
    @FXML
    Button LoadButton;
}
