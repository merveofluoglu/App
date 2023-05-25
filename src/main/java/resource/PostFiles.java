package resource;

public class PostFiles {
    private long fileId;
    private long postId;
    private byte[] file;
    private boolean isDeleted;

    public String base64;

    private String fileMediaType;

    public PostFiles() {}

    public PostFiles(long fileId, long postId, byte[] file, boolean isDeleted, String fileMediaType) {
        this.fileId = fileId;
        this.postId = postId;
        this.file = file;
        this.isDeleted = isDeleted;
        this.fileMediaType = fileMediaType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getFileMediaType() {
        return fileMediaType;
    }

    public void setFileMediaType(String fileMediaType) {
        this.fileMediaType = fileMediaType;
    }

    public boolean hasFile() {
        return file != null && file.length > 0 && fileMediaType != null && !fileMediaType.isBlank();
    }

    public int getFileSize() { return file != null ? file.length : Integer.MIN_VALUE; }
}
