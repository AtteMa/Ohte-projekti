package domain;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Luokka joka rakentaa Asteroid-monikulmion.
 */

public class Asteroid extends Unit {
    private double rotation;
    
    /**
     * Konstruktori Asteroid-oliolle. Konstruktorissa kutsutaan PolygonGenerator-
     * olion create() metodia, jotta luoduissa Asteroid-olioissa olisi vaihtuvuutta.
     * @param x Käyttäjän antama x-koordinaatti kokonaislukuna
     * @param y Käyttäjän antama y-koordinaatti kokonaislukuna
     */
    public Asteroid(int x, int y) {
        super(new PolygonGenerator().create(), x, y, Color.GRAY);
        
        Random rnd = new Random();

        super.getUnit().setRotate(rnd.nextInt(360));

        int accelerationCount = 9 + rnd.nextInt(50);

        for (int i = 0; i < accelerationCount; i++) {
            accelerate();
        }

        this.rotation = 0.5 - rnd.nextDouble();

    }
    
    /**
     * Asteroidin oma move() metodi joka lisää asteroidille pyörimisliikkeen
     */
    @Override
    public void move() {
        super.move();
        super.getUnit().setRotate(super.getUnit().getRotate() + rotation);

    }
}
