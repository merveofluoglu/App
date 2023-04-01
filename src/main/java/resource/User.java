package resource;
import java.sql.Timestamp;
import java.util.Objects;
public class User {
    private Long user_id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long role_id;

    private Timestamp creation_date;

    private Timestamp update_date;

    private String pp_path;

    public User(Long user_id,String name,String surname,String email,String password,Long role_id,Timestamp creation_date,Timestamp update_date,String pp_path){
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.pp_path = pp_path;
    }

    public User(Long user_id,String name,String surname,String email,String password,Long role_id,Timestamp creation_date,Timestamp update_date){
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.creation_date = creation_date;
        this.update_date = update_date;
    }

    public Long getUserID() {
        return user_id;
    }

    public void setUserID(Long user_id)
    {
        this.user_id = user_id;
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Long getRole_id(){
        return role_id;
    }

    public void setRole_id(Long role_id){
        this.role_id = role_id;
    }

    public Timestamp getCreation_date(){
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date){
        this.creation_date = creation_date;
    }

    public Timestamp getUpdate_date(){
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date){
        this.update_date = update_date;
    }

    public String getProfile_photo(){
        return pp_path;
    }

    public void setProfile_photo(byte[] profile_photo){
        this.pp_path = pp_path;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role_id='" + role_id + '\'' +
                ", creation_date=" + creation_date +
                ", update_date=" + update_date +
                '}';
    }
}