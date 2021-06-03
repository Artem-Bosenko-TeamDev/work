package io.javaclasses.fileHub.services.users;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is service for getting information about authenticated user in Filehub application.
 */
public class ReadingInfoAboutUser implements View<ReadUserProfileQuery, InfoAboutUserDto> {

    private static final Logger logger = LoggerFactory.getLogger(ReadingInfoAboutUser.class);

    private final AbstractInMemoryStorage<UserId, User> userStorage;

    public ReadingInfoAboutUser(AbstractInMemoryStorage<UserId, User> userStorage) {
        this.userStorage = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public InfoAboutUserDto handle(ReadUserProfileQuery query) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start read user process with id: " + query.id());
        }


        Optional<User> findUser = userStorage.findByID(query.id());

        if (findUser.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("User " + findUser.get().login() + " exist!");
            }

            return new InfoAboutUserDto(
                    findUser.get().id(),
                    findUser.get().login(),
                    findUser.get().password(),
                    findUser.get().firstName(),
                    findUser.get().lastName()
            );

        }else {

            if(logger.isErrorEnabled()){
                logger.error("User with id doesn't exist " + query.id());
            }

            throw new InvalidHandleCommandException("User with id doesn't exist " + query.id());
        }
    }
}
