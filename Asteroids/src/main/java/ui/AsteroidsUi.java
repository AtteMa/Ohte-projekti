package ui;

import domain.Unit;
import domain.Spaceship;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidsUi extends Application {
    
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    
    @Override
    public void start(Stage stage) {
        Button game = new Button("Start game");
        Button score = new Button("Scoreboard");
        Button back = new Button("back");
        Button back2 = new Button("back");
        
        VBox startBox = new VBox();
        startBox.setAlignment(Pos.CENTER);
        startBox.setSpacing(20);
        startBox.getChildren().addAll(game, score);
        
        VBox scoreBox = new VBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.getChildren().addAll(new Text("Here will be a scoreboard"), back2);
        
        BorderPane startPanel = new BorderPane();
        startPanel.setPrefSize(600, 400);
        startPanel.setCenter(startBox);
        
        BorderPane scorePanel = new BorderPane();
        scorePanel.setPrefSize(600, 400);
        scorePanel.setCenter(scoreBox);
        
        Pane gamePanel = new Pane();
        gamePanel.setPrefSize(600, 400);
        gamePanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Spaceship ship = new Spaceship(150, 100);
        
        gamePanel.getChildren().add(ship.getUnit());
        
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
            stage.setScene(gameScene);
        });
        score.setOnAction((event) -> {
            stage.setScene(scoreScene);
        });
        back.setOnAction((event) -> {
            stage.setScene(startScene);
        });
        back2.setOnAction((event) -> {
            stage.setScene(startScene);
        });
        
        stage.setScene(startScene);
        stage.show();
        
        new AnimationTimer() {
            
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
                
                ship.move();
            }
        }.start();
    }
    
    public static void main(String[] args) {
      launch(AsteroidsUi.class);
    }
    
}
