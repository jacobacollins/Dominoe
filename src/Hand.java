

public class Hand {

    dominoeLibrary boneyard;
    Dominoe [] dom = new Dominoe[7];
    public Hand(dominoeLibrary library){

        boneyard = new dominoeLibrary();
        boneyard = library;
        this.dom = dom;

        startingHand();
    }

    public void printHand(Dominoe[] dominoe){
            for(int i = 0; i < dominoe.length; i++){
                System.out.println(dominoe[i]);
            }
    }

    public void startingHand(){
     for(int i = 0; i < dom.length; i++){
        dom[i] = boneyard.draw();
    }
    System.out.println();
    }

   
    public static void main(String[] args){
        dominoeLibrary boneyard = new dominoeLibrary();
       Hand hand = new Hand(boneyard);
    }

    }
