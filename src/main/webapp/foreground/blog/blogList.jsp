<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="data_list">
  		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>&nbsp;最新博客
		</div>	
		<div class="datas">
			<ul>	
				<c:forEach items="${blogList}" var="blog">
					<li style="margin-bottom: 30px">
					  	<span class="title">
					  		<img alt="文章类型" src="${pageContext.request.contextPath}/static/userImages/yuan.jpg">
					  		<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title }</a>
					  	</span>
					  	<span class="summary">摘要: ${blog.summary }....</span>
					  	<span class="img">
					  		<c:forEach items="${blog.imageList }" var="image">
					  			<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${image }</a>
					  			&nbsp;&nbsp;
					  		</c:forEach>
	
					  	</span>
					  	<span class="info">
					  		<font color="#999"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></font> &nbsp;&nbsp;
					  		<font color="#33a5ba"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">阅读</a><font color="#999">(${blog.clickHit })</font>&nbsp;&nbsp;</font>
					  		<font color="#33a5ba"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">评论</a><font color="#999">(${blog.replyHit })</font>&nbsp;&nbsp;</font>  	
					  	</span>
					</li>
					<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:10px;" />	
				</c:forEach>																											
			</ul>			
		</div>  
		
		<div style="text-align: center;">
			<nav>
			  <ul class="pagination">
			    ${pageCode }
			 </ul>
			</nav>
		</div>			
</div>
