<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Johnny's Blog</title>
<!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${pageContext.request.contextPath}/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/static/css/clean-blog.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
  <script>
  	function checkData(){
  		var q = $("#q").val().trim();
  		if(q==null||q==''){
  			alert("请输入您要查询的关键字");
  			return false;
  		}
  		return true;
  	}
  </script>
</head>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">Johnny 高永强</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/index.html">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/blogger/aboutMe.html">About</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('static/img/team.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>Johnny's Blog</h1>
            <span class="subheading">与你分享生活的喜悦</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
      	<div style="float:right;">
      	<form action="${pageContext.request.contextPath}/q.html" method="post" onsubmit="return checkData()">
      		<h5>全文检索</h5>
	        <input type="text" id="q" name="q" value="${q}" placeholder="请输入要查询的关键字">
	        <button type="submit" class="btn btn-primary">搜索</button>
	    </form>
	    </div>
      	<div>
      		<h5>博客日期</h5>
			<ul>						
				<c:forEach items="${blogCountList}" var="blog">							
					<li><span><a href="${pageContext.request.contextPath}/index.do?releaseDateStr=${blog.releaseDateStr}">${blog.releaseDateStr}（${blog.blogCount }）</a></span></li>						
				</c:forEach>						
			</ul>
		</div>
		<div>
      		<h5>博客类别</h5>
			<ul>						
				<c:forEach items="${blogTypeList}" var="blog">							
					<li><span><a href="${pageContext.request.contextPath}/index.do?type_id=${blog.id}">${blog.typeName }（${blog.blogCount }）</a></span></li>						
				</c:forEach>						
			</ul>
		</div>
		<hr>
      	<!-- 在这里加载博客列表 -->
      	<div>
      		<jsp:include page="${mainPage}"></jsp:include>
      	</div>
      	
		<div>
			<h5>友情链接</h5>
			<ul>						
				<c:forEach items="${linkList }" var="link">
					<li><span><a href="${link.linkurl}" target="_blank">${link.linkname }</a></span></li>
				</c:forEach>											
			</ul>
		</div>
      </div>
    </div>
  </div>

  <hr>

  <!-- Footer -->
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <ul class="list-inline text-center">
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-qq fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-zhihu fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="https://github.com/yongqiangG">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
          </ul>
          <p class="copyright text-muted">Copyright &copy; Johnny 高永强 2019</p>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="${pageContext.request.contextPath}/static/js/clean-blog.min.js"></script>
  <script type="text/javascript">
  	//加载博客列表
  	/* $(function(){
  		$.ajax({
  			url:"${pageContext.request.contextPath}/admin/blog/list.do",
  			type:"post",
  			data:{"page":1,"rows":3},
  			dataType:"json",
  			success:function(result){
  				var rows = result.rows;
  				var total = result.total;
  				for(var i=0;i<rows.length;i++){
  					var blogId = rows[i].id;
  					var title = rows[i].title;
  					var summary = rows[i].summary;
  					var releaseDate = rows[i].releaseDate;
  					var clickHit = rows[i].clickHit;
  					var replyHit =rows[i].replyHit;
  					createBlog(title,summary,releaseDate,blogId,clickHit,replyHit);
  				}
  			},
  			error:function(){
  				alert("博客加载失败,请刷新试试");
  			}
  		});
  	})
  	//创建博客DIV
  	function createBlog(title,summary,releaseDate,blogId,clickHit,replyHit){
  		var sdiv="";
  		sdiv+='<div class="post-preview">';
  		sdiv+='<a href="article.jsp?blogId=';
  		sdiv+=blogId+'\">';
  		sdiv+='<h2 class="post-title">'
  		sdiv+=title;
  		sdiv+='</h2><h3 class="post-subtitle">';
  		sdiv+=summary;
  		sdiv+='</h3></a><p class="post-meta">Posted by<a href="#">&nbsp;高永强&nbsp;</a>on ';
  		sdiv+=releaseDate;
  		sdiv+='&nbsp;&nbsp;&nbsp;&nbsp;阅读(';
  		sdiv+=clickHit;
  		sdiv+=')&nbsp;&nbsp;评论(';
  		sdiv+=replyHit;
  		sdiv+=')';
  		sdiv+='</p></div><hr>';
		var $div=$(sdiv);
		$div.data("blogId",blogId);
		$("#blog").append($div);
  	}
  //加载更多博客
  	var page=1;
  	function moreBlog(){
  		page=page+1;
  		$.ajax({
  			url:"${pageContext.request.contextPath}/admin/blog/list.do",
  			type:"post",
  			data:{"page":page,"rows":3},
  			dataType:"json",
  			success:function(result){
  				var rows = result.rows;
  				var total = result.total;
  				for(var i=0;i<rows.length;i++){
  					var blogId = rows[i].id;
  					var title = rows[i].title;
  					var summary = rows[i].summary;
  					var releaseDate = rows[i].releaseDate;
  					var clickHit = rows[i].clickHit;
  					var replyHit =rows[i].replyHit;
  					createBlog(title,summary,releaseDate,blogId,clickHit,replyHit);
  				}
  				if(rows.length==0){
  					alert("没有更多博客了!");
  				}
  			},
  			error:function(){
  				alert("博客添加失败,请刷新试试");
  			}
  		});
  	}
  	//根据日期查询
  	function findByDate(){
  		
  	} */
  	
  </script>
</body>
</html>