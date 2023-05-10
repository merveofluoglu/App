package resource;


import java.sql.Timestamp;

public class ActionLog {

    private long actionId;
    private boolean isUserAct;
    private boolean isSystemAct;
    private String description;
    private Timestamp actionDate;
    private long userId;
    public ActionLog(){}

    public ActionLog(long actionId, boolean isUserAct, boolean isSystemAct, String description, Timestamp actionDate, long userId) {
        this.actionId = actionId;
        this.isUserAct = isUserAct;
        this.isSystemAct = isSystemAct;
        this.description = description;
        this.actionDate = actionDate;
        this.userId = userId;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public boolean isUserAct() {
        return isUserAct;
    }

    public void setUserAct(boolean userAct) {
        isUserAct = userAct;
    }

    public boolean isSystemAct() {
        return isSystemAct;
    }

    public void setSystemAct(boolean systemAct) {
        isSystemAct = systemAct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
