package resource;

import java.sql.Timestamp;

public class Message {

    private long message_id;
    private long creator_id;
    private long recipient_id;
    private long parent_message_id;
    private String subject;
    private String message_body;
    private Timestamp creation_date;
    private Timestamp expiration_date;
    private boolean is_read;


}
