<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>写博客页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.all.min.js">
	
</script>
<!--建议手动加载语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	//发布博客
	function submitData(){
		//取值
		var title=$("#title").val();
		var blogTypeId=$("#blogTypeId").combobox("getValue");
		var keyWord=$("#keyWord").val();
		var content=UE.getEditor("editor").getContent();
		var summary=UE.getEditor("editor").getContentTxt().substr(0,155);
		//判断非空
		if(title==null || title==''){
			$.messager.alert("系统提示","博客标题不能为空");
		} else if(blogTypeId==null || blogTypeId==''){
			$.messager.alert("系统提示","博客类别不能为空");
		} else if(content==null || content==''){
			$.messager.alert("系统提示","博客内容不能为空");
		} else {
			$.post("${pageContext.request.contextPath}/admin/blog/save.do",{'id':'${param.id}','title':title,'content':content,'blogType.id':blogTypeId,'summary':summary,'keyWord':keyWord},
				function(result){
				if(result.success){
					$.messager.alert("系统提示","博客发布成功");
				}else{
					$.messager.alert("系统提示","博客发布失败");
				}
			},"json");
		}
	}

</script>
</head>

<body style="margin: 10px; font-family: microsoft yahei">
<div id="p" class="easyui-panel" title="修改博客" style="padding: 10px">
<table cellspacing="20px">
	<tr>
		<td width="80px">博客标题</td>
		<td><input type="text" id="title" name="title" style="width:400px;"/></td>
	</tr>
	<tr>
		<td>所属类别</td>
		<td>
			<select class="easyui-combobox" id="blogTypeId" name="blogType.id" style="width: 150px;" editable="false" panelHeight="auto">
				<option value="">请选择博客类别
				<c:forEach var="blogType" items="${blogTypeList}">
					<option value="${blogType.id}">${blogType.typeName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>博客内容</td>
		<td>
			<script type="text/plain" id="editor" style="width:100%;height:500px;"></script>
		</td>
	</tr>
	<tr>
		<td>关键字</td>
		<td><input type="text" id="keyWord" name="keyWord" style="width:400px;"/>
		&nbsp;(多个关键字中间用空格隔开)</td>
	</tr>
	<tr>
		<td></td>
		<td><a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a></td>
	</tr>
	
</table>

</div>
<script type="text/javascript">
	//实例化编辑器
	var ue = UE.getEditor("editor");
	//编辑器载入后通过ajax载入内容
	ue.addListener("ready",function(){
		UE.ajax.request("${pageContext.request.contextPath}/admin/blog/findById.do",
		{
			method:"post",
			asyc:false,
			data:{"id":"${param.id}"},
			onsuccess:function(result){
				result = eval("("+result.responseText+")");
				$("#title").val(result.title);
				$("#keyWord").val(result.keyWord);
				$("#blogTypeId").combobox("setValue",result.blogType.id);
				UE.getEditor("editor").setContent(result.content);
			}
			
		}		
		);
	});
</script>
</body>
</html>
