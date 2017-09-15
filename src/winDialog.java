import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class winDialog {

    private Button quit,exit;
    private Alert dialog = new Alert(Alert.AlertType.INFORMATION);


public winDialog(String winner, Stage parent){

dialog.setTitle("Game Over");
dialog.setHeaderText(winner + " has won the game");
dialog.setContentText(winner + " could not extend\nthe field of" +
                               " play or has\nno more valid moves\n" +
                               "\nclick the OK button to exit the game");
Optional<ButtonType> exit = dialog.showAndWait();
if(exit.get() == ButtonType.OK){
    parent.close();
}

}


}
