package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;

public class StartController {
    
    private Engine engine;
    
    @FXML
    private void startGame() {
        try {
            // Load the main game FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/GameGui.fxml"));
            Parent mainGameRoot = loader.load();
            
            // Get the current stage and replace the scene
            Stage stage = (Stage) engine.getStage();
            Scene mainGameScene = new Scene(mainGameRoot);
            stage.setScene(mainGameScene);
            
            // Get the game controller and set up the engine reference
            GameController gameController = loader.getController();
            gameController.setEngineHandle(engine);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load main game: " + e.getMessage());
        }
    }
    
    @FXML
    private void quitGame() {
        Platform.exit();
    }
    
    // Setter for engine reference
    public void setEngineHandle(Engine pEngine) {
        engine = pEngine;
    }
}
