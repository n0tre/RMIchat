package com.ncedu.rmi;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerDriver {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("RMIchatServer" , new ChatServer());
    }
}
