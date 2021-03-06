package ui;

import dao.FilePlayerDao;
import domain.Ammunition;
import domain.Asteroid;
import domain.Player;
import domain.PlayerService;
import domain.Spaceship;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidsUi extends Application {
    
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    
    private PlayerService playerService;
    private AnimationTimer loop;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String playerFile = properties.getProperty("playerFile");
            
        FilePlayerDao playerDao = new FilePlayerDao(playerFile);
        
        playerService = new PlayerService(playerDao);
    }
    
    @Override
    public void start(Stage stage) {
        Button game = new Button("Start game");
        Button score = new Button("Scoreboard");
        TextField name = new TextField();
        Button back = new Button("back");
        Label startGameMessage = new Label("");
        Button stop = new Button("Quit game");
        List<Player> players = playerService.listAll();
        
        VBox startBox = new VBox();
        startBox.setAlignment(Pos.CENTER);
        startBox.setSpacing(20);
        startBox.getChildren().addAll(startGameMessage, name, game, score);
        
        VBox scoreBox = new VBox();
        HBox top = new HBox();
        top.setPadding(new Insets(30, 0, 20, 0));
        top.setAlignment(Pos.TOP_CENTER);
        top.getChildren().add(new Text("TOP 10 LEADERBOARD"));
        scoreBox.setAlignment(Pos.CENTER);
        HBox backbox = new HBox();
        backbox.setAlignment(Pos.BOTTOM_CENTER);
        backbox.setPadding(new Insets(10, 0, 30, 0));
        backbox.getChildren().add(back);
        
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparing(Player::getHighScore).reversed())
                .limit(10)
                .collect(Collectors.toList());
        sortedPlayers.forEach(player -> scoreBox.getChildren()
                .add(new Text(player.getName() + ": " + player.getHighScore() + "pts")));
        
        BorderPane startPanel = new BorderPane();
        startPanel.setPrefSize(600, 400);
        startPanel.setCenter(startBox);
        
        BorderPane scorePanel = new BorderPane();
        scorePanel.setPrefSize(600, 400);
        scorePanel.setCenter(scoreBox);
        scorePanel.setTop(top);
        scorePanel.setBottom(backbox);
        
        Pane gamePanel = new Pane();
        gamePanel.setPrefSize(600, 400);
        gamePanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Text text = new Text(20, 30, "");
        text.setFont(Font.font(20));
        text.setFill(Color.WHITE);
        gamePanel.getChildren().add(text);
        
        AtomicInteger points = new AtomicInteger();
        
        Spaceship ship = new Spaceship(WIDTH/2, HEIGHT/2);
        List<Ammunition> ammo = new ArrayList<>();
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(100), rnd.nextInt(100));
            asteroids.add(asteroid);
        }
        
        gamePanel.getChildren().add(ship.getUnit());
        asteroids.forEach(asteroid -> gamePanel.getChildren().add(asteroid.getUnit()));
        
        Scene startScene = new Scene(startPanel);
        Scene gameScene = new Scene(gamePanel);
        Scene scoreScene = new Scene(scorePanel);
        
        Map<KeyCode, Boolean> pressedButtons = new HashMap<>();
        
        gameScene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        gameScene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        game.setOnAction((event) -> {
            text.setText(name.getText() + "\nPoints: 0");
            
            if (name.getText().isEmpty()) {
                startGameMessage.setText("Player name cannot be empty!");
            } else if (playerService.createPlayer(name.getText(), Integer.toString(points.get()), 0)) {
                stage.setScene(gameScene);
                loop.start();
            } else {
                stage.setScene(gameScene);
                loop.start();
            }
        });
        score.setOnAction((event) -> {
            stage.setScene(scoreScene);
        });
        stop.setOnAction((event) -> {
            start(stage);
        });
        back.setOnAction((event) -> {
            stage.setScene(startScene);
        });
        
        stage.setScene(startScene);
        stage.show();
        
        loop = new AnimationTimer() {
            
            @Override
            public void handle(long present) {
                if (pressedButtons.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                
                if (pressedButtons.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                
                if (pressedButtons.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                
                if (pressedButtons.getOrDefault(KeyCode.DOWN, false)) {
                    ship.decelerate();
                }
                
                if (pressedButtons.getOrDefault(KeyCode.SPACE, false) && ammo.size() < 3) {
                    ship.shoot(ammo, ship);
                    
                    gamePanel.getChildren().add(ship.getAmmunition());
                }
                
                ship.move();
                asteroids.forEach(asteroid -> asteroid.move());
                ammo.forEach(ammunition -> ammunition.move());
                
                asteroids.forEach(asteroid -> {
                    if (ship.collide(asteroid)) {
                        Player p = playerService.findPlayer(name.getText());
                        if (p.getHighScore() < points.get()) {
                            playerService.updatePlayer(p, points.get());
                        }
                        stop();
                        gamePanel.getChildren().add(stop);
                    }
                });
                
                List<Ammunition> ammoToDelete = ammo.stream().filter(ammunition -> {
                    List<Asteroid> collided = asteroids.stream()
                        .filter(asteroid -> asteroid.collide(ammunition))
                        .collect(Collectors.toList());
                
                    if (collided.isEmpty()) {
                        return false;
                    }

                    collided.stream().forEach((col ->  {
                        asteroids.remove(col);
                        gamePanel.getChildren().remove(col.getUnit());
                    }));

                    return true;
                }).collect(Collectors.toList());
                
                ammoToDelete.forEach(ammunition -> {
                    gamePanel.getChildren().remove(ammunition.getUnit());
                    ammo.remove(ammunition);
                    text.setText(name.getText() + "\nPoints: " + points.addAndGet(100));
                });
                
                if (Math.random() < 0.005) {
                    Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
                    if (!asteroid.collide(ship)) {
                        asteroids.add(asteroid);
                        gamePanel.getChildren().add(asteroid.getUnit());
                    }
                }
                
            }
        };
    }
    
    public static void main(String[] args) {
      launch(AsteroidsUi.class);
    }
    
}
