package com.ncedu.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF chatClient, String name) throws RemoteException;

    void disconnectChatClient(ChatClientIF chatClient) throws RemoteException;

    void broadcastMessage(String message) throws RemoteException;

    void privateMessage(String message, String name) throws RemoteException;

    List<String> listOfActiveUsers(ChatServerIF chatClient) throws RemoteException;

    boolean isUnique(ChatServerIF list, String name) throws RemoteException;

    ChatClientIF getKeyByValue(Map<ChatClientIF, String> map, String value) throws RemoteException;

    boolean isOnline(ChatServerIF chatServer, String name) throws RemoteException;

}
