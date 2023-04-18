package resource;

import java.sql.Timestamp;

public class Post {
    private long post_id;
    private String name;
    private String description;
    private long user_id;
    private long customer_id;
    private double price;
    private String status;
    private Timestamp start_date;
    private Timestamp end_date;
    private boolean is_deleted;
    private boolean is_sold;
    private Timestamp sold_date;
    private Timestamp update_date;
    private long category_id;
    private long subcategory_id;

    public Post(long post_id, String name, String description, long user_id, long customer_id, double price, String status, Timestamp start_date, Timestamp end_date, boolean is_deleted, boolean is_sold, Timestamp sold_date, Timestamp update_date, long category_id, long subcategory_id) {
        this.post_id = post_id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.customer_id = customer_id;
        this.price = price;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_deleted = is_deleted;
        this.is_sold = is_sold;
        this.sold_date = sold_date;
        this.update_date = update_date;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
    }

    public Post() {}
    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean isIs_sold() {
        return is_sold;
    }

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }

    public Timestamp getSold_date() {
        return sold_date;
    }

    public void setSold_date(Timestamp sold_date) {
        this.sold_date = sold_date;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(long subcategory_id) {
        this.subcategory_id = subcategory_id;
    }
}