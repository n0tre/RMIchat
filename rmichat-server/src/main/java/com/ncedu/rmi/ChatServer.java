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

    public ArrayList listOfActiveUsers(ChatClientIF listOfUsers) throws RemoteException {
        ArrayList list = new ArrayList();
        for (int i = 0; i < chatClients.size(); i++)
        {
            list.add(chatClients.get(i).getName(listOfUsers));
        }
        return list;
    }



    public boolean isUnique (String name, ChatServerIF list) throws RemoteException {
        boolean unique = true;
        for (int i = 0; i < chatClients.size(); i++) {
            if (chatClients.get(i).getName().equals(name))
                unique = false;
            else unique = true; }
            return unique;
   }



    public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.add(chatClient);
        broadcastMessage("Client " + chatClient.getName(chatClient) + " is connected");
    }



    public synchronized void disconnectChatClient(ChatClientIF chatClient) throws RemoteException {
        this.chatClients.remove(chatClient);
        broadcastMessage("Client " + chatClient.getName(chatClient) + " is disconnected");
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
