package domain;

/* Avaruusalusta kuvaava luokka */

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Spaceship extends Unit {
    public Spaceship(int x, int y) {
        super(new Polygon(-7, -7, 13, 0, -7, 7), x, y, Color.RED);
    }
}
