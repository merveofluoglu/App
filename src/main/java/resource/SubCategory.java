package resource;

public class SubCategory {
    private long SubCategory_id;
    private String SubCategory_name;

    public SubCategory(long subCategoryId, String SubCategory_name) {
        this.SubCategory_id = SubCategory_id;
        this.SubCategory_name = SubCategory_name;
    }

    public long getSubCategory_id() {
        return SubCategory_id;
    }

    public void setSubCategory_id(long SubCategory_id) {
        this.SubCategory_id = SubCategory_id;
    }

    public String getSubCategory_name() {
        return SubCategory_name;
    }

    public void setSubCategory_name(String SubCategory_name) {
        this.SubCategory_name = SubCategory_name;
    }
}
