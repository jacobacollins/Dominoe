import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    dominoeLibrary boneyard;
    Dominoe [] dom = new Dominoe[7];
    ArrayList<Dominoe> doms;
    public Hand(dominoeLibrary library){


        boneyard = library;



        startingHand();
        doms = new ArrayList<Dominoe>(Arrays.asList(dom));

    }

    public void printHand(ArrayList<Dominoe> doms){

            System.out.println(doms.toString());

    }

    public void startingHand(){
        for(int i = 0; i < dom.length; i++){
            dom[i] = boneyard.draw();
        }
        System.out.println();
    }


}