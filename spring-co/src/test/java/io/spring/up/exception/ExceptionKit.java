package io.spring.up.exception;

import io.spring.up.exception.web._400ParameterMissingException;
import io.spring.up.exception.web._500JsonResponseException;
import org.junit.Test;

public class ExceptionKit {

    @Test(expected = WebException.class)
    public void test20003() {
        throw new _400ParameterMissingException(this.getClass(), "test20003", "test20003");
    }

    @Test(expected = WebException.class)
    public void test20005() {
        throw new _500JsonResponseException(this.getClass(),
                new Exception("Test"));
    }
}
