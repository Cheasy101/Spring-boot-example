package CheesusPackage.Customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Bulat Sharapov
 */
@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();

        Customer alex = new Customer(
                1, "Alex", "alex@gmail.com", 21
        );
        Customer jamila = new Customer(
                2, "jamila", "jamila@gmail.com", 19
        );
        customers.add(alex);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream().
                filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customers.stream()
                .filter(customer ->  customer.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream().anyMatch(c -> c.getId().equals(id));
    }


    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream().
                anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }




}
