package CheesusPackage;

import org.springframework.stereotype.Service;

/**
 * @author Bulat Sharapov
 */
@Service
public class FooService {

    private final CheesusMain.Foo foo;

    public FooService(CheesusMain.Foo foo) {
        this.foo = foo;
    }
    String getFooName() {
        return foo.name();
    }
}
