package com.ncedu.rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Scanner;
public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {
    private static final long serialVersionUID = 1L;
    private ChatServerIF chatServer;
    private String name = null;

    protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this);
    }
    public void retrieveMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    public String getName(ChatClientIF chatClient) {
        return name;
    }
    @Override
    public String getName() throws RemoteException {
        return name;
    }
    @Override
    public void run()
    {
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
                    System.out.println("Ok, " + name + ", enter the chat number");
                    String destination = scanner.nextLine();
                    int chatNumber = Integer.parseInt(destination);
                    System.out.println(name + ", enter your private message");
                    message = scanner.nextLine();
                    try {
                        chatServer.privateMessage(name + " : " + message, chatNumber);
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
                        System.out.println("List of active users: " + chatServer.listOfActiveUsers(this));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    ;
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




