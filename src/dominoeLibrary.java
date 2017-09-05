

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.Random;


public class dominoeLibrary{

    public Dominoe[] dominoes;
    private int currentDominoe;
      int count = 0;
    public dominoeLibrary(){

        dominoes = new Dominoe[28];
        Image dominoeLib = new Image(getClass().getResourceAsStream("Dominoes.png"));
        PixelReader reader = dominoeLib.getPixelReader();
        WritableImage temp;

        final int width = 28;
        final int height = 54;
        final int rows = 7;
        final int columns = 6;
        final Canvas subImage  = new Canvas(width * 6,height * 6);
        int top = 0;
        int bottom = 0;



        for (int counter = 0; counter < 7; counter++) {
            while (top < bottom) {
                temp = new WritableImage(reader, top* (width + 4), bottom * (54 + 10), width, height);
                dominoes[count] = new Dominoe(bottom, top,temp);

                count++;
                ++top;
            }
            while (bottom == top) {
                temp = new WritableImage(reader, top * (width + 4), bottom * (54 + 10), width, height);
                dominoes[count] = new Dominoe(bottom, top,temp);
                count++;

                top = 0;
                ++bottom;
            }


        }
        //System.out.println(count);

    }


public void shuffle(){
        currentDominoe = 0;

        Random rand = new Random();
        for(int i = 0; i < dominoes.length; i++){
            int j = rand.nextInt(28);

            Dominoe temp = dominoes[i];
            dominoes[i] = dominoes[j];
            dominoes[j] = temp;
        }
}

public Dominoe draw(){
    Dominoe dominoe = null;
if(dominoes != null ){
    while(dominoe == null){
        int next = (int)(Math.random()*dominoes.length);
        dominoe = dominoes[next];
        dominoes[next] = null;
    }
   // System.out.print(dominoe.toString() + " ");
   return dominoe;
}
    return null;
}

public void displayLibrary(){
    System.out.println();
    for(Dominoe d: dominoes){
        System.out.println(d);
    }
    System.out.println();
}
/*
    public static void main(String[] args){

    dominoeLibrary library = new dominoeLibrary();
    library.shuffle();

    System.out.print("current hand = ");

   for(int i = 0; i < 5; i++){

    library.draw();
    if(i%5 == 0 && i != 0){
        System.out.println();
    }
   }
    library.displayLibrary();



    }
*/


}

