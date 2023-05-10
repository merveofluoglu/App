package resource;

public class Favourites {

    private long favouriteId;
    private long userId;
    private long postId;

    public Favourites() {

    }

    public Favourites(long favouriteId, long userId, long postId) {
        this.favouriteId = favouriteId;
        this.userId = userId;
        this.postId = postId;
    }

    public long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(long favouriteId) {
        this.favouriteId = favouriteId;
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
