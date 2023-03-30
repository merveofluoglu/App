package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class Role {
    private Long role_id;

    private String name;

    public Role(Long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
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
                "role_id='" + role_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
