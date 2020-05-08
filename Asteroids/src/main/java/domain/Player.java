package domain;


/**
 * Ohjelman pelaajaa edustava luokka.
 */

public class Player {
    private String name;
    private String points;
    private int highScore;
    
    /**
     * Kostruktori Player-oliolle
     * @param name Käyttäjän antama merkkijono
     * @param points Käyttäjän antama merkkijono
     * @param highScore Käyttäjän antama kokonaisluku
     */
    public Player(String name, String points, int highScore) {
        this.name = name;
        this.points = points;
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        
        Player other = (Player) obj;
        return name.equals(other.name);
    }
}
