<%-- 
    Document   : chat
    Created on : 19-abr-2019, 11:13:58
    Author     : Geraldo
--%>

<%@page import="entities.MessageConversation"%>
<%@page import="ejb.MessageConversationFacade"%>
<%@page import="java.util.List"%>
<%@page import="entities.Conversation"%>
<%@page import="entities.Buyer"%>
<%@page import="ejb.ConversationFacade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!------ Include the above in your HEAD tag ---------->


<html>
    <head>
        <jsp:include page="../../partials/resource.jsp" />
        <link rel="stylesheet" href="/BookTwoLife-JPA-war/views/buyer/style/chatstyle.css"/>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />
        <%
            Buyer buyer = (Buyer) session.getAttribute("userlogin");
            Integer idConversation = (Integer) session.getAttribute("actualconver");
            ConversationFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/ConversationFacade!ejb.ConversationFacade");
            List<Conversation> conversations = cf.findWhereBuyer(buyer);
            Conversation actual = null;
        %>
        <div class="container">
            <div class="row">
                <div class="col-3"><h2>Conversaciones</h2>
                    <div class="list-group">
                        <%
                            for (Conversation conversation : conversations) {
                                if (conversation.getId() == idConversation) {
                                    actual = conversation;
                                }


                        %>
                        <a href="#" class="list-group-item list-group-item-action active">
                            <%=conversation.getIdSeller().getFname()%>
                        </a>
                        <%}%>
                        <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
                        <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
                        <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
                        <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">Vestibulum at eros</a>
                    </div>
                </div>
                <%if (actual != null) {

                %>
                <div class="col message"><h2>Conversacion con <%=actual.getIdSeller().getFname()%></h2>
                    <div class="msg-group center">
                        <%
                            MessageConversationFacade mcf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/MessageConversationFacade!ejb.MessageConversationFacade");
                            List<MessageConversation> messages = mcf.findWhereConver(actual);
                            for (MessageConversation message : messages) {

                                if (message.getIdSeller() == null) {

                        %>    
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-subtitle mb-2 text-muted text-right"><%=message.getIdBuyer().getFname()%></h6>
                                <p class="card-text float-right"><%=message.getMessage()%></p>
                            </div>
                        </div>    

                        <%     } else {
                        %>
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-subtitle mb-2 text-muted text-left"><%=message.getIdSeller().getFname()%></h6>
                                <p class="card-text float-left"><%=message.getMessage()%></p>
                            </div>
                        </div>

                        <%}
                            }%>

                    </div>
                    <div class="input-mess">

                        <form action="/BookTwoLife-JPA-war/FrontController">
                            <textarea name="message" id="input-box" class="form-control" rows="1" placeholder="Say something..."></textarea>
                            <input type="hidden" name="command" value="SendMessageCommand">
                            <input type="hidden" name="id" value="<%=actual.getId()%>">
                            <span class="input-group-btn">
                                <button class="btn btn-secondary" type="commit">send</button>
                            </span>
                        </form>
                    </div>

                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>