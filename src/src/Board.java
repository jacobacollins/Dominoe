import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Board {


    private int rightX = 1;
    private int rightY = 1;
    private int leftY = -1;
    private int leftX =1;
    private Canvas board;
    private Dominoe[] startingHand;
    private WritableImage currentDominoe;
    private GraphicsContext gc;
    private Dominoe centerDominoe;
    private Dominoe rightDominoe;
    private Dominoe rightTemp = new Dominoe(0,0,null);
    private Dominoe leftDominoe;



    public Board(Canvas board, Dominoe[] startingHand, GraphicsContext gc) {
        this.board = board;
        this.startingHand = startingHand;
        this.currentDominoe = currentDominoe;
        this.gc = gc;
    }


    public void setRightDominoe(Dominoe rightDominoe) {
        this.rightDominoe = rightDominoe;
    }


    public Dominoe getRightDominoe() {
        return rightDominoe;
    }



    public boolean isLegal(int firstNumber, int secondNumber){
        if(firstNumber == secondNumber){
            return true;
        }
        else if(firstNumber == 0 || secondNumber == 0){
            return  true;
        }
        else{return false;}
    }

    public void setCenterDominoe(Dominoe centerDominoe) {
        this.centerDominoe = centerDominoe;
    }

    public Dominoe getCenterDominoe() {
        return centerDominoe;
    }

    public Dominoe getLeftDominoe() {
        return leftDominoe;
    }

    public void setLeftDominoe(Dominoe leftDominoe) {
        this.leftDominoe = leftDominoe;
    }
    public void setRightX(int rightX) {
        this.rightX = rightX;
    }

    public void setRightY(int rightY) {
        this.rightY = rightY;
    }

    public void setLeftY(int leftY) {
        this.leftY = leftY;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public int getRightX() {

        return rightX;
    }

    public int getRightY() {
        return rightY;
    }

    public int getLeftY() {
        return leftY;
    }

    public int getLeftX() {
        return leftX;
    }

    public void DrawHand(ArrayList<Dominoe> startingHand){
        for(int i = 0; i < startingHand.size(); i++) {

            drawRotatedImage(gc, startingHand.get(i).getDominoPicture(),90, ((board.getWidth() /2)-178) + i * 60, board.getHeight() - 54);
           // gc.drawImage(startingHand[i].getDominoPicture(), ((board.getWidth() /2)-140) + i * 40, board.getHeight() - 54);

        }
}
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

public void DrawBoardDominoe(WritableImage currentDominoe, int i, int j, int isFlipped){
drawRotatedImage(gc,currentDominoe, 90 * isFlipped, (board.getWidth()/2 + (28 * i) ), 54 + j * 26);
}


public void flipDominoe(WritableImage flip, int currentDom, Dominoe flipped, int right, int left, int isFlipped){
    drawRotatedImage(gc, flip, 90 * isFlipped, ((board.getWidth() /2)-178) + currentDom * 60, board.getHeight() - 54);
    flipped.setLeftNumber(right);
    flipped.setRightNumber(left);
}




}
