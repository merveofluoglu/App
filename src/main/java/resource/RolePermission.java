package resource;
import java.sql.Timestamp;
import java.util.Objects;

public class RolePermission {

    private Long rolePermissionId;

    private Long roleId;

    private Long permissionId;

    public RolePermission() {}

    public RolePermission(Long rolePermissionId, Long roleId, Long permissionId) {
        this.rolePermissionId = rolePermissionId;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Long getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
