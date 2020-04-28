/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.PlayerDao;

/**
 *
 * @author atte
 */
public class PlayerService {
    private PlayerDao playerDao;
    
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
    
    public boolean createPlayer(String name, String points, String highScore) {
        if (playerDao.findByName(name) != null) {
            return false;
        }
        Player player = new Player(name, points, highScore);
        try {
            playerDao.create(player);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public Player findPlayer(String name) {
        return playerDao.findByName(name);
    }
}
