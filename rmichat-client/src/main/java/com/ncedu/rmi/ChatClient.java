package com.ncedu.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {
    private static final long serialVersionUID = 1L;
    private static final String CHAT_SERVER_URL = "rmi://localhost/RMIchatServer";
    private final ChatServerIF chatServer;
    private String name = null;

    protected ChatClient(ChatServerIF chatServer, String name) throws RemoteException {
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this.name, this);
    }

    public void retrieveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String choose;
        String message;
        {
            System.out.println(name + ", choose what you want: " + Actions.HELP.getHelp());
            while (true) {
                choose = scanner.nextLine();
                switch (choose) {
                    case "1": {
                        System.out.println("You are already connected and can start chatting");
                        break;
                    }
                    case "2": {
                        System.out.println("Ok, " + name + ", enter the recipient's name");
                        String destination = scanner.nextLine();
                        System.out.println(name + ", enter your private message");
                        message = scanner.nextLine();
                        try {
                            chatServer.privateMessage(name + " : " + message, destination);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            System.out.println("User already disconnected");
                        }

                        break;
                    }
                    case "3": {
                        System.out.println("Ok, " + name + ", enter your public message");
                        message = scanner.nextLine();
                        try {
                            chatServer.broadcastMessage(name + " : " + message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "4": {
                        try {
                            System.out.println("List of active users: " + chatServer.listOfActiveUsers(chatServer));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "5": {
                        try {
                            chatServer.disconnectChatClient(name);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    default: {
                        System.out.println(Actions.HELP.getHelp());

                        break;
                    }
                }
            }
        }
    }
}





