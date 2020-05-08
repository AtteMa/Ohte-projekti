package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PlayerServiceTest {
    FakePlayerDao playerDao;
    PlayerService service;
    
    @Before
    public void setUp() {
        playerDao = new FakePlayerDao();
        service = new PlayerService(playerDao);
    }
    
    @Test
    public void noNewPlayerIfNameNotUnique() {
        boolean result = service.createPlayer("test", "0", 100);
        assertFalse(result);
    }
    
    @Test 
    public void updatingScoreWorks() {
        Player p = service.findPlayer("test");
        int scoreOld = p.getHighScore();
        service.updatePlayer(p, scoreOld+10);
        assertEquals(110, p.getHighScore());
    }
}
