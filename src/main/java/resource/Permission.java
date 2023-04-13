package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Permission {

    private Long permission_id;

    private String name;


    public Permission(Long permission_id,String name){
        this.permission_id = permission_id;
        this.name = name;
    }

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long user_id)
    {
        this.permission_id = permission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission_id='" + permission_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
