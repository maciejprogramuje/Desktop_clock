package pl.programuje.maciej.zegar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.programuje.maciej.zegar.Controllers.ClockPaneController;

/**
 * Created by m.szymczyk on 2017-02-17.
 */
public class Zegar extends Application {
    public static final int CLOCK_SIZE = 50;
    public static final Double CLOCK_OPACITY = 0.8;
    public static final String SECONDS_POINTER_COLOUR = "RED";
    public static final String MINUTES_POINTER_COLOUR = "DARKBLUE";
    public static final String HOURS_POINTER_COLOUR = "DARKBLUE";
    public static final String POINTERS_STROKE_COLOUR = "BLACK";
    public static final String MARKS_COLOUR = "BLACK";
    public static final String TABLE_COLOUR = "WHEAT";

    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/zegarPane.fxml"));
        Scene scene = new Scene(parent);
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(CLOCK_OPACITY);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.getIcons().add(new Image("images/z.png"));
        primaryStage.show();

        new ClockPaneController().setStageAndListeners(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
