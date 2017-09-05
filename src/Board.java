import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;

public class Board {

    private Canvas board;
    private Dominoe[] startingHand;
    private WritableImage currentDominoe;
    private GraphicsContext gc;

    public Board(Canvas board, Dominoe[] startingHand, GraphicsContext gc) {
        this.board = board;
        this.startingHand = startingHand;
        this.currentDominoe = currentDominoe;
        this.gc = gc;
    }


    public void DrawHand(Dominoe[] startingHand){
        for(int i = 0; i < startingHand.length; i++) {

            drawRotatedImage(gc, startingHand[i].getDominoPicture(),90, ((board.getWidth() /2)-180) + i * 60, board.getHeight() - 54);
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
drawRotatedImage(gc,currentDominoe, 90 * isFlipped, (board.getWidth()/2 + (26 * i)), 54 + j * 26);
}


public void flipDominoe(WritableImage flip, int currentDom, Dominoe flipped, int right, int left, int isFlipped){
    drawRotatedImage(gc, flip, 90 * isFlipped, ((board.getWidth() /2)-178) + currentDom * 60, board.getHeight() - 54);
    flipped.setLeftNumber(right);
    flipped.setRightNumber(left);
}

}
