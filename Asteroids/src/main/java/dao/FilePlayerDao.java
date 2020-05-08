package dao;

import domain.Player;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Pelaajan pysyväistallennuksen hoitava luokka.
 */

public class FilePlayerDao implements PlayerDao {
    private List<Player> players;
    private String file;
    
    public FilePlayerDao(String file) throws Exception {
        this.players = new ArrayList<>();
        this.file = file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(" ");
                Player p = new Player(parts[0], parts[1], Integer.parseInt(parts[2]));
                players.add(p);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player: players) {
                writer.write(player.getName()
                        + " " 
                        + player.getPoints() 
                        + " "
                        + player.getHighScore()
                        + "\n");
            }
        }
    }
    
    @Override
    public Player findByName(String name) {
        return players.stream()
                .filter(p -> p.getName()
                .equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Player update(Player player, int highscore) throws Exception {
        player.setHighScore(highscore);
        save();
        return player;
    } 
    
    public Player create(Player player) throws Exception {
        players.add(player);
        save();
        return player;
    }
    
    @Override
    public List<Player> getAll() {
        return players;
    }
    
}
