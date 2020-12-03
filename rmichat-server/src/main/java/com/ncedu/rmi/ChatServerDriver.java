package com.ncedu.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerDriver {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(1099);
        try {
            registry.bind("RMIchatServer", new ChatServer());
        } catch (AlreadyBoundException e) {
            System.out.println("Nickname is already USED!");
        }
    }
}
