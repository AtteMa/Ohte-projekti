package domain;

import dao.PlayerDao;
import java.util.List;

/**
 * Pelaajan käsittelyä helpottava service luokka.
 */

public class PlayerService {
    private PlayerDao playerDao;
    
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
    
    public boolean createPlayer(String name, String points, int highscore) {
        if (playerDao.findByName(name) != null) {
            return false;
        }
        Player player = new Player(name, points, highscore);
        try {
            playerDao.create(player);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public List<Player> listAll() {
        return playerDao.getAll();
    }
    
    public void updatePlayer(Player player, int highscore) {
        Player p = playerDao.findByName(player.getName());
        try {
            playerDao.update(p, highscore);
        } catch (Exception e) {
            
        }
    }
    
    public Player findPlayer(String name) {
        return playerDao.findByName(name);
    }
}
