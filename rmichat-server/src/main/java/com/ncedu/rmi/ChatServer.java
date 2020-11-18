package com.ncedu.rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
    private static final long serialVersionUID = 1L;
    private ArrayList<ChatClientIF> chatClients;
    protected ChatServer() throws RemoteException {
        chatClients = new ArrayList<>();
    }


    public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.add(chatClient);
    }
    public void broadcastMessage(final String message) throws RemoteException {
            for (final ChatClientIF client: this.chatClients) {
                Thread clientThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            client.retrieveMessage(message);
                        } catch (RemoteException e) {
                            System.out.println("Shit happens");;
                        }
                    }
                };
                clientThread.start();
            }
    }
    public void privateMessage(String message, int i) throws RemoteException {
        chatClients.get(i).retrieveMessage(message);
    }
}
