package pl.programuje.maciej.zegar.Table;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static pl.programuje.maciej.zegar.Zegar.*;

/**
 * Created by 5742ZGPC on 2017-02-19.
 */
public class Pointers extends ElementsOnTable {

    public Pointers(Circle table) {
        this.table = table;
        elementsList.add(makePointer(CLOCK_SIZE * 0.20,CLOCK_SIZE * 0.6, HOURS_POINTER_COLOUR, POINTERS_STROKE_COLOUR));
        elementsList.add(makePointer(CLOCK_SIZE * 0.12, CLOCK_SIZE * 1.0, MINUTES_POINTER_COLOUR, POINTERS_STROKE_COLOUR));
        elementsList.add(makePointer(CLOCK_SIZE * 0.08, CLOCK_SIZE * 1.0, SECONDS_POINTER_COLOUR, POINTERS_STROKE_COLOUR));
    }

    public ArrayList<Pane> getPointers() {
        return elementsList;
    }
}
