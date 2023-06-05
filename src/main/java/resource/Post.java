package resource;

import java.sql.Timestamp;

public class Post {
    private long postId;
    private String name;
    private String description;
    private long userId;
    private long customerId;
    private double price;
    private String status;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean isDeleted;
    private boolean isSold;
    private Timestamp soldDate;
    private Timestamp updateDate;
    private long categoryId;
    private long subcategoryId;
    private String base64;
    private String fileMediaType;


    public Post() {}

    public Post(long postId, String name, String description, long userId, long customerId, double price, String status, Timestamp startDate, Timestamp endDate, boolean isDeleted, boolean isSold, Timestamp soldDate, Timestamp updateDate, long categoryId, long subcategoryId) {
        this.postId = postId;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDeleted = isDeleted;
        this.isSold = isSold;
        this.soldDate = soldDate;
        this.updateDate = updateDate;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
    }

    public Post(long postId, String name) {
        this.postId = postId;
        this.name = name;
    }
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Timestamp getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Timestamp soldDate) {
        this.soldDate = soldDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getFileMediaType() { return fileMediaType; }

    public void setFileMediaType(String fileMediaType) { this.fileMediaType = fileMediaType; }
}