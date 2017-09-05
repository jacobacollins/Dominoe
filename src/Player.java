
public class Player {
private Hand h;
private dominoeLibrary boneyard;
    public Player(dominoeLibrary boneyard){
    this.boneyard = boneyard;
    h = new Hand(boneyard);
    }
public void oomputerPlayer(){
//fill in
}

    public Hand getH() {
        return h;
    }
}
