package pl.programuje.maciej.zegar.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.joda.time.LocalTime;
import pl.programuje.maciej.zegar.Alarm.AlarmSign;
import pl.programuje.maciej.zegar.Alarm.AlarmTime;
import pl.programuje.maciej.zegar.Alarm.DoAlarm;
import pl.programuje.maciej.zegar.Table.Marks;
import pl.programuje.maciej.zegar.Menu.MyContexMenu;
import pl.programuje.maciej.zegar.Table.Pointers;

import static pl.programuje.maciej.zegar.Zegar.CLOCK_SIZE;
import static pl.programuje.maciej.zegar.Zegar.TABLE_COLOUR;

/**
 * Created by m.szymczyk on 2017-02-17.
 */
public class ClockPaneController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Circle table;

    double x, y;
    AlarmSign alarmSign;
    MyContexMenu myContexMenu;
    String alarmTimeString = "";
    DoAlarm doAlarm;

    @FXML
    public void initialize() {
        table.setRadius(CLOCK_SIZE);
        table.setFill(Paint.valueOf(TABLE_COLOUR));
        alarmSign = new AlarmSign();
        doAlarm = new DoAlarm();
        doAlarm.setClockPaneController(this);

        anchorPane.setBackground(Background.EMPTY);
        anchorPane.getChildren().addAll(alarmSign.getAlarmImageView());
        anchorPane.getChildren().addAll(new Marks(table).getMarks());
        int firstIndexForPointers = anchorPane.getChildren().size();
        anchorPane.getChildren().addAll(new Pointers(table).getPointers());

        setContextMenu();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        EventHandler onFinished = new EventHandler() {
            @Override
            public void handle(Event event) {
                LocalTime localTime = new LocalTime();

                Double s = localTime.getSecondOfMinute() * 360.0 / 60;
                Double m = (localTime.getMinuteOfHour() * 360.0 / 60) + (localTime.getSecondOfMinute() * 360.0 / 60 / 60);
                Double h = (localTime.getHourOfDay() * 360.0 / 12) + (localTime.getMinuteOfHour() * 360.0 / 12 / 60);

                anchorPane.getChildren().get(firstIndexForPointers).setRotate(h);
                anchorPane.getChildren().get(firstIndexForPointers + 1).setRotate(m);
                anchorPane.getChildren().get(firstIndexForPointers + 2).setRotate(s);

                String localTimeString = localTime.getHourOfDay()+":"+localTime.getMinuteOfHour()+":"+localTime.getSecondOfMinute();

                if(localTimeString.equals(alarmTimeString)) {
                    doAlarm.start();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), onFinished);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void setStageAndListeners(Stage stage) {
        Screen screen = Screen.getPrimary();

        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        stage.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if(stage.getX() < 0) {
                stage.setX(1);
            }
            if(stage.getX() + stage.getWidth() > screen.getBounds().getWidth()) {
                stage.setX(screen.getBounds().getWidth() - stage.getWidth() - 1);
            }
            if(stage.getY() < 0) {
                stage.setY(1);
            }
            if(stage.getY() + stage.getHeight() > screen.getBounds().getHeight()) {
                stage.setY(screen.getBounds().getHeight() - stage.getHeight() -1);
            }
        });

        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    private void setContextMenu() {
        myContexMenu = new MyContexMenu(alarmSign);
        myContexMenu.setClockPaneController(this);

        anchorPane.toBack();

        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton().equals(MouseButton.SECONDARY)) {
                myContexMenu.getContextMenu().show(anchorPane, event.getScreenX(), event.getScreenY());
            }
            if(event.getButton().equals(MouseButton.PRIMARY) && doAlarm.isAlarmRinging()) {
                doAlarm.stop();
            }
        });
    }

    public void setAlarmTimeString(String alarmTimeString) {
        this.alarmTimeString = alarmTimeString;
    }

    public AlarmSign getAlarmSign() {
        return alarmSign;
    }
}
