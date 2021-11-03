/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The MainApplication for the GUI
 * @author Mikita Belausau, Harpreet Randhawa
 */
public class MainApplication extends Application {
    /**
     * starts the GUI
     * @param stage
     * @throws IOException
     * @author Mikita Belausau
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the program
     * @param args
     * @author Mikita Belausau
     */
    public static void main(String[] args) {
        launch();
    }
}