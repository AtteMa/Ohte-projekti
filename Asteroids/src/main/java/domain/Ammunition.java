package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Luokka joka rakentaa Ammus-monikulmion.
 */
public class Ammunition extends Unit {
    
    /**
     * Konstruktori Ammunition-oliolle
     * @param x Käyttäjän antama x-koordinaatti kokonaislukuna
     * @param y Käyttäjän antama y-koordinaatti kokonaislukuna
     */
    public Ammunition(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y, Color.YELLOW);
    }
}
