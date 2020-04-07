package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidsUi extends Application {
    
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    
    public void start(Stage stage) {
        Button game = new Button("Start game");
        Button score = new Button("Scoreboard");
        Button back = new Button("back");
        Button back2 = new Button("back");
        
        VBox startBox = new VBox();
        startBox.setAlignment(Pos.CENTER);
        startBox.setSpacing(20);
        startBox.getChildren().addAll(game, score);
        
        VBox gameBox = new VBox();
        gameBox.setAlignment(Pos.CENTER);
        gameBox.getChildren().addAll(new Text("Here will be a game!"), back);
        
        VBox scoreBox = new VBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.getChildren().addAll(new Text("Here will be a scoreboard"), back2);
        
        BorderPane startPanel = new BorderPane();
        startPanel.setPrefSize(600, 400);
        startPanel.setCenter(startBox);
        
        BorderPane gamePanel = new BorderPane();
        gamePanel.setPrefSize(600, 400);
        gamePanel.setCenter(gameBox);
        
        BorderPane scorePanel = new BorderPane();
        scorePanel.setPrefSize(600, 400);
        scorePanel.setCenter(scoreBox);
        
        Scene startScene = new Scene(startPanel);
        Scene gameScene = new Scene(gamePanel);
        Scene scoreScene = new Scene(scorePanel);
        
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
    }
    
    public static void main(String[] args) {
      launch(AsteroidsUi.class);
    }
    
}
