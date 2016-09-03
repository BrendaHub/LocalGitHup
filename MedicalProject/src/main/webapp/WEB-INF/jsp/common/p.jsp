<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inline pull-right page">
	${pageTotal } 条记录 ${pageIndex }/${pageCount } 页
	<c:if test="${pageIndex>=2}">
            <a onclick="toPage(${pageIndex-1})" class="current prev" href="##">
                <i></i>
                &lt;上一页 
            </a>
        </c:if>
	<c:choose>
            <c:when test="${pageCount<10}">
                <c:forEach begin="1" end="${pageCount}" step="1" var="index">
                    <c:if test="${pageIndex==index}">
                        <a href="#" class="current">${index}</a>
                    </c:if>
                    <c:if test="${pageIndex!=index}">
                        <a href="javascript:toPage('${index}')">${index} </a>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:when test="${pageIndex<10}">
                <c:forEach var="index" begin="1" end="9" step="1">
                    <c:if test="${pageIndex == index}">
                        <a href="#" class="current">${index} </a>
                    </c:if>
                    <c:if test="${pageIndex != index}">
                        <a onclick="toPage(${index})">${index} </a>
                    </c:if>
                </c:forEach>
                <c:if test="${pageCount >10 }">
                    <span class="pagination-break">...</span>
                </c:if>
                <li>
                    <a onclick="toPage(${pageCount})">${pageCount} </a>
                </li>
            </c:when>
            <c:when test="${pageIndex==10 and pageCount < 13 }">
            	<a onclick="toPage(1)">1 </a>
            	<span class="pagination-break">... </span>
            	
            	<c:forEach var="index" begin="3" end="${pageCount }" step="1">
                    <c:if test="${pageIndex == index}">
                        <a href="#" class="current">${index}</a>
                    </c:if>
                    <c:if test="${pageIndex != index}">
                        <a onclick="toPage(${index})">${index} </a>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:when test="${(pageIndex+1) < pageCount}">
                <a onclick="toPage(1)">1</a>
                <span class="pagination-break">...</span>
                <c:forEach var="index" begin="${pageIndex-1}" end="${pageIndex+1}" step="1">
                    <c:if test="${pageIndex == index}">
                        <a href="#" class="current">${index} </a>
                    </c:if>
                    <c:if test="${pageIndex != index}">
                        <a onclick="toPage(${index})">${index} </a>
                    </c:if>
                </c:forEach>
                <c:if test="${(pageIndex+2) < pageCount}">
                    <span class="pagination-break">...</span>
                </c:if>
                <c:if test="${(pageIndex+1) < pageCount}">
                    <a onclick="toPage(${pageCount})">${pageCount} </a>
                </c:if>
            </c:when>
            <c:otherwise>
                <a onclick="toPage(1)">1</a>
                <span class="pagination-break">...</span>
                <c:forEach var="index" begin="${pageCount-3}" end="${pageCount}" step="1">
                    <c:if test="${pageIndex == index}">
                        <a href="#" class="current">${index} </a>
                    </c:if>
                    <c:if test="${pageIndex  != index}">
                        <a onclick="toPage(${index})">${index} </a>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>   
        <c:if test="${pageIndex < pageCount}">
            <a onclick="toPage(${pageIndex+1})" class="next" href="##">
            	下一页 &gt;
                <i></i>
            </a>
        </c:if>
</div>