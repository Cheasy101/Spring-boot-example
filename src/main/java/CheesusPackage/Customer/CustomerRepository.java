package CheesusPackage.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bulat Sharapov
 */
public interface CustomerRepository
        extends JpaRepository <Customer, Integer>{
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Integer id);

}
