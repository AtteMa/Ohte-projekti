import domain.Ammunition;
import domain.Asteroid;
import domain.Spaceship;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class spaceShipTest {
    
    Spaceship ship;
    
    public spaceShipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ship = new Spaceship(1, 1);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void spaceShipMovingWorks() {
        Point2D movement = new Point2D(0, 0);
        ship.move();
        assertEquals(movement, ship.getMovement());
    }
    
    @Test
    public void asteroidAmmoCollidingWorks() {
        Ammunition ammo = new Ammunition(100, 100);
        Asteroid asteroid = new Asteroid(100, 100);
        
        ammo.collide(asteroid);
        assertEquals(true, ammo.collide(asteroid));
        
    }    
}
