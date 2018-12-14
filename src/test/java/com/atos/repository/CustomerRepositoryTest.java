package com.atos.repository;


import com.atos.exception.AtosApplicationException;
import com.atos.helper.StorageHelper;
import com.atos.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryTest {

    private CustomerRepository testee;
    @Rule
    public ExpectedException expected = ExpectedException.none();

    final ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

    @Mock
    StorageHelper mockStorageHelper;

    @Before
    public void init() {
        testee = new CustomerRepository(mockStorageHelper);
    }

    @Test
    public void testAddCustomerSuccessfully() throws Exception{
        //Given
        Customer customer = getCustomer(90001L);
        when(mockStorageHelper.get(Mockito.any())).thenReturn(customer);
        //When
        testee.save(customer);
        //Then
        Customer foundCustomer = testee.findById(customer.getId());
        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(90001,foundCustomer.getId());
    }

    @Test
    public void testAddCustomerThrowsException() throws Exception{
        expected.expect(AtosApplicationException.class);
        expected.expectMessage("Error while saving customer");

        //Given
        Customer customer = getCustomer(90001L);
        Mockito.doThrow(new RuntimeException()).when(mockStorageHelper).add(Mockito.any());

        //When
        testee.save(customer);
    }

    @Test
    public void testFindAllSuccessfully() throws Exception{
        //Given
        Customer customer1 = getCustomer(90001L);
        Customer customer2 = getCustomer(90002L);
        when(mockStorageHelper.getAll()).thenReturn(Arrays.asList(customer1,customer2));
        //When
        List<Customer> customers = testee.findAll();
        //Then
        Assert.assertEquals(2,customers.size());
    }

    @Test
    public void testFindAllWhenNoCustomers() throws Exception{
        //Given
        List<Customer> values = new ArrayList<>();
        when(mockStorageHelper.getAll()).thenReturn(values);
        //When
        List<Customer> customers = testee.findAll();
        //Then
        Assert.assertEquals(0,customers.size());
    }

    @Test
    public void testDeleteCustomerOnSuccess() throws Exception {
        Customer customer1 = getCustomer(90001L);

        //When
        testee.delete(customer1);
        //Then
        Mockito.verify(mockStorageHelper, times(1)).remove(captor.capture());
        Assert.assertEquals(90001,captor.getValue().longValue());
    }

    @Test
    public void testDeleteCustomerOnException() throws Exception {
        expected.expect(AtosApplicationException.class);
        expected.expectMessage("Error while removing the customer!");

        //Given
        Mockito.doThrow(new RuntimeException()).when(mockStorageHelper).remove(90008L);
        Customer customer1 = getCustomer(90008L);

        //When
        testee.delete(customer1);
        //Then
        Mockito.verify(mockStorageHelper, times(1)).remove(captor.capture());
        Assert.assertEquals(90008,captor.getValue().longValue());
    }

    private Customer getCustomer(Long id) {
        return new Customer(id,"John","Sharpe");
    }

}