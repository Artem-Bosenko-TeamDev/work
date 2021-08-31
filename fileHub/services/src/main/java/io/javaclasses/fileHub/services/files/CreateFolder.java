package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to create a new empty folder by authenticated user.
 */
public class CreateFolder extends SecuredUserProcess<CreateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(CreateFolder.class);

    private final FolderStorage folderStorageInMemory;

    public CreateFolder(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    protected FolderId doHandle(CreateFolderCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start create folder " + inputCommand.name());
        }

        FolderId id = new FolderId(inputCommand.name(), inputCommand.owner());
        Folder folder = new Folder(id);
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setName(inputCommand.name());
        folder.setItemsAmount(inputCommand.itemsAmount());
        folder.setOwner(inputCommand.owner());

        try {

            folderStorageInMemory.create(folder);

            if (logger.isInfoEnabled()) {
                logger.info("Created folder was successful. id: " + folder.id());
            }

            return folder.id();

        } catch (DuplicatedUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
