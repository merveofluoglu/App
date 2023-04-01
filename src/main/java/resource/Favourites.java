package resource;

public class Favourites {

    private long favourite_id;
    private long user_id;
    private long post_id;

    public Favourites(long favourite_id, long user_id, long post_id) {
        this.favourite_id = favourite_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public long getFavourite_id() {
        return favourite_id;
    }

    public void setFavourite_id(long favourite_id) {
        this.favourite_id = favourite_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }
}
