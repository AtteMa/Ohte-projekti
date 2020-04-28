package domain;


/**
 * Ohjelman pelaajaa edustava luokka.
 */

public class Player {
    private String name;
    private String points;
    
    public Player(String name, String points) {
        this.name = name;
        this.points = points;
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
