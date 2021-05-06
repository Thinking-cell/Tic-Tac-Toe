import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class with all the functionality
 * @author Ranvir Singh , 000819787
 */
public class TicTacToe {

    List<List<Integer>> gameRec = new ArrayList<List<Integer>>();

    public TicTacToe(List<List<Integer>> gameRec) {
        this.gameRec = gameRec;
    }

    // Draw board
    public void displayBoard(GraphicsContext gc){


        gc.setStroke(Color.LIMEGREEN);
        gc.setLineWidth(8);
        gc.strokeLine(100,200,400,200);
        gc.strokeLine(100,300,400,300);
        gc.strokeLine(200,100,200,400);
        gc.strokeLine(300,100,300,400);


    }
    // Displayong all setpieces on board
    public void displaySetpieces(GraphicsContext gc){
        for(int turnValue=0;turnValue<=1;turnValue++) {// for both X and O
            for (int i=0; i<3; i++) {// Row access
                for (int j = 0; j < 3; j++) {// element access
                    if(turnValue==0) {//for O
                        if ((gameRec.get(i)).get(j)==turnValue) {
                            drawSP_O(100+(j*100), 100+(i*100), gc);
                        }
                    }
                    if(turnValue==1){//for X
                        if ((gameRec.get(i)).get(j)==turnValue) {
                            //test
                            //System.out.println("   "+(gameRec.get(i)).get(j)+" i:"+i+" j:"+j);
                            drawSP_X(100+(j*100), 100+(i*100), gc);}
                    }
                }
            }
        }
    }


    // To draw O setpiece
    private void drawSP_O(double x, double y, GraphicsContext gc){
        gc.setFill(Color.LINEN);
        gc.fillRect(x+5,y+5,90,90);
        gc.setLineWidth(5);
        gc.setStroke(Color.ORCHID);
        gc.strokeOval(x+10,y+10,80,80);

    }
    // to display score of 2 players
    public void displayScores(int[] scoreBoard, GraphicsContext gc){
        String string;
        string="Player O : ";
        gc.setLineWidth(1.5);
        gc.setStroke(Color.ORCHID);
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,17));
        gc.strokeText(string+scoreBoard[0],400,30);
        string="Player X : ";
        gc.setLineWidth(1.5);
        gc.setStroke(Color.SALMON);
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,17));
        gc.strokeText(string+scoreBoard[1],400,60);
    }

    // To draw X setpiece
    private void drawSP_X(double x, double y, GraphicsContext gc){
        gc.setFill(Color.LINEN);
        gc.fillRect(x+5,y+5,90,90);
        gc.setLineWidth(5);
        gc.setStroke(Color.SALMON);
        gc.strokeLine(x+10,y+10,x+80,y+80);
        gc.strokeLine(x+80,y+10,x+10,y+80);

    }

    public void wipeBoard(GraphicsContext gc){
        gc.setFill(Color.LINEN);
        gc.fillRect(0,0,500,500);
        gc.setStroke(Color.ROYALBLUE);
        gc.setLineWidth(14);
        gc.strokeRect(0,0,500,500);}

    public void whoseTurn(boolean turn,GraphicsContext gc){
        String string;
        if(turn){string="Turn : O";}else{string="Turn : X";}
        gc.setLineWidth(2.7);
        gc.setStroke(Color.INDIGO);
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC,35));
        gc.strokeText(string,50,50);

    }

    public int findElementIndex(double x){
        int X = (int) x;
        X = X / 100;
        if(x>=100&&x<=400){

            return X-1;
        }

        return -1;



    }

    public int findRowIndex(double y){
        int Y = (int) y;
        Y = Y / 100;
        if(y>=100&&y<=400) {

            return Y-1;
        }

        return  -1;
    }

    // Victoyr box display
    public void displayVictoryBox(boolean turnOX){
        String playerNum="X";

        if(turnOX){playerNum="O";}
        Box victoryBox =new Box();
        victoryBox.display( "You Win" ,playerNum+" Won!!!");

    }

    // To check winner
    public boolean checkWinner(GraphicsContext gc){

        boolean returnV=false;
        gc.setStroke(Color.CRIMSON);
        gc.setLineWidth(5);
        for(int turnV=0;turnV<=1;turnV++){
            if((gameRec.get(0)).get(0)==turnV&&(gameRec.get(0)).get(1)==turnV&&(gameRec.get(0)).get(2)==turnV){returnV=true;
            gc.strokeLine(150,150,350,150);}
            if((gameRec.get(0)).get(0)==turnV&&(gameRec.get(1)).get(0)==turnV&&(gameRec.get(2)).get(0)==turnV){returnV=true;
                gc.strokeLine(150,150,150,350);}
            if((gameRec.get(0)).get(0)==turnV&&(gameRec.get(1)).get(1)==turnV&&(gameRec.get(2)).get(2)==turnV){returnV=true;
                gc.strokeLine(150,150,350,350);}
            if((gameRec.get(1)).get(0)==turnV&&(gameRec.get(1)).get(1)==turnV&&(gameRec.get(1)).get(2)==turnV){returnV=true;
                gc.strokeLine(150,250,350,250);}
            if((gameRec.get(2)).get(0)==turnV&&(gameRec.get(2)).get(1)==turnV&&(gameRec.get(2)).get(2)==turnV){returnV=true;
                gc.strokeLine(150,350,350,350);}
            if((gameRec.get(0)).get(1)==turnV&&(gameRec.get(1)).get(1)==turnV&&(gameRec.get(2)).get(1)==turnV){returnV=true;
                gc.strokeLine(250,150,250,350);}
            if((gameRec.get(0)).get(2)==turnV&&(gameRec.get(1)).get(2)==turnV&&(gameRec.get(2)).get(2)==turnV){returnV=true;
                gc.strokeLine(350,150,350,350);}
            if((gameRec.get(2)).get(0)==turnV&&(gameRec.get(1)).get(1)==turnV&&(gameRec.get(0)).get(2)==turnV){returnV=true;
                gc.strokeLine(150,350,350,150);}

        }
    return returnV;
    }



}
