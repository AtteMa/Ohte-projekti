package dao;

import domain.Player;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
                Player p = new Player(parts[0], parts[1], parts[2]);
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
    
    public Player create(Player player) throws Exception {
        players.add(player);
        save();
        return player;
    }
    
    @Override
    public List<Player> getAll() {
        players.sort(Comparator.comparing(Player::getHighScore).reversed());
        return players;
    }
    
}
