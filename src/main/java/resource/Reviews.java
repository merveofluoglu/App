package resource;

import java.sql.Timestamp;

public class Reviews {

    private long review_id;
    private long user_id;
    private long seller_id;
    private long post_id;
    private double point_scale;
    private String description;
    private Timestamp create_date;
    private boolean is_deleted;

    public Reviews(long review_id, long user_id, long seller_id, long post_id, double point_scale, String description, Timestamp create_date, boolean is_deleted) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.seller_id = seller_id;
        this.post_id = post_id;
        this.point_scale = point_scale;
        this.description = description;
        this.create_date = create_date;
        this.is_deleted = is_deleted;
    }

    public long getReview_id() {
        return review_id;
    }

    public void setReview_id(long review_id) {
        this.review_id = review_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public double getPoint_scale() {
        return point_scale;
    }

    public void setPoint_scale(double point_scale) {
        this.point_scale = point_scale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
