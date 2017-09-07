import com.sun.java.browser.plugin2.DOM;

public class Player {
    private Hand h;
    private dominoeLibrary boneyard;
    private int playerTurn = 1;
    public Player(dominoeLibrary boneyard) {
        this.boneyard = boneyard;
        h = new Hand(boneyard);
    }

    public void oomputerPlayer() {
//fill in
    }

    public Hand getH() {
        return h;
    }


    public boolean playerTurn(int playerTurn) {
        if(playerTurn % 2 == 1) {
            return true;
        } else{
            return false;
        }
    }


}