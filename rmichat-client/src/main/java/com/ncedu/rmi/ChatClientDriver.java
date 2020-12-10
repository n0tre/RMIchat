package com.ncedu.rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

import static java.rmi.Naming.bind;

public class ChatClientDriver {
    private static final String CHAT_SERVER_URL = "rmi://localhost/RMIchatServer";

    public static void main(String[] args) throws RemoteException {

        ChatServerIF chatServer = null;
        Scanner s = new Scanner(System.in);
        ChatClient client = null;
        String choose;

        System.out.println("Enter your name and press Enter: ");
        String name = s.nextLine().trim();
        try {
            chatServer = (ChatServerIF) Naming.lookup(CHAT_SERVER_URL);
            while (client == null) {
                try {
                    {
                        if (chatServer != null) {
                            bind(name.toUpperCase(), chatServer);
                            client = new ChatClient(chatServer, name);
                            client.run();
                        }
                    }
                } catch (AlreadyBoundException | MalformedURLException exception) {
                    System.out.println("Nickname is already used! Choose another nickname, please:");
                    name = s.nextLine().trim();
                }
            }

        } catch (NotBoundException | MalformedURLException | ConnectException e) {
            System.out.println("Failed to connect to server. Server is not available, try later");
            System.out.println(Actions.CONNECT);
            choose = s.nextLine().trim();
            while (choose.equals("1")) {
                try {
                    chatServer = (ChatServerIF) Naming.lookup(CHAT_SERVER_URL);
                    while (client == null) {
                        try {
                            {
                                if (chatServer != null) {
                                    bind(name.toUpperCase(), chatServer);
                                    client = new ChatClient(chatServer, name);
                                    client.run();
                                }
                            }
                        } catch (AlreadyBoundException | MalformedURLException exception) {

                            System.out.println("Nickname is already used! Choose another nickname, please:");
                            name = s.nextLine().trim();
                        }
                    }
                    break;
                } catch (NotBoundException | MalformedURLException | ConnectException ex) {
                    System.out.println("Failed to connect to server, try later");
                    System.out.println(Actions.CONNECT);
                }
                choose = s.nextLine().trim();
            }
        }
    }


}



