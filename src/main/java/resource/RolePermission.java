package resource;
import java.sql.Timestamp;
import java.util.Objects;

public class RolePermission {

    private Long role_permission_id;

    private Long role_id;

    private Long permission_id;


    public RolePermission(Long role_permission_id,Long role_id,Long permission_id){
        this.role_permission_id = role_permission_id;
        this.role_id = role_id;
        this.permission_id = permission_id;
    }

    public Long getRole_permission_id() {
        return role_permission_id;
    }

    public void setRole_permission_id(Long role_permission_id)
    {
        this.role_permission_id = role_permission_id;
    }

    public Long getRole_id () {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }


    public Long getPermission_id () {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "role_permission_id='" + role_permission_id + '\'' +
                "role_id='" + role_id + '\'' +
                "permission_id='" + permission_id + '\'' +
                '}';
    }
}
