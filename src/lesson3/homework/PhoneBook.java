package lesson3.homework;

import java.util.*;

public class PhoneBook {
    TreeMap<String, ArrayList<String>> phones;

    public PhoneBook(){
        phones = new TreeMap<>();
    }

    public void add(String name, String phoneNumber) {
        if (phones.containsKey(name)) {
            phones.get(name).add(phoneNumber);
        } else {
            phones.put(name, new ArrayList<>(Collections.singletonList(phoneNumber)));
        }
    }

    public String get(String name){
        StringBuilder answer = new StringBuilder();
        Iterator<String> it = phones.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            if (name.equals(key)) {
                answer.append(phones.get(key));
            }
        }
        return answer.toString();
    }
}
