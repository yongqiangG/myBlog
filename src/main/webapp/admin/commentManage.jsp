<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>评论管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
</script>

</head>

<body style="margin: 1px; font-family: microsoft yahei">
	<table id="dg" title="评论管理" class="easyui-datagrid" fitcolumns="true" pagination="true" rownumbers="true"
	url="${pageContext.request.contextPath}/admin/comment/list.do" fit="true" toolbar="#tb">
<thead>
	<tr>
		<th field="cb" checkbox="true" align="center"></th>
		<th field="id" width="20" align="center">编号</th>
		<th field="userIp" width="200" align="center">用户Ip</th>
		<th field="content" width="50" align="center">内容</th>
		<th field="commentDate" width="50" align="center">发表日期</th>
		<th field="state" width="50" align="center">状态</th>
		<th field="blogType" width="50" align="center" formatter="formatBlogType">博客类型</th>
	</tr>
</thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openModifyBlogTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改博客</a>
		<a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除博客</a>
	</div>
	<div>
		&nbsp;根据标题检索:&nbsp;<input type="text" id="searchByTitle" size="20" onkeydown="if(event.KeyCode=13) searchBlogByTitle()"/>
		<a href="javascript:searchBlogByTitle()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
	</div>
</div>
</body>
</html>
