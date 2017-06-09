package pl.programuje.maciej.zegar.Alarm;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.joda.time.LocalTime;

/**
 * Created by m.szymczyk on 2017-03-01.
 */
public class AlarmTime extends CustomMenuItem {
    UpAndDown hoursPane, minutesPane, secondsPane;
    Button okButton, cancelButton;

    public AlarmTime() {
        LocalTime time = new LocalTime();

        hoursPane = new UpAndDown(time.getHourOfDay(), 23);
        minutesPane = new UpAndDown(time.getMinuteOfHour(), 59);
        secondsPane = new UpAndDown(time.getSecondOfMinute(), 59);

        setContent(setContent());
        setHideOnClick(false);
    }

    private Node setContent() {
        HBox hBox1 = new HBox(hoursPane.getvBox(), minutesPane.getvBox(), secondsPane.getvBox());
        hBox1.setPadding(new Insets(5, 5, 5, 5));
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        HBox hBox2 = new HBox(okButton, cancelButton);
        hBox2.setPadding(new Insets(5, 5, 5, 5));
        hBox2.setMargin(okButton, new Insets(0,5,0,0));
        VBox vBox = new VBox(hBox1, hBox2);
        return vBox;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public UpAndDown getHoursPane() {
        return hoursPane;
    }

    public UpAndDown getMinutesPane() {
        return minutesPane;
    }

    public UpAndDown getSecondsPane() {
        return secondsPane;
    }
}
