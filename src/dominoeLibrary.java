

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.Random;


public class dominoeLibrary{

    public ArrayList<Dominoe> dominoes;
    private int currentDominoe;
    private Image blank;
    private WritableImage blankDominoePic;
    private Dominoe blankDominoe;
    private  int count = 0;
    public dominoeLibrary(){

        dominoes = new ArrayList<Dominoe>(28);
        Image dominoeLib = new Image(getClass().getResourceAsStream("Dominoes.png"));
        blank = new Image(getClass().getResourceAsStream("blank.png"));

        PixelReader read = blank.getPixelReader();

        PixelReader reader = dominoeLib.getPixelReader();
        WritableImage temp;

        final int width = 28;
        final int height = 54;
        final int rows = 7;
        final int columns = 6;
        final Canvas subImage  = new Canvas(width * 6,height * 6);
        int top = 0;
        int bottom = 0;

        blankDominoePic = new WritableImage(read,0, 0,28 ,54);


        blankDominoe = new Dominoe(-2,-2, getBlankDominoePic());


        for (int counter = 0; counter < 7; counter++) {
            while (top < bottom) {
                temp = new WritableImage(reader, top* (width + 4), bottom * (54 + 10), width, height);
                dominoes.add(count, new Dominoe(bottom, top,temp));

                count++;
                top++;
            }
            while (top == bottom) {
                temp = new WritableImage(reader, top * (width + 4), bottom * (54 + 10), width, height);
                dominoes.add(count,new Dominoe(bottom, top,temp));
                count++;

                top = 0;
                ++bottom;
            }


        }
      //  System.out.println(count);

    }


    public WritableImage getBlankDominoePic() {
        return blankDominoePic;
    }

    public Dominoe getBlankDominoe() {
        return blankDominoe;
    }

    public void shuffle(){
        currentDominoe = 0;

        Random rand = new Random();
        for(int i = 0; i < dominoes.size(); i++){
            int j = rand.nextInt(28);

            Dominoe temp = dominoes.get(i);
            dominoes.set(i, dominoes.get(j));
            dominoes.set(j, temp);

        }
}

public Dominoe draw(){
    Dominoe dominoe = null;
if(dominoes != null ){
    while(dominoe == null){
        int next = (int)(Math.random()*dominoes.size());
        dominoe = dominoes.get(next);
        dominoes.set(next, null);
    }
    //System.out.print(dominoe.toString() + " ");
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

