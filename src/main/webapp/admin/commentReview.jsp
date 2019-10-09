<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>评论审核页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	//点击标题弹出用户预览页面
	function formatBlogTitle(val,row){
		return "<a target='_blank' href='${pageContext.request.contextPath}/article/"+val.id+".html'>"+val.title+"</a>";
	}
	//提交审核结果
	function commentReview(state){
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请至少选择一条数据进行操作");
			return;
		}
		var strIds = [];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示","您确定要提交这<font color=red>"+selectedRows.length+"</font>条评论的审核吗?",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/review.do",
						{"ids":ids,"state":state},
						function(result){
							if(result.success){
								$.messager.alert("系统提示","提交成功");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示","提交失败,请稍后再试试吧");
							}
						},
						"json"
				)
			}
		})
	}


</script>

</head>

<body style="margin: 1px; font-family: microsoft yahei">
<table id="dg" title="评论审核" class="easyui-datagrid" fitColumns="true" pagination="true"
	url="${pageContext.request.contextPath}/admin/comment/list.do?state=0" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true" align="center"></th>
			<th field="id" width="20" align="center">编号</th>
			<th field="blog" width="200" align="center" formatter="formatBlogTitle">博客标题</th>
			<th field="userIp" width="50" align="center">用户的IP</th> 
			<th field="content" width="200" align="center">评论内容</th> 
			<th field="commentDate" width="50" align="center">评论日期</th> 
		</tr>
	</thead>
</table>
<div id="tb"> 
	<div>
		<a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCLs="icon-ok" plain="true">审核通过</a>
		<a href="javascript:commentReview(2)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
	</div>
</div>

</body>
</html>
