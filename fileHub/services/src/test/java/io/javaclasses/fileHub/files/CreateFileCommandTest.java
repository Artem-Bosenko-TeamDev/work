package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AnonymousUserCommand;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateFileCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(CreateFileCommand.class.getConstructor(AuthToken.class, String.class,
                    MimeType.class, UserID.class, FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(CreateFileCommand.class.getMethods());
    }
}