package com.fabricode.customer.Service;

import com.fabricode.customer.CustomerDeleteRequest;
import com.fabricode.customer.CustomerRegistrationRequest;
import com.fabricode.customer.Model.Customer;
import com.fabricode.customer.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        return customerRepository.save(customer);
    }

    public Customer deleteCustomer(CustomerDeleteRequest customerDeleteRequest) {
        Optional<Customer> customer = customerRepository.findById(customerDeleteRequest.id());

        if (customer.isEmpty())
            return null;

        customerRepository.delete(customer.get());
        return customer.get();
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByID(Integer id) {
        return customerRepository.findById(id);
    }
}
