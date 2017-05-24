<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mancala.css">
    <title>Mancala</title>
</head>
<body>
<div id="main_container">
    <div id="content_wrapper">
        <div id="currentplayerindicator">
        <span>
            <c:choose>
                <c:when test="${sessionScope.isGameOver == true}">
                    <c:out value="${sessionScope.getWinner}"/> wins!
                </c:when>
                <c:otherwise>
                    <c:out value="${sessionScope.getActivePlayer}"/>'s turn.
                </c:otherwise>
            </c:choose>
        </span>
        </div>
        <div id="gameboard">
            <div class="fieldcontainer" id="p2fieldcontainer">
                <div class="holecontainer" id="p2holecontainer">
                    <c:choose>
                        <c:when test="${sessionScope.getActivePlayer eq 'Player 2'}">
                            <c:set var="holep2" value="active"/>
                            <c:set var="holep1" value="inactive"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="holep2" value="inactive"/>
                            <c:set var="holep1" value="active"/>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${sessionScope.isGameOver == true}">
                        <c:set var="holep1" value="inactive"/>
                        <c:set var="holep2" value="inactive"/>
                    </c:if>

                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=13'">
                        <c:out value="${sessionScope.stonesInFields[12]}"/>
                    </a></div>
                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=12'">
                        <c:out value="${sessionScope.stonesInFields[11]}"/>
                    </a></div>
                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=11'">
                        <c:out value="${sessionScope.stonesInFields[10]}"/>
                    </a></div>
                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=10'">
                        <c:out value="${sessionScope.stonesInFields[9]}"/>
                    </a></div>
                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=9'">
                        <c:out value="${sessionScope.stonesInFields[8]}"/>
                    </a></div>
                    <div class="${holep2}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=8'">
                        <c:out value="${sessionScope.stonesInFields[7]}"/>
                    </a></div>
                </div>
                <div class="kalaha" id="p1kalaha"><span><c:out value="${sessionScope.stonesInFields[6]}"/></span>
                </div>
            </div>
            <div class="fieldcontainer" id="p1fieldcontainer">
                <div class="kalaha" id="p2kalaha"><span><c:out value="${sessionScope.stonesInFields[13]}"/></span>
                </div>
                <div class="holecontainer" id="p1holecontainer">
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=1'">
                        <c:out value="${sessionScope.stonesInFields[0]}"/>
                    </a></div>
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=2'">
                        <c:out value="${sessionScope.stonesInFields[1]}"/>
                    </a></div>
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=3'">
                        <c:out value="${sessionScope.stonesInFields[2]}"/>
                    </a></div>
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=4'">
                        <c:out value="${sessionScope.stonesInFields[3]}"/>
                    </a></div>
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=5'">
                        <c:out value="${sessionScope.stonesInFields[4]}"/>
                    </a></div>
                    <div class="${holep1}"><a
                            onclick="javascript:location.href='${pageContext.request.contextPath}mancalaServlet?hole=6'">
                        <c:out value="${sessionScope.stonesInFields[5]}"/>
                    </a></div>
                </div>
            </div>
        </div>
        <div id="refreshmessage">
            <c:if test="${sessionScope.isGameOver == true}">
                <span>Refresh the page or click <a href="mancalaServlet">here</a> to play another game.</span>
                <c:remove var="currentGameState" scope="session"/>
            </c:if></div>
    </div>
</div>
</body>
</html>
