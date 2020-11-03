package com.ncedu.rmi.server;

import com.ncedu.rmi.client.ChatClientIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

    private static final long serialVersionUID = 1L;
    private ArrayList<ChatClientIF> chatClients;

    protected ChatServer() throws RemoteException {
        chatClients = new ArrayList<ChatClientIF>();

    }

    public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.add(chatClient);
    }

    public synchronized void broadcastMessage(String message) throws RemoteException {
            int i = 0;
            while (i < chatClients.size()) {
                chatClients.get(i++).retrieveMessage(message);
            }
    }
}
