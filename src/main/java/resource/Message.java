package resource;

import java.sql.Timestamp;

public class Message {

    private long message_id;
    private long creator_id;
    private long recipient_id;
    private long parent_message_id;
    private String subject;
    private String message_body;
    private boolean is_read;
    private Timestamp creation_date;
    private Timestamp expiration_date;

    public Message(
            long message_id, long creator_id, long recipient_id, long parent_message_id,
            String subject, String message_body, boolean is_read,
            Timestamp creation_date, Timestamp expiration_date){

        this.message_id = message_id;
        this.creator_id = creator_id;
        this.recipient_id = recipient_id;
        this.parent_message_id = parent_message_id;
        this.subject = subject;
        this.message_body = message_body;
        this.is_read = is_read;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
    }

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public long getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(long recipient_id) {
        this.recipient_id = recipient_id;
    }

    public long getParent_message_id() {
        return parent_message_id;
    }

    public void setParent_message_id(long parent_message_id) {
        this.parent_message_id = parent_message_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }
}
