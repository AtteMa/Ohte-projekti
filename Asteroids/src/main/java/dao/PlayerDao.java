package dao;

import domain.Player;
import java.util.List;


public interface PlayerDao {
    
    Player findByName(String name);
    
    Player create(Player player) throws Exception;
    
    List<Player> getAll();
    
}
