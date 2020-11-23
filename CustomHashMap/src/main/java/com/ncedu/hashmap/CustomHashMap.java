package com.ncedu.hashmap;

public class CustomHashMap<K, V> {

   public int mapCapacity = 16;
   public int size = 0;
   private Record<K, V>[] array;

   public CustomHashMap() {
    array = new Record[mapCapacity];
   }

   public CustomHashMap(int mapCapacity) {
    this.mapCapacity = mapCapacity;
    array = new Record[mapCapacity];
   }


    public void put(K key, V value){
        int bucket = bucket(key);
        Record newRecord = new Record(key, value, null);
        if(array[bucket] == null){
            array[bucket] = newRecord;
        }else {
            Record<K, V> previousNode = null;
            Record<K, V> currentNode = array[bucket];
            while(currentNode != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();

            }
            if(previousNode != null) {
                previousNode.setNext(newRecord);
            }
        }
    }

    public void remove(K key) {

            int bucket = bucket(key);
            Record previous = null;
            Record record = array[bucket];
            while (record != null){
                if(record.getKey().equals(key)){
                    if(previous == null){
                        record = record.getNext();
                        array[bucket] = record;
                        return;
                    }else {
                        previous.setNext(record.getNext());
                        return;
                    }
                }
                previous = record;
                record = record.getNext();

            }

        }



    public void getSize() {
        for (int i = 0; i< mapCapacity; i++) {
            if (array[i] != null)
                size++;
        }
        System.out.println("Current size is " + size);
    }
    private int bucket (K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(hash(key) % mapCapacity);
    }

    public int hash(K key) {
       int h;
       return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public void view(){
        for(int i = 0; i < mapCapacity; i++){
            if(array[i] != null){
                Record<K, V> currentNode = array[i];
                while (currentNode != null){
                    System.out.println(String.format(currentNode.getKey() + " " + currentNode.getValue()));
                    currentNode = currentNode.getNext();
                }
            }
        }
    }



}


