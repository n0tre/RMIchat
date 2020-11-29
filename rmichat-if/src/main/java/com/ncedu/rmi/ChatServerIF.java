package com.ncedu.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF chatClient ) throws RemoteException;
    void disconnectChatClient(ChatClientIF chatClient) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void privateMessage(String message, String name) throws RemoteException;
    List<ChatServerIF> listOfActiveUsers(ChatClientIF chatServer) throws RemoteException;
    boolean isUnique (String name, ChatServerIF list) throws RemoteException;
    int indexOf(String name) throws RemoteException;
}
