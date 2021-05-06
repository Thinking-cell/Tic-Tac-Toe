import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * PopupBox for victory and other functions
 * @author Ranvir Singh,000819787
 */
public class Box {


    public void display(String title, String text){

        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        // Upper Vbox
        VBox Main=new VBox(20);
        Main.setAlignment(Pos.CENTER);
        Main.setAlignment(Pos.CENTER);

        //labels and buttons
        Label Text =new Label(text);
        Text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.REGULAR,26));
        Text.setTextFill(Color.TOMATO);
        Button closeButton= new Button("  Close\nWindow");
        closeButton.setFont(Font.font("Arial", FontWeight.BOLD, null,14));
        closeButton.setTextFill(Color.MAGENTA);
        closeButton.setOnAction(e -> stage.close());// action of close button

        //setting stage

        StackPane pane = new StackPane();
        pane.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,null,null)));
        pane.setPadding(new Insets(10,10,10,10));

        Main.getChildren().addAll(Text,closeButton);
        pane.getChildren().add(Main);
        Scene scene = new Scene(pane,400,340);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }
}

