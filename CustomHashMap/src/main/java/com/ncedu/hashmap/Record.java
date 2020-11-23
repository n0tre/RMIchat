package com.ncedu.hashmap;
public class Record<K,V> {
        private K key;
        private V value;
        private Record<K, V> next;

        Record(K key, V value, Record<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public K getKey()        { return key; }
        public V getValue()      { return value; }
        public void setValue(V value) { this.value = value;}
        public void setKey(K key) {this.key = key;}
        public Record getNext() { return next; }
        public void setNext(Record<K,V> next) { this.next = next; }

    }

