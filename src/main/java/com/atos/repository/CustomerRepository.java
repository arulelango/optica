package com.atos.repository;

import com.atos.exception.AtosApplicationException;
import com.atos.helper.StorageHelper;
import com.atos.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository {
    Logger LOG = LoggerFactory.getLogger(CustomerRepository.class);

    private StorageHelper storageHelper;

    public CustomerRepository(StorageHelper storageHelper){
        this.storageHelper = storageHelper;
    }

    public void save(Customer customer) throws AtosApplicationException{
        try{
            LOG.info("Save method called.");
            storageHelper.add(customer);
            LOG.info("Customer created :"+customer.getId());
        }catch (Exception e){
            throw new AtosApplicationException("Error while saving customer!");
        }

    }

    public List<Customer> findAll() throws AtosApplicationException {

        LOG.info("Getting all the customers...");
        return storageHelper.getAll();
    }

    public void delete(Customer customer) throws AtosApplicationException{
        try{
            storageHelper.remove(customer.getId());
            LOG.info("Deleted customer with id :"+customer.getId());
        }catch (Exception e){
            throw new AtosApplicationException("Error while removing the customer!");
        }

    }

    public Customer findById(Long id){
        return storageHelper.get(id);
    }
}
