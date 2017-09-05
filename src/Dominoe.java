

import com.sun.java.browser.plugin2.DOM;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Dominoe{

    private int leftNumber;
    private int rightNumber;
    private WritableImage dominoPicture;

    public Dominoe(){}

    public Dominoe(int leftNumber, int rightNumber, WritableImage dominoPicture){
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.dominoPicture = dominoPicture;

    }


    public int getLeftNumber(){
        return this.leftNumber;
    }

    public int getRightNumber(){
        return this.rightNumber;
    }

    public void setRightNumber(int rightNumber){
        this.rightNumber = rightNumber;
    }
    public void setLeftNumber(int leftNumber){
        this.leftNumber = leftNumber;
    }

    public WritableImage getDominoPicture() {
        return dominoPicture;
    }

    public void setDominoPicture(WritableImage dominoPicture) {
        this.dominoPicture = dominoPicture;
    }

    public String toString(){

        return "[" + leftNumber + "]|[" + rightNumber + "]"  ;
    }



    public static void main(String[] args) throws IOException {


       Application.launch(args);
    }


}
