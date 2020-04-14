/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author atte
 */
public class Ammunition extends Unit{
    public Ammunition(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y, Color.YELLOW);
    }
}
