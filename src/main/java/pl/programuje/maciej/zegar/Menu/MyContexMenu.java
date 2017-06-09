package pl.programuje.maciej.zegar.Menu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import org.joda.time.LocalTime;
import pl.programuje.maciej.zegar.Alarm.DoAlarm;
import pl.programuje.maciej.zegar.Controllers.ClockPaneController;
import pl.programuje.maciej.zegar.Alarm.AlarmSign;
import pl.programuje.maciej.zegar.Alarm.AlarmTime;

/**
 * Created by m.szymczyk on 2017-02-23.
 */
public class MyContexMenu {
    ContextMenu contextMenu;
    AlarmSign alarmSign;
    ClockPaneController clockPaneController;
    Menu alarmItem;
    String alarmTimeString = "";
    AlarmTime alarmTimeItem;

    public MyContexMenu(AlarmSign alarmSign) {
        this.alarmSign = alarmSign;

        contextMenu = new ContextMenu();
        contextMenu.autoHideProperty().setValue(false);

        alarmItem = new Menu("Set alarm");
        alarmTimeItem = new AlarmTime();
        alarmItem.getItems().add(alarmTimeItem);

        alarmTimeItem.getOkButton().setOnAction(event -> {
            alarmTimeString = alarmTimeItem.getHoursPane().getResult() +":"+alarmTimeItem.getMinutesPane().getResult()+":"+alarmTimeItem.getSecondsPane().getResult();
            clockPaneController.setAlarmTimeString(alarmTimeString);
            alarmSign.getAlarmImageView().setOpacity(1.0);
            contextMenu.hide();
        });

        alarmTimeItem.getCancelButton().setOnAction(event -> {
            alarmSign.getAlarmImageView().setOpacity(0.2);
            alarmTimeString = "";
            contextMenu.hide();
        });

        MenuItem closeItem = new MenuItem("Close");
        closeItem.setOnAction(event -> System.exit(0));

        CheckMenuItem alwaysOnTopItem = new CheckMenuItem("Always on top");
        alwaysOnTopItem.setSelected(true);

        contextMenu.getItems().addAll(alarmItem, new SeparatorMenuItem(), alwaysOnTopItem, new SeparatorMenuItem(), closeItem);
    }

    public ContextMenu getContextMenu() {
        if(alarmSign.getAlarmImageView().getOpacity() != 1) {
            alarmTimeItem.getHoursPane().getField().setText(String.valueOf(new LocalTime().getHourOfDay()));
            alarmTimeItem.getMinutesPane().getField().setText(String.valueOf(new LocalTime().getMinuteOfHour()));
            alarmTimeItem.getSecondsPane().getField().setText(String.valueOf(new LocalTime().getSecondOfMinute()));
        }
        return contextMenu;
    }

    public void setClockPaneController(ClockPaneController clockPaneController) {
        this.clockPaneController = clockPaneController;
    }
}
