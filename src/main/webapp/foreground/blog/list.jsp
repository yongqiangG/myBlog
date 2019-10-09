<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div>
  		<h3 style="text-align:center;">最新博客</h3>
      	<div id="blog">
      	<c:forEach items="${blogList}" var="blog">
      		<div class="post-preview">
  				<a href="${pageContext.request.contextPath}/article/${blog.id}.html">
  				<h2 class="post-title">
  				${blog.title}
  				</h2>
				<h3 class="post-subtitle">
  				${blog.summary}
  				</h3>
			</a>
			<p class="post-meta">Posted by<a href="#">&nbsp;高永强&nbsp;
	    		</a>
				on&nbsp;<fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy年MM月dd日"/>
  				&nbsp;&nbsp;&nbsp;&nbsp;
				阅读(${blog.clickHit})&nbsp;&nbsp;
				评论(${blog.replyHit})
  			</p>
		</div><hr>
		</c:forEach>
      	</div>
      	<ul class="pagination">
		  ${pageCode}
		</ul>
		
		
				
</div>
