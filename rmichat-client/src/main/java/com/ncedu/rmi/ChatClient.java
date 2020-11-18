package com.ncedu.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            System.out.println(name + ", Вы хотите отправить личное сообщение?");
            message = scanner.nextLine();
            if (message.equals("нет")) {
                System.out.println("Хорошо, " + name + ", введите сообщение, которое будет видно всем");
                message = scanner.nextLine();
                try {
                    chatServer.broadcastMessage(name + " : " + message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Хорошо, " + name +  ", введите номер чата, в который вы хотите отправить сообщение");
                String destination = scanner.nextLine();
                int chatNumber = Integer.parseInt(destination);
                System.out.println(name + ", теперь введите сообщение для адресата");
                message = scanner.nextLine();
                try {
                    chatServer.privateMessage(name + " : " + message, chatNumber);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
