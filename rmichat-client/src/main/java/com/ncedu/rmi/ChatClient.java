package com.ncedu.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {
    private static final long serialVersionUID = 1L;
    private final ChatServerIF chatServer;
    private String name = null;

    protected ChatClient(ChatServerIF chatServer, String name) throws RemoteException {
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this, this.name);
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
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " choose what you want " + Arrays.asList(Actions.values()));
            while (true) {
                choose = scanner.nextLine();
                if (choose.equals("1")) {
                    System.out.println("Ok, " + name + ", enter the recipient's name");
                    String destination = scanner.nextLine();
                    try {
                        if (chatServer.isOnline(chatServer, destination)) {
                            System.out.println(name + ", enter your private message");
                            message = scanner.nextLine();
                            try {
                                chatServer.privateMessage(name + " : " + message, destination);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else System.out.println("User is offline");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (choose.equals("2")) {
                    System.out.println("Ok, " + name + ", enter your public message");
                    message = scanner.nextLine();
                    try {
                        chatServer.broadcastMessage(name + " : " + message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (choose.equals("3")) {
                    try {
                        System.out.println("List of active users: " + chatServer.listOfActiveUsers(chatServer));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (choose.equals("4")) {
                    try {
                        chatServer.disconnectChatClient(this);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}





