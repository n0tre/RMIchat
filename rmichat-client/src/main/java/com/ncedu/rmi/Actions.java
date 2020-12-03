package com.ncedu.rmi;

public enum Actions {
    HELP {
        @Override
        public String toString() {
            return "\n" + "\n" + SEND_PRIVATE_MESSAGE + "\n" +
                    SEND_PUBLIC_MESSAGE + "\n" +
                    GET_LIST_OF_ACTIVE_USERS + "\n" +
                    DISCONNECT + "\n";

        }
    },
    SEND_PRIVATE_MESSAGE {
        @Override
        public String toString() {
            return "1. Send Private Message";
        }
    },

    SEND_PUBLIC_MESSAGE {
        @Override
        public String toString() {
            return "2. Send Public Message";
        }
    },
    GET_LIST_OF_ACTIVE_USERS {
        @Override
        public String toString() {
            return "3. Get List of active users";
        }
    },
    DISCONNECT {
        @Override
        public String toString() {
            return "4. Disconnect";
        }
    }

}

