package domain;

import dao.PlayerDao;
import java.util.List;
import ui.AsteroidsUi;

/**
 * Pelaajan käsittelyä helpottava service luokka.
 */

public class PlayerService {
    private PlayerDao playerDao;
    
    /**
     * Konstruktori PlayerService-oliolle.
     * 
     * @param playerDao Käyttäjän antama PlayerDao-olio
     */
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
    
    /**
     * Uuden pelaajan luomiseen ja tallentamiseen käytettävä metodi.
     * 
     * @param name Käyttäjän antama merkkijono
     * @param points Käyttäjän antama kokonaisluku merkkijonomuodossa ("0")
     * @param highscore Käyttäjän antama kokonaisluku
     * @return true jos pelaajaa ei löydy tiedostosta, muuten false
     */
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
    
    /**
     * Listaa kaikki pelaajat.
     * @return pelaajat sisältävä List-olio
     */
    public List<Player> listAll() {
        return playerDao.getAll();
    }
    
    /**
     * Pelaajan ennätyspisteiden päivittävä metodi.
     * 
     * @param player Käyttäjän antama Player-olio
     * @param highscore Käyttäjän antama kokonaisluku (int)
     */
    public void updatePlayer(Player player, int highscore) {
        Player p = playerDao.findByName(player.getName());
        try {
            playerDao.update(p, highscore);
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Etsii yksittäisen käyttäjän tiedostosta nimen perusteella.
     * @param name Käyttäjän antama merkkijono
     * @return parametrina annettua nimeä vastaava Player-olio
     */
    public Player findPlayer(String name) {
        return playerDao.findByName(name);
    }
}
