package io.javaclasses.fileHub;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.UserRegisterDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatedUserCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(AuthenticatedUserCommand.class.getConstructor(AuthToken.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(AuthenticatedUserCommand.class.getMethods());
    }
}