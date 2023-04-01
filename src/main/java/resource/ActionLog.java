package resource;


import java.sql.Timestamp;

public class ActionLogs {

    private long action_id;
    private boolean is_user_act;
    private boolean is_system_act;
    private String description;
    private Timestamp action_date;
    private long user_id;
}
