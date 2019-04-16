package it.polito.tdp.controller;
	
import it.polito.tdp.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PowerOutages.fxml"));
			
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,559,530);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Carico Controller
			PowerOutagesController controller = loader.getController();
			
			//Setto il modello
			Model model = new Model();
			controller.setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
