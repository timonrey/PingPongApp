package com.commercetools.pingpong.model;

public class Message {

    String buttonId;
    int actionType;

    public Message (String buttonId, int actionType) {
        this.buttonId = buttonId;
        this.actionType = actionType;
    }

    public String getButtonId() {
        return buttonId;
    }

    public int getActionType() {
        return actionType;
    }


}
