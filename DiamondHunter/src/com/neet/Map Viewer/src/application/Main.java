package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXMLLoader;

//This is the main class that runs the application
public class Main extends Application {
	public static Stage primaryStage;
	public static MapDisplay mapDisplay;
	
	public BorderPane root;
	public TilePane tile;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Map Viewer");

        initialise();
        
		showMap();
		}
//this method calls the methods in MapDisplay to load the map and images files then renders them on a tile pane, which then gets displayed onto the application
	public void showMap() {
		mapDisplay = new MapDisplay();
		mapDisplay.loadMapFile("./testmap.map");
		mapDisplay.loadImagesFiles("./testtileset.gif", "./items.gif");
		
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("test.fxml"));
				
				tile = (TilePane) loader.load();
			} catch (IOException e)
				{
					e.printStackTrace();
				}
			
		tile.setPrefColumns(mapDisplay.numCols);
		tile.setPrefRows(mapDisplay.numRows);
		mapDisplay.initialiseCanvas();
		tile.getChildren().add(mapDisplay.currentCanvas);
		
		root.setCenter(tile);
		
		}
//Initialises the application GUI based on the fxml file
	public void initialise() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Hud.fxml"));
            root = (BorderPane) loader.load();

            Scene scene = new Scene(root);
          
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
	
