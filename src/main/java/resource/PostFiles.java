package resource;

public class PostFiles {
    private long file_id;

    private long post_id;

    private String file_type;

    private double file_size;

    private String file_path;

    public PostFiles(long file_id, long post_id, String file_type, double file_size, String file_path) {
        this.file_id = file_id;
        this.post_id = post_id;
        this.file_type = file_type;
        this.file_size = file_size;
        this.file_path = file_path;
    }

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

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public double getFile_size() {
        return file_size;
    }

    public void setFile_size(double file_size) {
        this.file_size = file_size;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
