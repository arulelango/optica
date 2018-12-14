package com.atos.controller;

import com.atos.exception.AtosApplicationException;
import com.atos.model.Customer;
import com.atos.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {
    Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository repository;

    @RequestMapping(method = GET,value = "/all")
    public ResponseEntity getCustomers() throws AtosApplicationException {
        LOG.info("GetAll Customer REST called");
        try{
            List<Customer> customerList = repository.findAll();
            if(customerList == null || customerList.size() ==0){
                return returnResponse("No customers found!!",HttpStatus.OK);
            }

            return returnResponse(customerList,HttpStatus.OK);

        }catch (AtosApplicationException e){
            return returnResponse("Application issue",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = {POST, PUT},value = "/add")
    public ResponseEntity addUpdateCustomer(@RequestBody Customer customer) {
        LOG.info("Create customer called ");
        try{
            repository.save(customer);
            return new ResponseEntity(HttpStatus.OK);

        }catch (AtosApplicationException e){
            return returnResponse("Application issue",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = DELETE, value = "/remove/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        LOG.info("Delete customer called with Id :"+id);
        try{
            Customer customer = repository.findById(id);
            repository.delete(customer);
            return new ResponseEntity(HttpStatus.OK);
        }catch (AtosApplicationException e){
            return returnResponse("Application issue",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity returnResponse(Object body, HttpStatus status){
        return new ResponseEntity(body, status);
    }
}
