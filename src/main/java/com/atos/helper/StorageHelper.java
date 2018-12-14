package com.atos.helper;

import com.atos.model.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StorageHelper {
    private HashMap<Long,Customer> store = new HashMap<>();

    public void add(Customer customer){
        store.put(customer.getId(),customer);
    }

    public Customer get(Long id){
        return store.get(id);
    }

    public List<Customer> getAll(){
        return store.values().stream().collect(Collectors.toList());
    }

    public void remove(Long id){
        store.remove(id);
    }

}
