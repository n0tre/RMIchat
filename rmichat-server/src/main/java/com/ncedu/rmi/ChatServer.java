package com.ncedu.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
    private static final long serialVersionUID = 1L;
    private final Map<ChatClientIF, String> chatClients;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 8);

    public ChatServer() throws RemoteException {
        chatClients = new ConcurrentHashMap<>();
    }

    public Collection<String> listOfActiveUsers(ChatServerIF chatClient) throws RemoteException {
        return chatClients.values();
    }

    public boolean isUnique(ChatServerIF list, String name) throws RemoteException {
        boolean unique = true;
        for (ChatClientIF chatClientIF : chatClients.keySet()) {
            if (chatClients.get(chatClientIF).equals(name))
                unique = false;
        }
        return unique;
    }

    public void registerChatClient(ChatClientIF chatClient, String name) throws RemoteException {
        this.chatClients.put(chatClient, name);
        broadcastMessage("Client " + chatClients.get(chatClient) + " is connected");
    }

    public void disconnectChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.remove(chatClient);
        broadcastMessage("Client " + chatClients.get(chatClient) + " is disconnected");
    }

    public void broadcastMessage(final String message) throws RemoteException {
        for (final ChatClientIF client : this.chatClients.keySet()) {
            executorService.execute(() -> {
                try {
                    client.retrieveMessage(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public ChatClientIF getKeyByValue(Map<ChatClientIF, String> map, String value) {
        for (ChatClientIF chatClientIF : chatClients.keySet()) {
            if (chatClients.get(chatClientIF).hashCode() == (value.hashCode()))
                if (chatClients.get(chatClientIF).equals(value))
                return chatClientIF;
        }
        return null;
    }

    public void privateMessage(String message, String name) throws RemoteException {
        executorService.execute(() -> {
            try {
                if (getKeyByValue(chatClients, name) != null) {
                    getKeyByValue(chatClients, name).retrieveMessage(message);
                } else System.out.println("User is offline");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean isOnline(ChatServerIF chatServer, String name) {
        boolean answer = false;
        for (Map.Entry<ChatClientIF, String> chatClientIFStringEntry : chatClients.entrySet()) {
            if (Objects.equals(chatClientIFStringEntry.getValue().hashCode(), name.hashCode())) {
                answer = true;
                break;
            }
        }
        return answer;
    }
}

