package resource;

import java.sql.Timestamp;

public class Reviews {

    private long reviewId;
    private long userId;
    private long sellerId;
    private long postId;
    private double pointScale;
    private String description;
    private Timestamp createDate;
    private boolean isDeleted;

    public Reviews() {
    }

    public Reviews(long reviewId, long userId, long sellerId, long postId, double pointScale, String description, Timestamp createDate, boolean isDeleted) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.sellerId = sellerId;
        this.postId = postId;
        this.pointScale = pointScale;
        this.description = description;
        this.createDate = createDate;
        this.isDeleted = isDeleted;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public double getPointScale() {
        return pointScale;
    }

    public void setPointScale(double pointScale) {
        this.pointScale = pointScale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
