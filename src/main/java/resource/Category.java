package resource;


public class Category {
    private long category_id;
    private String category_name;
    public Category(long category_id, String category_name){
        this.category_id=category_id;
        this.category_name=category_name;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
