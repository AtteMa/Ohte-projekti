import domain.Ammunition;
import domain.Asteroid;
import domain.Spaceship;
import java.util.ArrayList;
import java.util.List;
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
        ship = new Spaceship(100, 100);
        List<Ammunition> ammo = new ArrayList<>(); 
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
        
        assertEquals(true, ammo.collide(asteroid));
    }
    
    @Test
    public void shipAsteroidCollidingWorks() {
        Asteroid asteroid = new Asteroid(100, 100);
        
        assertEquals(true, ship.collide(asteroid));
    }
    
    @Test
    public void correctAmountOfAmmoIsShot() {
        List<Ammunition> ammo = new ArrayList<>();
        ship.shoot(ammo, ship);
        
        assertEquals(1, ammo.size());
    }
}
