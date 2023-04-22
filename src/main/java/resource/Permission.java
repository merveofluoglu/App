package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Permission {

    private long permission_id;

    private String name;

    private boolean is_deleted;


    public Permission(long permission_id,String name,Boolean is_deleted){
        this.permission_id = permission_id;
        this.name = name;
        this.is_deleted = is_deleted;
    }
    public Permission(){}
    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(long permission_id)
    {
        this.permission_id = permission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDeleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean isDeleted) {
        this.is_deleted = isDeleted;
    }



}
