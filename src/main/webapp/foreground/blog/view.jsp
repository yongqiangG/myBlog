<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	//显示所有评论
	function showComments(){
		$(".comment").show();
	}
	//重新加载验证码
	function loadImage(){
		$("#randImage").attr("src",'${pageContext.request.contextPath}/image.jsp?'+Math.random());
	}
	//提交评论
	function submitData(){
		var content = $("#commentContent").val();
		var imageCode = $("#imageCode").val();
		if(content==null||content==''){
			alert("评论不能为空");
		}else if(imageCode==null||imageCode==''){
			alert("验证码不能为空")
		}else{
			$.post("${pageContext.request.contextPath}/comment/save.do",{'content':content,'imageCode':imageCode,'blog.id':'${blog.id}'},function(result){
				if(result.success){
					window.location.reload();
					alert("评论提交成功,审核成功后显示");
				}else{
					alert(result.errorInfo);
				}
			},"json");
		}
	}
	//关键字查询
	function query1(keyWord){
		$("#q1").val(keyWord);
		$("#queryForm").submit();
	}
</script>
<div id="title" style="text-align:center;">
    <h3>${blog.title}</h3>
    <div class="bshare-custom">
    	<a title="分享到QQ空间" class="bshare-qzone"></a>
    	<a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
    	<a title="更多" class="bshare-more bshare-more-icon more-style-addthis"></a>
    	<script type="text/javascript" charset="UTF-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=&amp;lang=zh"></script>
    	<script type="text/javascript" charset="UTF-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
    </div>
    <div>
    	发布时间:&nbsp;(<fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>)
    	&nbsp;&nbsp;博客类别&nbsp;:${blog.blogType.typeName}
    	&nbsp;&nbsp;阅读&nbsp;:${blog.clickHit}
    	&nbsp;&nbsp;评论&nbsp;:${blog.replyHit}
    </div>
</div>
<hr>
<div id="article">
    ${blog.content}
</div>
<hr>
<div>
	<font><strong>关键字:</strong></font>
	<c:choose>
		<c:when test="${keyWords==null}">
			&nbsp;&nbsp;无
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/q.html" method="post" id="queryForm">
				<input type="hidden" id="q1" name="q"/>
				<c:forEach var="keyWord" items="${keyWords}">
					&nbsp;&nbsp;<a href="javascript:query1('${keyWord}')" style="color:red;" target="_blank">${keyWord}</a>&nbsp;&nbsp;
				</c:forEach>
			</form>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<div>
	${pageCode}
</div>
<hr>
<div>
	<strong>评论信息</strong>
	<c:if test="${commentList.size()>10}">
		<a href="javascript:showComments()" style="float:right;">点击显示所有评论</a>
	</c:if>
	<hr>
	<div>
		<c:choose>
			<c:when test="${commentList.size()==0}">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${commentList}" varStatus="status">
					<c:choose>
						<c:when test="${status.index<10}">
							<div>
								<span>
									<strong>${status.index+1}楼</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<strong>${comment.userIp}:</strong>&nbsp;
									${comment.content}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									(<fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>)
								</span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="comment" style="display:none;">
								<strong>${status.index+1}楼</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<strong>${comment.userIp}:</strong>&nbsp;
								${comment.content}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								(<fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>)
							</div>
						</c:otherwise>
						
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<hr>
<div>
	<strong>发表评论</strong>
	<hr>
	<div>
		<textarea rows="3" style="width:100%" id="commentContent" placeholder="快来说两句吧"></textarea>
	</div>
	<div>
		验证码:<input type="text" name="imageCode" id="imageCode" size="10"
		onkeydown="if(event.keyCode==13) submitData()"/>
		&nbsp;<img src="${pageContext.request.contextPath}/image.jsp" name="randImage" id="randImage"
		title="换一张试试" onclick="javascript:loadImage()" width="60px" height="18px" border="1" align="absmiddle"/>
		<button type="button" onclick="submitData()" style="float:right;">提交评论</button>
	</div>
	<hr>
</div>