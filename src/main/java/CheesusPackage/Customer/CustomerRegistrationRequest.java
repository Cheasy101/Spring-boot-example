package CheesusPackage.Customer;

/**
 * @author Bulat Sharapov
 */
public record CustomerRegistrationRequest(String name,
                                          Integer age,
                                          String email) {
}
