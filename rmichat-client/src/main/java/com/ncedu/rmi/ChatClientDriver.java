package com.ncedu.rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
public class ChatClientDriver {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String chatServerURL = "rmi://localhost/RMIchatServer";
        ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
            Scanner s = new Scanner(System.in);
            System.out.println("Enter your name and press Enter: ");
            String name = s.nextLine().trim();
           while (!(chatServer.isUnique(chatServer, name))) {
                System.out.println("Nickname already used. Please choose another one: ");
                name = s.nextLine().trim();
            }
            new Thread(new ChatClient(chatServer, name)).start();
    }
}

