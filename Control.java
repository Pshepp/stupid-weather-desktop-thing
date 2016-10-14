package ass;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Control{
  @FXML
  private Label loc;
  @FXML
  private Label temp;
  @FXML
  private Label tempH;
  @FXML
  private Label tempL;
  @FXML
  private Label conditions;
  @FXML
  private Label humid;
 
   
  @FXML
  private void initialize() {	
	setIsh();
  	Timeline tL = new Timeline(new KeyFrame(Duration.hours(1),ae -> setIsh()));
    tL.setCycleCount(Animation.INDEFINITE);
    tL.play();
  }

  private void setIsh(){
	  Info.Update();
	  temp.setText(Info.temp+(char)0x00B0);
      tempH.setText((char)0x2191+Info.temH+(char)0x00B0);
      tempL.setText((char)0x2193+Info.temL+(char)0x00B0);
      loc.setText(Info.loc);
      conditions.setText(Info.cond);
      humid.setText(Info.hum+"%");	  
  }
}
