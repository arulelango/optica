package com.atos.helper;


import com.atos.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StorageHelperTest {
    private StorageHelper testee;

    @Before
    public void init(){
        testee = new StorageHelper();
    }

    @Test
    public void testAdd(){
        //When
        testee.add(getCustomer(50001L));

        //Then
        assertEquals(1,testee.getAll().size());
    }

    @Test
    public void testGetCustomer(){
        //When
        testee.add(getCustomer(50001L));

        //Then
        Customer customer = testee.get(50001L);
        assertEquals("John",customer.getFirstName());
        assertEquals("Sharpe",customer.getSurName());
    }

    @Test
    public void testGetAll(){
        //Given
        testee.add(getCustomer(50001L));
        testee.add(getCustomer(50002L));
        testee.add(getCustomer(50003L));

        //When
        List<Customer> customerList = testee.getAll();
        //Then
        assertEquals(3,customerList.size());
    }

    @Test
    public void testRemove(){
        //Given
        testee.add(getCustomer(50001L));
        testee.add(getCustomer(50002L));
        testee.add(getCustomer(50003L));
        assertEquals(3,testee.getAll().size());

        //When
        testee.remove(50002L);
        //Then
        assertEquals(2,testee.getAll().size());
        assertNotNull(testee.get(50001L));
        assertNotNull(testee.get(50003L));
        assertNull(testee.get(50002L));
    }

    private Customer getCustomer(Long id) {
        return new Customer(id,"John","Sharpe");
    }
}