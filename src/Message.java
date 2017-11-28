public class Message {

    Players player;
    int actionType;
    Players number;
    //Message message;

    public Message (Players player, int actionType) {
        this.player = player;
        this.actionType = actionType;
        this.number = number;
        //this.message = message;

    }

    public Players getPlayer() {
        return player;
    }

    public int getActionType() {
        return actionType;
    }



}
