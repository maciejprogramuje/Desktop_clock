package pl.programuje.maciej.zegar.Table;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

/**
 * Created by m.szymczyk on 2017-02-20.
 */
public class Pointer {
    Polygon polygon;
    double width, height;
    String colour;

    public Pointer(double width, double height, String backgroundColour, String strokeColour) {
        this.width = width;
        this.height = height;
        this.colour = backgroundColour;
        polygon = new Polygon(width/2,0, width,height/4, width/2,height, 0,height/4);
        polygon.setStroke(Paint.valueOf(strokeColour));
        polygon.setStrokeType(StrokeType.INSIDE);
        polygon.setFill(Paint.valueOf(backgroundColour));
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
