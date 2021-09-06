package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to update an existed folder.
 */
public final class UpdateFolderCommand extends AuthenticatedUserCommand {

    private final FolderId id;

    private final String name;

    private final UserId owner;

    private final FolderId parentFolder;

    public UpdateFolderCommand(AuthToken token, FolderId id, String name, UserId owner,
                               @Nullable FolderId parentFolder) {

        super(checkNotNull(token));

        this.id = checkNotNull(id);

        this.name = checkNotNull(name);

        this.owner = checkNotNull(owner);

        this.parentFolder = parentFolder;

    }

    public FolderId id() {

        return id;
    }

    public String name() {

        return name;
    }

    public UserId owner() {

        return owner;
    }

    public FolderId parentFolder() {

        return parentFolder;
    }
}