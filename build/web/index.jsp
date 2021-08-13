<%-- 
    Document   : index
    Created on : Aug 12, 2021, 4:36:34 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="public/css/style.css">
        <title>Home</title>
    </head>
    <body>
        <!-- Main Wrapper -->
        <div class="main-wrapper no-collapse">
            <!-- Header -->
            <%@include file="component/header.jsp" %>
            <!-- Main Body -->
            <div class="main-body no-collapse flex pt-05 pr-4 pb-1">
                <c:if test="${exception!=null}" var="isException">
                    <p class="title-article font-sans font-bold ml-5">${exception.message}</p>
                </c:if>
                <c:if test="${!isException}">
                    <!-- Left -->
                    <div class="left pl-5 pr-1">
                        <p class="title-article font-sans font-bold">${displayArticle.title}</p>
                        <img class="img-article" src="${displayArticle.image}">
                        <p class="content-article font-sans pb-1">${displayArticle.content}</p>
                        <p class="author-article font-sans pt-05"><img class="pr-05 author-img">
                            ${displayArticle.authorLine}</p>
                    </div>
                    <!-- Right -->
                    <%@include file="component/right-div.jsp" %>
                </c:if>
            </div>
            <!-- Footer -->
            <div class="footer no-repeat">
            </div>
        </div>
        <!-- End of Main Wrapper -->
    </body>
</html>
