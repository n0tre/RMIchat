package com.ncedu.rmi.server;
import com.ncedu.rmi.client.ChatClientIF;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF chatClient ) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void privateMessage(String message, int i) throws RemoteException;
}
