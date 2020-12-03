package com.ncedu.rmi;

public enum Actions {

    HELP {
        @Override
        public String toString() {
            return "\n" + "\n" +
                    CONNECT + "\n" +
                    SEND_PRIVATE_MESSAGE +  "\n" +
                    SEND_PUBLIC_MESSAGE + "\n" +
                    GET_LIST_OF_ACTIVE_USERS + "\n" +
                    DISCONNECT + "\n";

        }
    },

    CONNECT {
       @Override
       public String toString() {
           return "1. Connect";
       }
    },
    SEND_PRIVATE_MESSAGE {
        @Override
        public String toString() {
            return "2. Send Private Message";
        }
    },

    SEND_PUBLIC_MESSAGE {
        @Override
        public String toString() {
            return "3. Send Public Message";
        }
    },
    GET_LIST_OF_ACTIVE_USERS {
        @Override
        public String toString() {
            return "4. Get List of active users";
        }
    },
    DISCONNECT {
        @Override
        public String toString() {
            return "5. Disconnect";
        }
    };

    public static String HELP(String name) {
        return name + " choose your action " + "\n" +
                "\n" + CONNECT + "\n" +
                SEND_PRIVATE_MESSAGE + "\n" +
                SEND_PUBLIC_MESSAGE + "\n" +
                GET_LIST_OF_ACTIVE_USERS + "\n" +
                DISCONNECT + "\n";
    }
}

