package my.company.services;

import my.company.entities.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    public void deleteCustomer(int theId);

    public List<Customer> searchCustomers(String theSearchName);
}
