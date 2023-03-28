package resource;
import java.sql.Timestamp;
import java.util.UUID;
public class User {
    private UUID user_id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long role_id;

    private Timestamp creation_date;

    private Timestamp update_date;

    private String pp_path;

    public User(UUID user_id,String name,String surname,String email,String password,Long role_id,Timestamp creation_date,Timestamp update_date,String pp_path){
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

    public User(UUID user_id,String name,String surname,String email,String password,Long role_id,Timestamp creation_date,Timestamp update_date){
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.creation_date = creation_date;
        this.update_date = update_date;
    }
}
