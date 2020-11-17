package com.ncedu.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF chatClient ) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void privateMessage(String message, int i) throws RemoteException;
}
