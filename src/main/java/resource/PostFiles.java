package resource;

public class PostFiles {
    private long file_id;
    private long post_id;
    private byte[] file;
    private boolean is_deleted;

    private String file_media_type;

    public PostFiles(long file_id, long post_id, byte[] file, boolean is_deleted, String file_media_type) {
        this.file_id = file_id;
        this.post_id = post_id;
        this.file = file;
        this.is_deleted = is_deleted;
        this.file_media_type = file_media_type;
    }

    public PostFiles() {}

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public byte[] getFile() { return file; }

    public void setFile(byte[] file) { this.file = file; }

    public boolean isIs_deleted() { return is_deleted; }

    public void setIs_deleted(boolean is_deleted) { this.is_deleted = is_deleted; }

    public String getFile_media_type() {
        return file_media_type;
    }

    public void setFile_media_type(String file_media_type) {
        this.file_media_type = file_media_type;
    }

    public boolean hasFile() {
        return file != null && file.length > 0 && file_media_type != null && !file_media_type.isBlank();
    }

    public int getFileSize() { return file != null ? file.length : Integer.MIN_VALUE; }
}
