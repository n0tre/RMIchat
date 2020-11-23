package com.ncedu.hashmap;
public class MainApp {
    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<Integer, String>();
        System.out.println("First test, add pairs");
        map.put(1, "Lamborghini");
        map.put(9, "Ferrari");
        map.put(17, "Porshe");
        map.put(1, "Vaz 2114");
        map.view();

        System.out.println("Second test, remove pairs");
        map.remove(2);
        map.view();

        System.out.println("Third test, getting size");
        map.getSize();
        map.view();




    }
}
