<%-- 
    Document   : search
    Created on : Aug 12, 2021, 4:36:58 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" type="text/css" href="public/css/style.css">
    </head>
    <body>
        <!-- Main Wrapper -->
        <div class="main-wrapper no-collapse">
            <!-- Header -->
            <%@include file="component/header.jsp" %>
            <!-- Main Body -->
            <div class="main-body no-collapse flex pt-05 pr-4 pb-1">
                <!-- Left -->
                <div class="left pl-5 pr-1 flex-col">
                    <c:choose>
                        <%-- when user enter go-to-page on url and it is 0 or not number--%>
                        <c:when test="${currentPage == 0}">
                            <p class="title-article font-sans font-bold">
                                Go-to-page must be a positive number and not exceed total number of
                                found pages.
                            </p>
                        </c:when>
                        <%-- when not found any articles match keyword --%>
                        <c:when test="${numOfPage==0}">
                            <p class="title-article font-sans font-bold">No articles found.</p>
                        </c:when>
                        <%-- when user enter go-to-page on url and it exceeds number of found pages--%>
                        <c:when test="${currentPage>numOfPage}">
                            <p class="title-article font-sans font-bold">Exceed number of found pages.</p>
                        </c:when>
                        <%-- when there are found articles and to-page in range --%>
                        <c:when test="${numOfPage > 0}">
                            <c:forEach items="${searchList}" var="article">
                                
                                    <div class="mb-4 font-sans">
                                        <a class="a-reset pointer" href="home?id=${article.id}">
                                        <p class="title-article font-bold underline">${article.title}</p>
                                        </a>
                                        <img class="img-shortcut mr-1 mb-1" src="${article.image}">
                                        ${article.shortcut}
                                    </div>
                                
                            </c:forEach>
                            <center>
                                <a class="ma-05 a-reset font-sans" href="search?keyword=${keyword}&currentPage=${currentPage-1}">
                                    <span class="black">Page </span>
                                </a>
                                <c:if test="${currentPage > 1}">
                                    <a class="ma-05 a-reset font-sans" href="search?keyword=${keyword}&currentPage=${currentPage-1}">
                                        <span class="black font-bold"><</span>
                                    </a>
                                </c:if>
                                <c:forEach var="pageNum" begin="${pageFrom}" end="${pageTo}">
                                    <c:if test="${pageNum==currentPage}" var="isCurPage">
                                        <span class="ma-05 font-bold font-sans">${currentPage}</span>
                                    </c:if>
                                    <c:if test="${!isCurPage}">
                                        <a class="ma-05 a-reset font-sans" href="search?keyword=${keyword}&currentPage=${pageNum}">
                                            <span class="underline black">${pageNum}</span>
                                        </a>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage < numOfPage}">
                                    <a class="ma-05 a-reset font-sans" href="search?keyword=${keyword}&currentPage=${currentPage+1}">
                                        <span class="black font-bold">></span>
                                    </a>
                                </c:if>
                            </center>
                        </c:when>
                        <%-- not enter keyword --%>
                        <c:otherwise>
                            <p class="title-article font-sans font-bold">Make sure you have entered keyword.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- Right -->
                <%@include file="component/right-div.jsp" %>
            </div>
            <div class="footer"></div>
        </div>
        <!-- End of Main Wrapper -->
    </body>
</html>
