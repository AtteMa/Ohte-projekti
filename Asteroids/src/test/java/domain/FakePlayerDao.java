package domain;

import dao.PlayerDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author atte
 */
public class FakePlayerDao implements PlayerDao {
    List<Player> players = new ArrayList<>();
    
    public FakePlayerDao() {
        players.add(new Player("test", "0", 100));
    }
    
    @Override
    public Player findByName(String name) {
        return players.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
    
    @Override
    public Player create(Player player) {
        players.add(player);
        return player;
    }
    
    @Override
    public Player update(Player player, int score) {
        player.setHighScore(score);
        return player;
    }
    
    @Override
    public List<Player> getAll() {
        return players;
    }
}
