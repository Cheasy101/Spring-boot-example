package CheesusPackage.Customer;

import CheesusPackage.exception.DuplicateResourceException;
import CheesusPackage.exception.RequestValidationException;
import CheesusPackage.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bulat Sharapov
 */
@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {

        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDAO.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Customer with [%s] does not exist".formatted(id)
                        ));
    }

    public void deleteCustomerById(Integer customerId) {
        if (!customerDAO.existsPersonWithId(customerId)) {
            throw new ResourceNotFoundException(
                    "Customer with id [%s] not found".formatted(customerId)
            );
        }
        customerDAO.deleteCustomer(customerId);
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerDAO.existsPersonWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException(("email {%s} already taken".formatted(email))
            );
        }
        customerDAO.insertCustomer(new Customer(
                customerRegistrationRequest.name(), customerRegistrationRequest.email(), customerRegistrationRequest.age()
        ));
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest request) {

        Customer customer = getCustomer(customerId);
        boolean changes = false;

        if (request.name() != null && !request.name().equals(customer.getName())) {
            customer.setName(request.name());
            changes = true;
        }
        if (request.age() != null && !request.age().equals(customer.getAge())) {
            customer.setAge(request.age());
            changes = true;
        }
        if (request.email() != null && !request.email().equals(customer.getEmail())) {
            if (customerDAO.existsPersonWithEmail(request.email())) {
                throw new DuplicateResourceException
                        ("email already taken");
            }
            customer.setEmail(request.email());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }
        customerDAO.updateCustomer(new Customer(customerId, request.name(), request.email(), request.age()));
    }
}
