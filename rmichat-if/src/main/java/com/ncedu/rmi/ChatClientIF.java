package com.ncedu.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ChatClientIF extends Remote {
    void retrieveMessage(String message) throws RemoteException;
    String getName(ChatClientIF chatClient) throws RemoteException;
    String getName() throws RemoteException;
}


