/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author atte
 */
public class Asteroid extends Unit{
    private double rotation;

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

    @Override
    public void move() {
        super.move();
        super.getUnit().setRotate(super.getUnit().getRotate() + rotation);

    }
}
