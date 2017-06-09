package pl.programuje.maciej.zegar.Alarm;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pl.programuje.maciej.zegar.Controllers.ClockPaneController;
import pl.programuje.maciej.zegar.Zegar;

/**
 * Created by m.szymczyk on 2017-03-01.
 */
public class DoAlarm {
    ClockPaneController clockPaneController;
    boolean alarmRinging;
    MediaPlayer audio;

    public DoAlarm() {
        Media media = new Media(Zegar.class.getResource("/sounds/bell.mp3").toString());
        audio = new MediaPlayer(media);
    }

    public void start() {
        alarmRinging = true;
        audio.play();
    }

    public void stop() {
        alarmRinging = false;
        audio.stop();
        clockPaneController.getAlarmSign().getAlarmImageView().setOpacity(0.2);
    }

    public void setClockPaneController(ClockPaneController clockPaneController) {
        this.clockPaneController = clockPaneController;
    }

    public boolean isAlarmRinging() {
        return alarmRinging;
    }
}
