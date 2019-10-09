<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div>
  		<h3 style="text-align:center;">搜索&nbsp;<font color="red">${q}</font>&nbsp;的结果&nbsp;(总共搜索到&nbsp;${resultTotal}&nbsp;条记录)</h3>
      	<div id="blog">
      	<c:choose>
      		<c:when test="${blogList.size()==0}">
      			<div class="post-preview">
      				未查询到结果,请换个关键字看看
      			</div>
      		</c:when>
      		<c:otherwise>
      			<c:forEach var="blog" items="${blogList}">
      				<div class="post-preview">
      					<a href="${pageContext.request.contextPath}/article/${blog.id}.html" target="_blank">
	  						<h2 class="post-title">
	  						${blog.title}
	  						</h2>
	  						<h3 class="post-subtitle">
	  						${blog.content}...
	  						</h3>
  						</a>
  						<a href="${pageContext.request.contextPath}/article/${blog.id}.html" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;发布日期:${blog.releaseDateStr}</a>
      				</div>
      			</c:forEach>
      		</c:otherwise>
      	</c:choose>
      	</div>
</div>
<hr>
<ul class="pagination">
	${pageCode}
</ul>
