package CheesusPackage;

import CheesusPackage.Customer.Customer;
import CheesusPackage.Customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author Bulat Sharapov
 */

@SpringBootApplication
public class CheesusMain {
    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(CheesusMain.class, args);

//        printBeans(applicationContext);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer alex = new Customer(
                    "Alex", "alex@gmail.com", 21
            );
            Customer jamila = new Customer(
                    "jamila", "jamila@gmail.com", 19
            );
            List<Customer> customers = List.of(alex, jamila);
            customerRepository.saveAll(customers);
        };
    }

    @Bean
    public Foo getName() {
        return new Foo("Bar");
    }

    record Foo(String name) {
    }

    private static void printBeans(ConfigurableApplicationContext ctx) {
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
