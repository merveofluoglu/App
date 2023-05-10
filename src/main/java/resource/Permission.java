package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Permission {

    private long permissionId;

    private String name;



    public Permission(long permissionId,String name){
        this.permissionId = permissionId;
        this.name = name;
    }
    public Permission(){}

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
