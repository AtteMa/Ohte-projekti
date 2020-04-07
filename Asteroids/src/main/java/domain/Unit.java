package domain;

/* Pelin liikkuvien osien yliluokka */

import ui.AsteroidsUi;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Unit {
    private Polygon unit;
    private Point2D movement;
    
    public Unit(Polygon polygon, int x, int y, Color color) {
        this.unit = polygon;
        this.unit.setTranslateX(x);
        this.unit.setTranslateY(y);
        
        polygon.setFill(color);
        
        this.movement = new Point2D(0, 0);
    }
    
    public Polygon getUnit() {
        return unit;
    }
    
    public Point2D getMovement() {
        return movement;
    }
    
    public void setMovement(Point2D movement) {
        this.movement = movement;
    }
    
    public void turnLeft() {
        this.unit.setRotate(this.unit.getRotate() - 3);
    }
    
    public void turnRight() {
        this.unit.setRotate(this.unit.getRotate() + 3);
    }
    
    public void move() {
        this.unit.setTranslateX(this.unit.getTranslateX() + this.movement.getX());
        this.unit.setTranslateY(this.unit.getTranslateY() + this.movement.getY());
        
        if (this.unit.getTranslateX() < 0) {
            this.unit.setTranslateX(this.unit.getTranslateX() + AsteroidsUi.WIDTH);
        }
        
        if (this.unit.getTranslateX() > AsteroidsUi.WIDTH) {
            this.unit.setTranslateX(this.unit.getTranslateX() % AsteroidsUi.WIDTH);
        }
        
        if (this.unit.getTranslateY() < 0) {
            this.unit.setTranslateY(this.unit.getTranslateY() + AsteroidsUi.HEIGHT);
        }
        
        if (this.unit.getTranslateY() > AsteroidsUi.HEIGHT) {
            this.unit.setTranslateY(this.unit.getTranslateY() % AsteroidsUi.HEIGHT);
        }
    }
    
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.unit.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.unit.getRotate()));
        
        changeX *= 0.01;
        changeY *= 0.01;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
    public void decelerate() {
        double changeX = Math.cos(Math.toRadians(this.unit.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.unit.getRotate()));
        
        changeX *= -0.01;
        changeY *= -0.01;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
    public boolean collide(Unit other) {
        Shape collideZone = Shape.intersect(this.unit, other.getUnit());
        return collideZone.getBoundsInLocal().getWidth() != -1;
    }
}
