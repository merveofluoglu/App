package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class User {
    private long userId;

    private String name;

    private String surname;

    private String email;

    private String password;

    private long roleId;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private byte[] ppPath;

    private boolean isDeleted;

    public User(){}

    public User(long userId, String name, String surname, String email, String password, long roleId, Timestamp creationDate, Timestamp updateDate, byte[] ppPath, boolean isDeleted) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.ppPath = ppPath;
        this.isDeleted = isDeleted;
    }

    public User(long userId, String name, String surname, String email, String password, long roleId, Timestamp creationDate, Timestamp updateDate, boolean isDeleted) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public byte[] getPpPath() {
        return ppPath;
    }

    public void setPpPath(byte[] ppPath) {
        this.ppPath = ppPath;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
