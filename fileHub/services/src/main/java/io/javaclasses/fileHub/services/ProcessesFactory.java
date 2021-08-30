package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.RegisterUser;

/**
 * The entry point that allows using all server's services of FileHub application.
 */
public class ProcessesFactory {

    private final UserStorage userStorage = new UserStorageInMemory();

    private final AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

    public AuthenticateUser authenticateUser() {

        return new AuthenticateUser(userStorage, authorizationStorage);
    }

    public RegisterUser registerUser() {

        return new RegisterUser(userStorage);
    }
}
