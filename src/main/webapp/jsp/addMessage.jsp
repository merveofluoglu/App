<%--
  Created by IntelliJ IDEA.
  User: mehmetsanisoglu
  Date: 13.04.2023
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Message</title>
</head>
<body>
    <form method="post"  action="/message/add">
        <label for="creator_id">Creator id:</label>
        <input id="creator_id" name="creator_id" type="text"/><br/>

        <label for="recipient_id">Recipient id:</label>
        <input id="recipient_id" name="recipient_id" type="text"/><br/>

        <label for="parent_message_id">Parent Message Id:</label>
        <input id="parent_message_id" name="parent_message_id" type="text"/><br/>

        <label for="subject">Subject:</label>
        <input id="subject" name="subject" type="text"/><br/>

        <label for="message_body">Price:</label>
        <input id="message_body" name="message_body" type="text"/><br/>

        <button type="submit">Submit</button><br/>
    </form>
</body>
</html>
