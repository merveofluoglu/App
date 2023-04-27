package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Permission {

    private long permission_id;

    private String name;



    public Permission(long permission_id,String name){
        this.permission_id = permission_id;
        this.name = name;
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




}
