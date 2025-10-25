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

import java.io.IOException;

import java.net.URL;
import java.lang.IllegalStateException;

public class Engine extends Application {

	private Stage stage;
	private GameController mainController;
	private UpdateTimer updateTimer;
	
	final String APPNAME = "HackPSU Project";
	final String GUIPATH = "/res/fxml/GameGui.fxml";
	//final String ICONPATH = "/res/image/rh-icon.png";

	@Override
	public void start(Stage pStage) {
		Parent root = loadGUI(GUIPATH);
		Scene mainScene = new Scene(root);
		pStage.setScene(mainScene);
		//pStage.getIcons().add(makeImage(ICONPATH));
		pStage.setTitle(APPNAME);
		pStage.show();
		stage = pStage;

		updateTimer = new UpdateTimer(this);
		updateTimer.start();
	}

	Parent loadGUI(String path) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Parent gui = loader.load();
			mainController = loader.getController();
			mainController.setEngineHandle(this);
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