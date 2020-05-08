package domain;

/**
 * Luokka rakentaa Spaceship-monikulmion.
 */

import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Spaceship extends Unit {
    /**
     * Konstruktori Spaceship-oliolle
     * 
     * @param x Käyttäjän antama x-koordinaatti kokonaislukuna
     * @param y Käyttäjän antama y-koordinaatti kokonaislukuna
     */
    public Spaceship(int x, int y) {
        super(new Polygon(-7, -7, 13, 0, -7, 7), x, y, Color.RED);
    }
    
    private Ammunition ammunition;
    
    /**
     * Aluksen ampumis-operaation suorittava metodi. Luo Ammunition-olion ja
     * liikuttaa sitä aluksen osoittamaan suuntaan.
     * 
     * @param ammo Käyttäjän antama List-olio
     * @param ship Käyttäjän antama Spaceship-olio
     */
    public void shoot(List ammo, Spaceship ship) {
        this.ammunition = new Ammunition((int) ship.getUnit().getTranslateX(),
            (int) ship.getUnit().getTranslateY());
        this.ammunition.getUnit().setRotate(ship.getUnit().getRotate());
        ammo.add(this.ammunition);
                    
        this.ammunition.accelerate();
        this.ammunition.setMovement(this.ammunition.getMovement().normalize());
    }
    
    public Polygon getAmmunition() {
        return this.ammunition.getUnit();
    }
}
