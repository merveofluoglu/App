<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>

</head>
<body>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

    <a href="${pageContext.request.contextPath}/jsp/post.jsp" >View / Edit / Delete / Add Posts</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/upload-file.jsp" >Upload File</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/list-file.jsp" >List Files</a>
    <!--<br/><a href="${pageContext.request.contextPath}/jsp/uploadFile-result.jsp" >List File</a>-->
    <br/><a href="${pageContext.request.contextPath}/jsp/get_favourites.jsp" >Favourites</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/profile.jsp" >Profile</a>

    <%
        String role = (String) session.getAttribute("role");
        if (Objects.equals(role, "admin")) { %>
            <br/><a href="${pageContext.request.contextPath}/jsp/admin-dashboard/index.html" >Admin Dashboard</a>
    <% }
    %>

    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit.</p>
    <div class="sticky" id="messageBox">
        <div id="chats">
        </div>
        <div id="messages">

        </div>
        <div id="sendMessage">
            <input style="width: 75%;" id="newMessage" type="text"><button style="width: 25%;" onclick="sendMessage()">SEND</button>

        </div>
        <span id="messageButton" onclick="expandMessages()">Messages</span>
    </div>
    <script>


        function expandMessages(){
            const messageBox = document.getElementById("messageBox");
            const chats = document.getElementById("chats");
            const messages = document.getElementById("messages");
            const sendMessage = document.getElementById("sendMessage");


            if (messageBox.style.height === '300px'){
                messageBox.style.height = '30px';
                chats.style.display = "none";
                messages.style.display = "none";
                sendMessage.style.display = "none";
                return
            }

            loadChats();
        }

        function loadChats(){
            const messageBox = document.getElementById("messageBox");
            const chats = document.getElementById("chats");
            const messages = document.getElementById("messages");
            const sendMessage = document.getElementById("sendMessage");

            messageBox.style.height = '300px';
            chats.style.display = "block";
            messages.style.display = "none";
            sendMessage.style.display = "none";

            $.ajax({
                    url: "${pageContext.request.contextPath}/message/userchatreceivers",
                    method: "GET",
                    data: {},
                    success: function (response) {
                        showChat(response.data);
                    },
                    error: function () {
                        alert("error, couldn't get user chats");
                    }
                }
            );
        }

        function showChat(chatsReceivers){
            const chatsBox = document.getElementById('chats');
            chatsBox.innerHTML = "";

            for (let i = 0; i < chatsReceivers.length; i++) {
                chatsBox.appendChild(createChatNode(chatsReceivers[i]));
            }
        }

        function createChatNode(recipientDict){
            const receiver = recipientDict['name'] + " " + recipientDict['surname'];
            const chatNode = document.createElement("div");

            const receiverNameNode = document.createElement("span");
            const receiverTextNode = document.createTextNode(receiver);
            receiverNameNode.appendChild(receiverTextNode);

            const receiverId = document.createElement("span");
            const receiverIdTextNode = document.createTextNode(recipientDict['userId']);
            receiverId.appendChild(receiverIdTextNode);
            receiverId.setAttribute("style", "display: none;");

            chatNode.appendChild(receiverNameNode);
            chatNode.appendChild(receiverId);

            chatNode.addEventListener("click", function (){
                loadSelectedChatMessages(chatNode);
            } )

            chatNode.setAttribute("class", "chat");
            return chatNode;
        }

        function loadSelectedChatMessages(chatNode){
            const recipientIdVal = chatNode.children[1].textContent;
            $.ajax({
                    url: "${pageContext.request.contextPath}/message/messages_of_chat",
                    method: "GET",
                    data: {
                        recipientId: recipientIdVal
                    },
                    success: function (response) {
                        loadSelectedChatPage(response.data)
                    },
                    error: function () {
                        alert("error, couldn't get messages of chat");
                    }
                }
            );
        }

        function loadSelectedChatPage(messagesList){
            const chats = document.getElementById("chats");
            chats.setAttribute("style", "display: none;");

            const chatSection = document.getElementById("messages");
            chatSection.setAttribute("style", "display: block;");
            chatSection.innerHTML = "";


            const recipientIdNode = document.createElement("span");


            $.ajax({
                    url: "${pageContext.request.contextPath}/message/message_owner",
                    method: "GET",
                    data: {
                        creatorId: messagesList[0]['creatorId']
                    },
                    success: function (response) {
                        if (response.data === "user"){
                            const recipientIdTextNode = document.createTextNode(messagesList[0]['recipientId']);
                            recipientIdNode.appendChild(recipientIdTextNode);
                        }
                        else{
                            const recipientIdTextNode = document.createTextNode(messagesList[0]['creatorId']);
                            recipientIdNode.appendChild(recipientIdTextNode);
                        }
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
            recipientIdNode.setAttribute("style", "display: none;");
            chatSection.appendChild(recipientIdNode);


            const backButtonNode = document.createElement("div");
            const backButtonTextNode = document.createTextNode("Back");
            backButtonNode.setAttribute("class", "backButton");
            backButtonNode.appendChild(backButtonTextNode);

            backButtonNode.addEventListener("click", function(){
                loadChats();
            })

            chatSection.appendChild(backButtonNode);

            messagesList.forEach(createMessageNode)

            const newMessage = document.getElementById("sendMessage");
            newMessage.setAttribute("style", "display: block; height: 10%;");
        }

        function createMessageNode(messageDict){
            const chatSection = document.getElementById("messages");

            const messageNode = document.createElement("span");
            const messageTextNode = document.createTextNode(messageDict["messageBody"]);
            messageNode.appendChild(messageTextNode);

            console.log("Checking the owner of" + messageDict['recipientId']);

            $.ajax({
                    url: "${pageContext.request.contextPath}/message/message_owner",
                    method: "GET",
                    data: {
                        creatorId: messageDict['creatorId']
                    },
                    success: function (response) {
                        if (response.data === "user"){
                            messageNode.setAttribute("class", "message userMessage");
                        }
                        else{
                            messageNode.setAttribute("class", "message otherMessage");
                        }
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
            const lineNode = document.createElement("div");
            lineNode.setAttribute("class", "messageLine");
            lineNode.appendChild(messageNode);
            chatSection.appendChild(lineNode);
        }

        function sendMessage(){
            const message = document.getElementById("newMessage").value;
            const recipientId = document.getElementById("messages").children[0].textContent;
            console.log(recipientId)
            const _data = {
                'recipientId': recipientId,
                'subject': "testing",
                'messageBody': message,
            };
            console.log(_data);
            $.ajax({
                    url: "${pageContext.request.contextPath}/message/add",
                    method: "POST",
                    data: _data,
                    success: function (response) {
                        console.log("success");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

    </script>

    <style>
        #messageBox {
            position: sticky;
            float: right;
            height: 30px;
            width: 200px;
            bottom: 0;
            background-color: red;
            text-align: center;
        }
        .backButton{
            background-color: greenyellow;
        }
        #chats{
            position: relative;
            display: none;
            background-color: white;
            height: 90%;
        }
        .chat{
            position: relative;
            border-width: 2px;
            border-style: solid;
            top: 2px;
            bottom: 2px;
        }
        #messages{
            display: none;
            background-color: deepskyblue;
            height: 80%;
        }
        .messageLine{
            display: block;
            width: 100%;
            height: 40px;
        }
        .message{
            display: block;
            padding: 2px 1px 2px 1px;
        }
        .userMessage{
            color: blue;
            float: left;
        }
        .otherMessage{
            color: red;
            float: right;
        }
        #sendMessage{
            display: none;
            height: 10%;
        }
        #messageButton{
            position: relative;
            display: block;
            height: 10%;
            width: 100%;
        }

    </style>
</body>
</html>
