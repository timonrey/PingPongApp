public class Message {

    Players player;
    int actionType;

    public Message (Players playerNumber, int actionType) {
        this.player = playerNumber;
        this.actionType = actionType;
    }

    public Players getPlayer() {
        return player;
    }

    public int getActionType() {
        return actionType;
    }



}
