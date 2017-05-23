<%@ page import="nl.Servlet.GameState" %>
<%--
  Created by IntelliJ IDEA.
  User: ckyoung
  Date: 22-May-17
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mancala.css">
    <title>Mancala</title>
</head>
<body>
<% GameState currentGameState = (GameState) session.getAttribute("currentGameState");%>
<div id="main_container">
    <div id="currentplayerindicator">
        <p>
            <c:choose>
                <c:when test="${sessionScope.isGameOver == true}">
                    <c:out value="${sessionScope.getWinner}"/> wins!
                </c:when>
                <c:otherwise>
                    <c:out value="${sessionScope.getActivePlayer}"/>'s turn.
                </c:otherwise>
            </c:choose>

        </p>
    </div>
    <div id="gameboard">
        <div class="fieldcontainer" id="p2fieldcontainer">
            <div class="holecontainer" id="p2holecontainer">
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=13'">

                    <%=currentGameState.getStonesOfHole(13)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=12'">
                    <%=currentGameState.getStonesOfHole(12)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=11'">
                    <%=currentGameState.getStonesOfHole(11)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=10'">
                    <%=currentGameState.getStonesOfHole(10)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=9'">
                    <%=currentGameState.getStonesOfHole(9)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=8'">
                    <%=currentGameState.getStonesOfHole(8)%>
                </a></div>
            </div>
            <div class="kalaha" id="p2kalaha"><%=currentGameState.getStonesOfHole(7)%>
            </div>
        </div>
        <div class="fieldcontainer" id="p1fieldcontainer">
            <div class="kalaha" id="p1kalaha"><%=currentGameState.getStonesOfHole(14)%>
            </div>
            <div class="holecontainer" id="p1holecontainer">
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=1'">
                    <%=currentGameState.getStonesOfHole(1)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=2'">
                    <%=currentGameState.getStonesOfHole(2)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=3'">
                    <%=currentGameState.getStonesOfHole(3)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=4'">
                    <%=currentGameState.getStonesOfHole(4)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=5'">
                    <%=currentGameState.getStonesOfHole(5)%>
                </a></div>
                <div class="p2hole"><a
                        onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=6'">
                    <%=currentGameState.getStonesOfHole(6)%>
                </a></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
