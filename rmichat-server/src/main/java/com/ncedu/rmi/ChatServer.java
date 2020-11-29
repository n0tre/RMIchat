package com.ncedu.rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
    private static final long serialVersionUID = 1L;
    private CopyOnWriteArrayList<ChatClientIF> chatClients;

    protected ChatServer() throws RemoteException {
        chatClients = new CopyOnWriteArrayList<>();
    }

    public List listOfActiveUsers(ChatClientIF listOfUsers) throws RemoteException {
        List list = new ArrayList();
        for (int i = 0; i < chatClients.size(); i++) {
            list.add(chatClients.get(i).getName(listOfUsers));
        }
        return list;
    }
    public boolean isUnique(String name, ChatServerIF list) throws RemoteException {
        boolean unique = true;
        for (int i = 0; i < chatClients.size(); i++) {
            if (chatClients.get(i).getName().toUpperCase().equals(name.toUpperCase()))
                unique = false;
        }
        return unique;
    }
    public void registerChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.add(chatClient);
        broadcastMessage("Client " + chatClient.getName(chatClient) + " is connected");
    }
    public void disconnectChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.remove(chatClient);
        broadcastMessage("Client " + chatClient.getName(chatClient) + " is disconnected");
    }
    ExecutorService executorService = Executors.newFixedThreadPool(32);
    public void broadcastMessage(final String message) throws RemoteException {
        for (final ChatClientIF client : this.chatClients) {
            executorService.execute(() -> {
                try {
                    client.retrieveMessage(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    public void privateMessage(String message, String name) throws RemoteException {
        executorService.execute(() -> {
            try {
                chatClients.get(indexOf(name)).retrieveMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
    public int indexOf(String name) throws RemoteException {
        int nameIndex = -1;
        for (int i = 0; i < chatClients.size(); i++) {
            if (chatClients.get(i).getName().equals(name))
                nameIndex = i;
        }
        return nameIndex;
    }
}

