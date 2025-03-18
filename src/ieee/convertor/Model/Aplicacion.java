/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieee.convertor.Model;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andres Monsalve
 */
public class Aplicacion extends Application{
    public static void main(String[] args) {
        launch(args);
    }
	    
    @Override
    public void start(Stage primaryStage) throws Exception {
	        
	Parent root = FXMLLoader.load(getClass().getResource("/ieee/convertor/View/IEEEConverterView.fxml"));

        primaryStage.setTitle("IEEE - Converter");
        primaryStage.setScene (new Scene (root));
        primaryStage.setResizable(false);
        primaryStage.show();    
    }
}
