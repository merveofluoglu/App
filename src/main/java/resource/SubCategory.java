package resource;

public class SubCategory {
    private long subcategory_id;
    private String subcategory_name;
    private long category_id;

    public SubCategory() {
    }

    public SubCategory(long subcategory_id, String subcategory_name, long category_id) {
        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
        this.category_id = category_id;
    }

    public long getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(long subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}
