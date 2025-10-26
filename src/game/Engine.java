package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

import java.net.URL;
import java.lang.IllegalStateException;

import game.math.Direction;

public class Engine extends Application {

	private Stage stage;
	private GameController mainController;
	private UpdateTimer updateTimer;
	
	final String APPNAME = "HackPSU Project";
	final String GUIPATH = "/res/fxml/StartScreen.fxml";
	//final String ICONPATH = "/res/image/rh-icon.png";

	final int MINWIDTH = 900;
	final int MINHEIGHT = 750;

	@Override
	public void start(Stage pStage) {
		Parent root = loadGUI(GUIPATH);
		Scene mainScene = new Scene(root);
		pStage.setScene(mainScene);
		//pStage.getIcons().add(makeImage(ICONPATH));
		pStage.setMinWidth(MINWIDTH);
		pStage.setMinHeight(MINHEIGHT);
		pStage.setTitle(APPNAME);
		pStage.show();
		stage = pStage;

		// kb input
		mainScene.setOnKeyPressed(this::handleKeyPress);
		root.requestFocus();

		updateTimer = new UpdateTimer(this);
		updateTimer.start();
	}

	private void handleKeyPress(KeyEvent e) {
		switch (e.getCode()) {
			case W -> {
				mainController.getLevelRenderer().getLevel().movePlayer(Direction.UP);
			}
			case A -> {
				mainController.getLevelRenderer().getLevel().movePlayer(Direction.LEFT);
			}
			case S -> {
				mainController.getLevelRenderer().getLevel().movePlayer(Direction.DOWN);
			}
			case D -> {
				mainController.getLevelRenderer().getLevel().movePlayer(Direction.RIGHT);
			}
		}
	}

	private Parent loadGUI(String path) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Parent gui = loader.load();
			
			// Handle different controller types
			Object controller = loader.getController();
			if (controller instanceof GameController) {
				mainController = (GameController) controller;
				mainController.setEngineHandle(this);
			} else if (controller instanceof StartController) {
				StartController startController = (StartController) controller;
				startController.setEngineHandle(this);
			}
			
			return gui;
		} 
		catch (Exception ex) {
	        ex.printStackTrace();
	        throw new RuntimeException("Failed to load FXML", ex);
	    }
	}
 
	public static Image makeImage(String path) {
		return new Image(Engine.class.getResource(path).toExternalForm());
	}

	public Stage getStage() {
		return (stage != null && stage.isShowing())? stage : null;
	}

	public UpdateTimer getUpdateTimer() {
		return updateTimer;
	}
}