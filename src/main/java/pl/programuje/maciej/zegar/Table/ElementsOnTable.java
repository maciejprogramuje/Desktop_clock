package pl.programuje.maciej.zegar.Table;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static pl.programuje.maciej.zegar.Zegar.MARKS_COLOUR;

/**
 * Created by m.szymczyk on 2017-02-20.
 */
public class ElementsOnTable {
    ArrayList<Pane> elementsList = new ArrayList<>();
    Circle table;

    public Pane makeMarks(double width, double height, int numbersOfMarks, int i) {
        Rectangle mark = new Rectangle(width, height, Paint.valueOf(MARKS_COLOUR));
        Pane pane = makeSymetric(mark, true);
        pane.setLayoutX(table.getRadius() - width / 2);
        pane.setRotate(360 / numbersOfMarks * i);
        return pane;
    }

    public Pane makePointer(double width, double height, String backgroundColour, String strokeColour) {
        Pointer pointer = new Pointer(width, height, backgroundColour, strokeColour);
        pointer.polygon.setLayoutY(table.getRadius() - height);
        Pane pane = makeSymetric(pointer.getPolygon(), false);
        pane.setLayoutX(table.getRadius() - width / 2);
        return pane;
    }

    public Pane makeSymetric(Node node, Boolean fillOthersideByColour) {
        Pane pane = new Pane(node);
        if(fillOthersideByColour) {
            Rectangle symetricRectangle = new Rectangle(((Rectangle)node).getWidth(), ((Rectangle)node).getHeight(), (((Rectangle)node).getFill()));
            symetricRectangle.setLayoutY(table.getRadius() * 2 - ((Rectangle)node).getHeight());
            pane.getChildren().add(symetricRectangle);
        } else {
            pane.setPrefHeight(table.getRadius() * 2);
        }
        return pane;
    }
}
