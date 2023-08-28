package CheesusPackage.Customer;

/**
 * @author Bulat Sharapov
 */
public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {


}
