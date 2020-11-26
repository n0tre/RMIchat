package com.ncedu.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF chatClient ) throws RemoteException;
    void disconnectChatClient(ChatClientIF chatClient) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void privateMessage(String message, int i) throws RemoteException;
    ArrayList listOfActiveUsers(ChatClientIF chatServer) throws RemoteException;
    boolean isUnique (String name, ChatServerIF list) throws RemoteException;
}
