package CheesusPackage.Customer;

import java.util.List;
import java.util.Optional;

/**
 * @author Bulat Sharapov
 */
public interface CustomerDAO {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer customerId);

    void insertCustomer(Customer customer);

    void deleteCustomer(Integer customerId);

    boolean existsPersonWithId(Integer id);

    boolean existsPersonWithEmail(String email);

    void updateCustomer(Customer customer);
}
