<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tree</title>
<%-- <base href="<%=basePath%>"> --%>
<!-- easyui核心UI文件 -->
<link rel="StyleSheet" href="jquery-easyui-1.2.6/themes/default/easyui.css" type="text/css" />
<!-- easyui图标 -->
<link rel="StyleSheet" href="jquery-easyui-1.2.6/themes/icon.css" type="text/css" />
<!-- jquery核心库 -->
<script type="text/javascript" src="jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
<!-- easyui核心库 -->
<script type="text/javascript" src="jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
<!-- easyui国际化文件 -->
<script type="text/javascript" src="jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script> 
<!-- 页面自定义JS -->
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<!-- 创建树 -->
	<ul id="tt" style="display:block;float:left"></ul>
	<!-- 节点右键菜单 -->
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="appendFile()" data-options="iconCls:'icon-add'">添加文件</div>
		<div onclick="appendFolder()" data-options="iconCls:'icon-add'">添加文件夹</div>
		<div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
	</div>
	<!-- 默认树 -->
	 <ul id="tt2" style="display:block;float:left"></ul>
	<div id="mm2" class="easyui-menu" style="width:120px;">
		<div onclick="appendFile2()" data-options="iconCls:'icon-add'">添加文件</div>
		<div onclick="appendFolder2()" data-options="iconCls:'icon-add'">添加文件夹</div>
		<div onclick="removeit2()" data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
	</div> 
</body>

</html>