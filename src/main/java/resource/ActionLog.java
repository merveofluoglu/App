package resource;


import java.sql.Timestamp;

public class ActionLog {

    private long action_id;
    private boolean is_user_act;
    private boolean is_system_act;
    private String description;
    private Timestamp action_date;
    private long user_id;
    public ActionLog(){}
    public ActionLog(long action_id, boolean is_user_act, boolean is_system_act, String description, Timestamp action_date, long user_id) {
        this.action_id = action_id;
        this.is_user_act = is_user_act;
        this.is_system_act = is_system_act;
        this.description = description;
        this.action_date = action_date;
        this.user_id = user_id;
    }

    public long getAction_id() {
        return action_id;
    }

    public void setAction_id(long action_id) {
        this.action_id = action_id;
    }

    public boolean isIs_user_act() {
        return is_user_act;
    }

    public void setIs_user_act(boolean is_user_act) {
        this.is_user_act = is_user_act;
    }

    public boolean isIs_system_act() {
        return is_system_act;
    }

    public void setIs_system_act(boolean is_system_act) {
        this.is_system_act = is_system_act;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAction_date() {
        return action_date;
    }

    public void setAction_date(Timestamp action_date) {
        this.action_date = action_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
