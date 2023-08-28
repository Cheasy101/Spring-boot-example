package CheesusPackage.Customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bulat Sharapov
 */
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId) {
        return customerService.getCustomer(customerId);
    }
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        customerService.addCustomer(request);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId){
        customerService.deleteCustomerById(customerId);
    }

    @PatchMapping("{customerId}")
    public void editCustomerById(@PathVariable("customerId") Integer customerId,
                                 @RequestBody CustomerUpdateRequest request ){
        customerService.updateCustomer(customerId, request);
    }
}
