package com.ncedu.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
    private static final long serialVersionUID = 1L;
    private final Map<String, ChatClientIF> chatClients;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 8);

    public ChatServer() throws RemoteException {
        chatClients = new ConcurrentHashMap<>();
    }

    public Collection<String> listOfActiveUsers(ChatServerIF chatClient) throws RemoteException {
        return chatClients.keySet();
    }

    /* public boolean isUnique(ChatServerIF list, String name) throws RemoteException {
        boolean unique = true;
        for (ChatClientIF chatClientIF : chatClients.keySet()) {
            if (chatClients.get(chatClientIF).equals(name))
                unique = false;
        }
        return unique;
    }

     */

    public void registerChatClient(String name, ChatClientIF chatClient) throws RemoteException {
        this.chatClients.put(name, chatClient);
        broadcastMessage("Client " + name + " is connected");
    }

    public void disconnectChatClient(String name) throws RemoteException {
        this.chatClients.remove(name);
        broadcastMessage("Client " + name + " is disconnected");
    }

    public void broadcastMessage(final String message) throws RemoteException {
        for (final String client : this.chatClients.keySet()) {
            executorService.execute(() -> {
                try {
                    chatClients.get(client).retrieveMessage(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    try {
                        disconnectChatClient(client);
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                }
            });
        }
    }

    /* public ChatClientIF getKeyByValue(Map<ChatClientIF, String> map, String value) {
        for (ChatClientIF chatClientIF : chatClients.keySet()) {
            if (chatClients.get(chatClientIF).hashCode() == (value.hashCode()))
                if (chatClients.get(chatClientIF).equals(value))
                return chatClientIF;
        }
        return null;
    }

     */

    public void privateMessage(String message, String name) throws RemoteException {
        executorService.execute(() -> {
            try {
                if (chatClients.get(name) != null) {
                    chatClients.get(name).retrieveMessage(message);
                } else System.out.println("User is offline");
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    disconnectChatClient(name);
                    System.out.println("User is already disconnected");//
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
    }

    /* public boolean isOnline(ChatServerIF chatServer, String name) {
        boolean answer = false;
        for (Map.Entry<ChatClientIF, String> chatClientIFStringEntry : chatClients.entrySet()) {
            if (Objects.equals(chatClientIFStringEntry.getValue().hashCode(), name.hashCode())) {
                answer = true;
                break;
            }
        }
        return answer;
    }


     */
}

