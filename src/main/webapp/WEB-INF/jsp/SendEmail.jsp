<%@ page import = "java.io.*,java.util.*,javax.mail.*"%>
<%@ page import = "javax.mail.internet.*,javax.activation.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="ir.maktab.service.VerifyCodeUserService" %>

<%
    String result;

    // Recipient's email ID needs to be mentioned.
    String to = (String) session.getAttribute("email");

    // Sender's email ID needs to be mentioned
    String from = "mcmohd@gmail.com";

    // Assuming you are sending email from localhost
    String host = "smtp.gmail.com";

    // Get system properties object
    Properties properties = System.getProperties();

    // Setup mail server
    properties.setProperty("mail.smtp.host", host);

    // Get the default Session object.
    Session mailSession = Session.getDefaultInstance(properties);

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(mailSession);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        // Set Subject: header field
        message.setSubject("This is the Subject Line!");

        // Now set the actual message
        int code = (int)(Math.random()*(999999-100000+1)+1000000);
        message.setText("verify code is : " + code);

        // Send message
        Transport.send(message);
        result = "Sent message successfully....";
        session.setAttribute("verifyCode",code);
    } catch (MessagingException mex) {
        mex.printStackTrace();
        result = "Error: unable to send message....";
    }
%>

<html>
<head>
    <title>Send Email using JSP</title>
</head>

<body>
<center>
    <h1>Send Email using JSP</h1>
</center>

<p align = "center">
    <%=("Result: " + result + "\n")
    %>
</p>
</body>
</html>