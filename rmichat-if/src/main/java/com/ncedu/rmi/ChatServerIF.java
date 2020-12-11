package com.ncedu.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote {
    void registerChatClient(String name, ChatClientIF chatClient) throws RemoteException;

    void disconnectChatClient(String name) throws RemoteException;

    void broadcastMessage(String message) throws RemoteException;

    void privateMessage(String message, String name) throws RemoteException;

    Object listOfActiveUsers(ChatServerIF chatClient) throws RemoteException;

    // boolean isUnique(ChatServerIF list, String name) throws RemoteException;

    // ChatClientIF getKeyByValue(Map<ChatClientIF, String> map, String value) throws RemoteException;

    // boolean isOnline(ChatServerIF chatServer, String name) throws RemoteException;


}
