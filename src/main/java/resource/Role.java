package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Role {
    private Long roleId;

    private String name;

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long role_id) {
        this.roleId = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + roleId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
