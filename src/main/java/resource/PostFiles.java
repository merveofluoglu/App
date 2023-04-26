package resource;

import java.util.Arrays;

public class PostFiles {
    private long file_id;
    private long post_id;
    private byte[] file;
    private boolean is_deleted;

    public PostFiles(long file_id, long post_id, byte[] file, boolean is_deleted) {
        this.file_id = file_id;
        this.post_id = post_id;
        this.file = file;
        this.is_deleted = is_deleted;
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

    @Override
    public String toString() {
        if(file != null) {
            return "PostFiles{" +
                    "file_id=" + file_id +
                    ", post_id=" + post_id +
                    ", file=" + file.toString() +
                    ", is_deleted=" + is_deleted +
                    '}';
        }
        else {
            return "PostFiles{" +
                    "file_id=" + file_id +
                    ", post_id=" + post_id +
                    ", file=" + null +
                    ", is_deleted=" + is_deleted +
                    '}';
        }
    }
}
