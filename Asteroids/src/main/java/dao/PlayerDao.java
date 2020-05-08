package dao;

/**
 * Pelaajan käsittelyä hoitava rajapinta.
 */

import domain.Player;
import java.util.List;


public interface PlayerDao {
    
    Player findByName(String name);
    
    Player create(Player player) throws Exception;
    
    Player update(Player player, int highscore) throws Exception;
    
    List<Player> getAll();
    
}
