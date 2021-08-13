<%-- 
    Document   : right-div
    Created on : Aug 12, 2021, 4:34:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="right pl-1 no-collapse">
            <p class="title-article font-sans font-bold">Digital News</p>
            <p class="content-article font-sans mb-025">${lastArticle.shortcut}</p>
            <p class="title-article font-sans font-bold mt-05 mb-025">Search</p>
            <form class="mt-05" action="search" method="GET" autocomplete="on" >
                <input type="text" name="keyword" class="search-box">
                <input type="submit" value="Go" class="submit-bt font-sans ml-025">
            </form>
            <p class="title-article font-sans font-bold mt-1 mb-025">Last Articles</p>
            <c:forEach items="${last5List}" var="a">
                <a class="a-reset pointer font-sans" href="home?id=${a.id}">
                    <p class="recent-5-articles underline">${a.title}</p>
                </a>
            </c:forEach>
        </div>
    </body>
</html>
