package resource;

import java.sql.Timestamp;

public class Message {

    private long messageId;
    private long creatorId;
    private long recipientId;
    private long parentMessageId;
    private String subject;
    private String messageBody;
    private boolean isRead;
    private Timestamp creationDate;
    private Timestamp expirationDate;

    public Message(
            long messageId, long creatorId, long recipientId, long parentMessageId,
            String subject, String messageBody, boolean isRead,
            Timestamp creationDate, Timestamp expirationDate){

        this.messageId = messageId;
        this.creatorId = creatorId;
        this.recipientId = recipientId;
        this.parentMessageId = parentMessageId;
        this.subject = subject;
        this.messageBody = messageBody;
        this.isRead = isRead;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long message_id) {
        this.messageId = messageId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public long getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(long parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }
}
