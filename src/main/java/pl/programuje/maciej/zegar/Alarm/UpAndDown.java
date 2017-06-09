package pl.programuje.maciej.zegar.Alarm;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Created by m.szymczyk on 2017-02-24.
 */
public class UpAndDown {
    int width = 30;
    Button up = new Button("↑");
    Button down = new Button("↓");
    TextField field;
    VBox vBox;
    AnimationTimer upTimer, downTimer;

    public UpAndDown(int input, int max) {
        up.setMaxWidth(width);
        down.setMaxWidth(width);
        field = new TextField();
        field.setMaxWidth(width);
        vBox = new VBox(up, field, down);

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                field.setText("0" + newValue);
            }
        });

        field.setText(String.valueOf(input));

        upTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (Integer.valueOf(field.getText()) < max) {
                    field.setText(String.valueOf(Integer.valueOf(field.getText()) + 1));
                } else {
                    field.setText("0");
                }
            }
        };

        downTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (Integer.valueOf(field.getText()) > 0) {
                    field.setText(String.valueOf(Integer.valueOf(field.getText()) - 1));
                } else {
                    field.setText(String.valueOf(max));
                }
            }
        };

        up.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    upTimer.start();
                    sleep();
                } else {
                    upTimer.stop();
                }
            }
        });

        down.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    downTimer.start();
                    sleep();
                } else {
                    downTimer.stop();
                }
            }
        });
    }

    public VBox getvBox() {
        return vBox;
    }

    private void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getResult() {
        return Integer.valueOf(field.getText());
    }

    public TextField getField() {
        return field;
    }
}
