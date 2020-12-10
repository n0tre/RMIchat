package com.ncedu.rmi;

public enum Actions {

    HELP("Help"),
    CONNECT("1.Connect"),
    SEND_PRIVATE_MESSAGE("2.Send private message"),
    SEND_PUBLIC_MESSAGE("3.Send public message"),
    GET_LIST_OF_ACTIVE_USERS("4.Get list of active users"),
    DISCONNECT("5.Disconnect");

    String description;

    Actions(String description) {
        this.description = description;
    }

    public String getHelp() {
        return "\n" + "\n" +
                CONNECT.description + "\n" +
                SEND_PRIVATE_MESSAGE.description + "\n" +
                SEND_PUBLIC_MESSAGE.description + "\n" +
                GET_LIST_OF_ACTIVE_USERS.description + "\n" +
                DISCONNECT.description + "\n";
    }
}

