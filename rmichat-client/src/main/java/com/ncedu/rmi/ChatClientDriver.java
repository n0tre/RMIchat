package com.ncedu.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static java.rmi.Naming.bind;

public class ChatClientDriver {


    public static void main(String[] args) throws RemoteException, MalformedURLException {
        String chatServerURL = "rmi://localhost/RMIchatServer";

        ChatServerIF chatServer = null;
        try {
            chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to server");
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your name and press Enter: ");
        String name = s.nextLine().trim();


        Thread thread = new Thread();
        while (!thread.isAlive()) {
            try {
                {
                    if (chatServer != null) {
                        bind(name.toUpperCase(), chatServer);
                        thread = new Thread(new ChatClient(chatServer, name));
                        thread.start();
                    }
                }
            } catch (AlreadyBoundException | MalformedURLException e) {

                System.out.println("Nickname is already used! Choose another nickname, please:");
                name = s.nextLine().trim();
            }
        }

    }

}



