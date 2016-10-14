package ass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.initStyle(StageStyle.TRANSPARENT);
    Parent root = FXMLLoader.load(getClass().getResource("weather.fxml"));
    root.setStyle( "-fx-background-color: rgba(255, 255, 255, 0);");
    Rectangle2D sBounds = Screen.getPrimary().getVisualBounds();
    stage.setX(sBounds.getMinX() + sBounds.getWidth()-200);
    stage.setY(0);
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    stage.setScene(scene);
    stage.show();
    stage.toBack();//still need to figure out how to keep window behind all others
  }

  public static void main(String[] args) {
    launch(args);
  }

}
