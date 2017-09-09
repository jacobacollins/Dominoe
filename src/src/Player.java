import com.sun.java.browser.plugin2.DOM;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private Hand h;
    private dominoeLibrary boneyard;
    private Board board;
    private int playerTurn = 1;
    boolean check = true;
    private Dominoe temp;

    public Player(dominoeLibrary boneyard) {
        this.boneyard = boneyard;
        h = new Hand(boneyard);

    }



    public Hand getH() {
        return h;
    }



    public Dominoe leftComputerMove(ArrayList<Dominoe> computerHand, Dominoe currentDominoe, Board b, int dominoe, dominoeLibrary lib) {

        if ( dominoe < computerHand.size() && computerHand.get(dominoe).getRightNumber() == currentDominoe.getLeftNumber()) {
            temp = computerHand.get(dominoe);
            computerHand.set(dominoe, lib.draw());
            System.out.println("computer picked dominoe" + temp);
            return temp;

        } else if (dominoe < computerHand.size() && computerHand.get(dominoe).getRightNumber() != currentDominoe.getLeftNumber()) {
            if (check == true) {
                check = false;
                b.flipDominoe(computerHand.get(dominoe).getDominoPicture(), dominoe, computerHand.get(dominoe), computerHand.get(dominoe).getRightNumber(), computerHand.get(dominoe).getLeftNumber(), -1);

                return leftComputerMove(computerHand, currentDominoe, b, dominoe, lib);
            } else if (check == false) {
                check = true;
                return leftComputerMove(computerHand, currentDominoe, b, dominoe + 1, lib);
            }
        }
        System.out.println(computerHand);
        computerHand.add(dominoe,lib.draw());
System.out.println("no current matching " + dominoe);

        return leftComputerMove(computerHand, currentDominoe, b, dominoe, lib);
    }


public Dominoe rightComputerMove(Dominoe[] computerHand, Dominoe currentDominoe, Board b, int dominoe, dominoeLibrary lib){
    if ( dominoe < 7 &&computerHand[dominoe].getLeftNumber() == currentDominoe.getRightNumber() || (computerHand[dominoe].getLeftNumber() == 0) || currentDominoe.getRightNumber() == 0) {
        temp = computerHand[dominoe];
        computerHand[dominoe] = lib.draw();
        System.out.println("computer picked dominoe" + temp);
        return temp;

    } else if (dominoe < 7 && computerHand[dominoe].getLeftNumber() != currentDominoe.getRightNumber() || (computerHand[dominoe].getLeftNumber() == 0) || currentDominoe.getRightNumber() == 0) {
        if (check == true) {
            check = false;
            b.flipDominoe(computerHand[dominoe].getDominoPicture(), dominoe, computerHand[dominoe], computerHand[dominoe].getRightNumber(), computerHand[dominoe].getLeftNumber(), -1);
            return rightComputerMove(computerHand, currentDominoe, b, dominoe, lib);
        } else if (check == false) {
            check = true;
            return rightComputerMove(computerHand, currentDominoe, b, dominoe + 1, lib);
        }
    }


    return currentDominoe;
}
}