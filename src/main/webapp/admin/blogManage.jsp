<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>博客管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script>
	//博客类型id转为博客类型名称
	function formatBlogType(val,row){
		return val.typeName;
	}
	//根据标题检索博客
	function searchBlogByTitle(){
		$("#dg").datagrid('load',{"title":$("#searchByTitle").val()});
	}
	//打开修改博客页面
	function openModifyBlogTab(){
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要修改的博客数据");
			return;
		}
		var row=selectedRows[0];
		window.parent.openTab("修改博客","modifyBlog.jsp?id="+row.id,"icon-writeBlog");
	}
	//删除博客信息
	function deleteBlog(){
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请至少选择一条博客信息进行删除");
			return;
		}
		var strIds = [];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示","你确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗?",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/blog/delete.do",{"ids":ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已成功删除博客数据");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","删除失败,请稍后再试试吧");
					}
				},"json");
			}
		})
	}
	
</script>
</head>
<body style="margin: 1px; font-family: microsoft yahei">
<table id="dg" title="博客管理" class="easyui-datagrid" fitcolumns="true" pagination="true" rownumbers="true"
	url="${pageContext.request.contextPath}/admin/blog/list.do" fit="true" toolbar="#tb">
<thead>
	<tr>
		<th field="cb" checkbox="true" align="center"></th>
		<th field="id" width="20" align="center">编号</th>
		<th field="title" width="200" align="center">博客标题</th>
		<th field="releaseDate" width="50" align="center">发布日期</th>
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
