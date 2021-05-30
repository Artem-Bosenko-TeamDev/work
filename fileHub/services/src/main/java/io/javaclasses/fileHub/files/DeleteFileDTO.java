package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;

/**
 * This is object, that contains data after successful
 * execution {@link DeleteFileProcess delete file process}.
 */
public final class DeleteFileDTO {
    private final String DELETED_FILE_ID;

    public DeleteFileDTO(FileID id) {
        DELETED_FILE_ID = "File was deleted: " + Preconditions.checkNotNull(id);
    }

    public String getDELETED_FILE_ID() {
        return DELETED_FILE_ID;
    }
}
