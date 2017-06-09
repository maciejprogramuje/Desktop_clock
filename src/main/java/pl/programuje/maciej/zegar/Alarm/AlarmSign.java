package pl.programuje.maciej.zegar.Alarm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static pl.programuje.maciej.zegar.Zegar.CLOCK_SIZE;

/**
 * Created by m.szymczyk on 2017-02-23.
 */
public class AlarmSign {
    ImageView alarmImageView;

    public AlarmSign() {
        alarmImageView = new ImageView();
        alarmImageView.setImage(new Image("/images/alarm.png"));
        alarmImageView.setFitWidth(CLOCK_SIZE * 0.40);
        alarmImageView.setFitHeight(CLOCK_SIZE * 0.40);
        alarmImageView.setLayoutX(CLOCK_SIZE - alarmImageView.getFitWidth() / 2);
        alarmImageView.setLayoutY(CLOCK_SIZE * 1.4);
        alarmImageView.setOpacity(0.2);
    }

    public ImageView getAlarmImageView() {
        return alarmImageView;
    }
}
