package GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainthread.MainThread;


public class Main extends Application {
    public static Canvas canvas = new Canvas(1200, 600);
    public static Stage stageDelegate;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageDelegate = primaryStage;

        Group root = new Group();

        //Initialize background battlefield here
        primaryStage.setTitle("Heaven-Hell Battle!");
        Parent anchorpane = FXMLLoader.load(getClass().getClassLoader().getResource("battlefield.fxml"));

        root.getChildren().add(anchorpane);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
