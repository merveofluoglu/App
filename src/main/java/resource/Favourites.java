package resource;

public class Favourites {

    private long userId;
    private long postId;

    public Favourites() {

    }

    public Favourites(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
