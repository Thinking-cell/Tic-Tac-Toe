import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Array.get;

/**
 * Main view for the game
 * @author Ranvir Singh,000819787
 */

public class Main extends Application {
    // Records of Player and game
    static List<List<Integer>> gameRec = new ArrayList<List<Integer>>();

    //turn of player
    // By default O Plays first ie 0=true, X=false
    static boolean TurnOX= true;
    static int[] scoreBoard ={0,0};


    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        // Setting main stage

        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.PALEGOLDENROD,null,null)));
        Canvas canvas = new Canvas(500,500);

        // Main code starts here
        // for graphics
        GraphicsContext gc=canvas.getGraphicsContext2D();
        // to record values
        gameRec.add(0, Arrays.asList(9,9,9));
        gameRec.add(1, Arrays.asList(9,9,9));
        gameRec.add(2, Arrays.asList(9,9,9));


        // Layout
        VBox MBox=new VBox(30);
        MBox.setAlignment(Pos.CENTER);
        MBox.setPadding(new Insets(10,20,10,10));


        //head
        Label head = new Label("    TIC TAC TOE!!");
        head.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, FontPosture.REGULAR,55));
        head.setTextFill(Color.ORANGERED);
        // h box containing game
        HBox secondary = new HBox(15);
        secondary.setAlignment(Pos.BOTTOM_RIGHT);
        //left side options vbox
        VBox leftMenu=new VBox(10);
        //reset button
        Button buttonResetGame=new Button("Reset\nBoard");
        buttonResetGame.setFont(Font.font("Arial", null, FontPosture.ITALIC,30));
        buttonResetGame.setTextFill(Color.PALEVIOLETRED);
        buttonResetGame.setOnAction(e->{
            TicTacToe reset=new TicTacToe(gameRec);
            for(int i =0;i<3;i++){gameRec.set(i, Arrays.asList(9,9,9));}
            reset.wipeBoard(gc);
            reset.displayBoard(gc);
            reset.displaySetpieces(gc);
            reset.whoseTurn(TurnOX,gc);
            reset.displayScores(scoreBoard,gc);
            Box resetPrompt=new Box();
            resetPrompt.display("Game Restarted", " GAME RESET COMPLETE!");

        });
        // Score reset
        Button buttonResetScores=new Button("Reset\nScores");
        buttonResetScores.setFont(Font.font("Arial", null, FontPosture.ITALIC,30));
        buttonResetScores.setTextFill(Color.HOTPINK);
        buttonResetScores.setOnAction(e->{
            TicTacToe reset=new TicTacToe(gameRec);
            reset.wipeBoard(gc);
            reset.displayBoard(gc);
            reset.displaySetpieces(gc);
            reset.whoseTurn(TurnOX,gc);
            scoreBoard[0]=0;
            scoreBoard[1]=0;
            reset.displayScores(scoreBoard,gc);

        });
        leftMenu.setAlignment(Pos.CENTER);


        //Distinguishing canvas
        gc.setFill(Color.LINEN);
        gc.fillRect(0,0,500,500);
        gc.setStroke(Color.ROYALBLUE);
        gc.setLineWidth(14);
        gc.strokeRect(0,0,500,500);
        //adding childrens
        leftMenu.getChildren().addAll(buttonResetGame,buttonResetScores);
        secondary.getChildren().addAll(leftMenu,canvas);

        MBox.getChildren().addAll(head,secondary);
        root.getChildren().add(MBox);

        //Initialization of game board
        TicTacToe Start=new TicTacToe(gameRec);
        Start.wipeBoard(gc);
        Start.displayBoard(gc);
        Start.whoseTurn(TurnOX,gc);
        Start.displayScores(scoreBoard,gc);
        //Input using Mouse
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                TicTacToe Board=new TicTacToe(gameRec);
                //getting list indexes
                int row = Board.findRowIndex(e.getY());
                int element = Board.findElementIndex(e.getX());

                if(row!=-1&&element!=-1){
                    if((gameRec.get(row)).get(element)==9) {
                        if (TurnOX) {
                            (gameRec.get(row)).set(element, 0);

                        } else {
                            (gameRec.get(row)).set(element, 1);
                        }

                        TurnOX = !TurnOX;
                    }else{Box error = new Box();error.display("Error!", "Space Already Occupied!");}

                }else{}

                TicTacToe setPiece = new TicTacToe(gameRec);
                setPiece.wipeBoard(gc);
                setPiece.displayBoard(gc);
                setPiece.whoseTurn(TurnOX,gc);
                setPiece.displaySetpieces(gc);
                setPiece.displayScores(scoreBoard,gc);
                // if win then reset code block

                if(setPiece.checkWinner(gc)){
                    setPiece.displayVictoryBox(!TurnOX);
                    if(!TurnOX){scoreBoard[0]=scoreBoard[0]+1;}
                    if(TurnOX){scoreBoard[1]=scoreBoard[1]+1;}
                    for(int i =0;i<3;i++){gameRec.set(i, Arrays.asList(9,9,9));}
                    setPiece.wipeBoard(gc);
                    setPiece.displayBoard(gc);
                    setPiece.whoseTurn(TurnOX,gc);
                    setPiece.displayScores(scoreBoard,gc);
                }

                //if no win then reset code block
                int NoWin=0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if((gameRec.get(i)).get(j)!=9){NoWin=NoWin+1;}}}
                if(NoWin==9){
                    for(int i =0;i<3;i++){gameRec.set(i, Arrays.asList(9,9,9));}
                    setPiece.wipeBoard(gc);
                    setPiece.displayBoard(gc);
                    setPiece.whoseTurn(TurnOX,gc);
                    setPiece.displayScores(scoreBoard,gc);
                    Box reseetBoard= new Box();
                    reseetBoard.display("NoWin reset ","Oh Man, Nobody Wins,\n      Board Resetted");
                }

            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        Scene scene = new Scene(root,700,630);
        stage.setTitle("TicTacToe by 000819787");
        stage.setScene(scene);
        stage.show();

    }
}
