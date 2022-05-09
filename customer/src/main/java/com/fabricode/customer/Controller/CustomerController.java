package com.fabricode.customer.Controller;

import com.fabricode.customer.CustomerDeleteRequest;
import com.fabricode.customer.CustomerRegistrationRequest;
import com.fabricode.customer.Service.CustomerService;
import com.fabricode.customer.Model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService){

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new customer registration", customerRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerCustomer(customerRegistrationRequest));
    }

    @DeleteMapping
    public ResponseEntity<Customer> deleteCustomer(@RequestBody CustomerDeleteRequest customerDeleteRequest){
        log.info("new delete customer request", customerDeleteRequest);
        Customer customer = customerService.deleteCustomer(customerDeleteRequest);
        if(customer != null)
            return ResponseEntity.ok(customer);

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping(path = "{customerID}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerID") Integer customerID){

        Optional<Customer> customer = customerService.getCustomerByID(customerID);

        if(customer.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer.get());
    }
}
