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
    private int overflowCount;

    private ArrayList<Dominoe> temporary= new ArrayList<>();



    public Player(dominoeLibrary boneyard) {
        this.boneyard = boneyard;
        h = new Hand(boneyard);

    }



    public Hand getH() {
        return h;
    }



    public Dominoe leftComputerMove(ArrayList<Dominoe> computerHand, Dominoe currentDominoe, Board b, int dominoe, dominoeLibrary lib) {

        if ( dominoe < computerHand.size() && b.isLegal(computerHand.get(dominoe).getRightNumber(),currentDominoe.getLeftNumber()) && computerHand.get(dominoe).getRightNumber()!= -2) {

b.setCounter(1);

            temp = computerHand.get(dominoe);

            if(b.getBoardCount() > 0) {
                computerHand.set(dominoe, lib.draw());

            } else{
                computerHand.set(dominoe, lib.getBlankDominoe());

            }
            if(temp.getLeftNumber() < temp.getRightNumber()){
                b.setCounter(-1);
            }
            return temp;

        } else if (dominoe < computerHand.size() && !b.isLegal(computerHand.get(dominoe).getRightNumber(),currentDominoe.getLeftNumber()) && computerHand.get(dominoe).getRightNumber() != -2) {
            if (check == true) {
                check = false;
                b.flipDominoeNumbers(computerHand.get(dominoe), computerHand.get(dominoe).getRightNumber(), computerHand.get(dominoe).getLeftNumber());
                b.setCounter(-1);
               return leftComputerMove(computerHand, currentDominoe, b, dominoe, lib);
            } else if (check == false) {
                check = true;
                return leftComputerMove(computerHand, currentDominoe, b, dominoe + 1, lib);
            }
        }


if(b.getBoardCount() > 0) {
    computerHand.add(dominoe, lib.draw());
    return leftComputerMove(computerHand, currentDominoe, b, dominoe, lib);
}

++overflowCount;
b.setCounter(1);

        if(computerHand.get(dominoe).getLeftNumber() == -2){
            return lib.getBlankDominoe();
        } else if(overflowCount >= 7){

            return lib.getBlankDominoe();

        } else{
            return lib.getBlankDominoe();
        }
    }


public Dominoe rightComputerMove(ArrayList<Dominoe> computerHand, Dominoe currentDominoe, Board b, int dominoe, dominoeLibrary lib){
    if (dominoe < computerHand.size() && b.isLegal(computerHand.get(dominoe).getLeftNumber(),currentDominoe.getRightNumber())  && computerHand.get(dominoe).getRightNumber()!= -2) {

       b.setCounter(1);

        temp = computerHand.get(dominoe);

        if(b.getBoardCount() > 0) {
            computerHand.set(dominoe, lib.draw());

        } else{
            computerHand.set(dominoe, lib.getBlankDominoe());

        }
        if(temp.getLeftNumber() < temp.getRightNumber()){
            b.setCounter(-1);
        }
        return temp;

    } else if (dominoe < computerHand.size() && !b.isLegal(computerHand.get(dominoe).getLeftNumber(),currentDominoe.getRightNumber())) {
        if (check == true) {
            check = false;
            b.flipDominoeNumbers(computerHand.get(dominoe), computerHand.get(dominoe).getRightNumber(), computerHand.get(dominoe).getLeftNumber());
            b.setCounter(-1);
            return rightComputerMove(computerHand, currentDominoe, b, dominoe, lib);
        } else if (check == false) {
            check = true;
            return rightComputerMove(computerHand, currentDominoe, b, dominoe + 1, lib);
        }
    }


    if(b.getBoardCount() > 0) {
        computerHand.add(dominoe, lib.draw());
        return rightComputerMove(computerHand, currentDominoe, b, dominoe, lib);
    }
    ++overflowCount;
    b.setCounter(1);
    if(computerHand.get(dominoe).getLeftNumber() == -2){
        return lib.getBlankDominoe();
    } else if(overflowCount >= 7){

        return lib.getBlankDominoe();

    }else{
        return lib.getBlankDominoe();
    }}
}