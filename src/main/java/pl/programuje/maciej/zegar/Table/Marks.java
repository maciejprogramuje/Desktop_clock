package pl.programuje.maciej.zegar.Table;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static pl.programuje.maciej.zegar.Zegar.CLOCK_SIZE;

/**
 * Created by 5742ZGPC on 2017-02-19.
 */
public class Marks extends ElementsOnTable {
    public Marks(Circle table) {
        this.table = table;
        makeElements(true, 12);
        makeElements(false, 60);
    }

    private void makeElements(boolean bigMarks, int numbersOfMarks) {
        double width, height;
        if(bigMarks) {
            width = CLOCK_SIZE * 0.04;
            height = CLOCK_SIZE * 0.15;
        } else {
            width = CLOCK_SIZE * 0.02;
            height = CLOCK_SIZE * 0.10;
        }

        for (int i = 0; i < numbersOfMarks / 2; i++) {
            elementsList.add(makeMarks(width, height, numbersOfMarks, i));
        }
    }

    public ArrayList<Pane> getMarks() {
        return elementsList;
    }
}
