<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<title>详情</title>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
    <div class="content clearfix">
    	<jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include>
	    <div class="main">
		    <!-- 面包屑 开始@@@@@@@@@@@@@@@@@@ -->
			<div class="breadcrumb">
				<div class="breadcrumb-hidden">
					<span class="breadcrumb-icon"></span>
					<ol id="breadCrumb">
						<li>
							<span class="breadcrumb-line">.</span>
							<a href="list.htm">双击这里修改</a>
						</li>
						<li>
							<span class="breadcrumb-line">.</span>
							<a href="#">详情</a>
						</li>
					</ol>
				</div>
			</div>
			<!-- 面包屑  结束&&&&&&&&&&&& -->
			
			<!-- 表单 开始@@@@@@@@@@@@@@@@@@ -->
			<table class="detail-table">
				<tbody>
					<tr>
					<#list table.columns as column>
						<th>${column.columnAlias}：</th>
						<#if column.columnNameFirstLower == "deleted">
						<td>未删除</td>
						<#else>
						<td>${r'${'}${table.classNameFirstLower}Form.${column.columnNameFirstLower}}</td>
						</#if>
						<#if column_index%2 == 1>
					</tr>
					<tr>
						</#if>
					</#list>
					<#if table.columns?size %2 == 1>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</#if>
					</tr>
				</tbody>
			</table>
			<!-- 表单  结束&&&&&&&&&&&& -->
			<div class="btn-row">
				<a href="list.htm" class="return-btn">返回</a>
			</div>
	    </div>
    </div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
</html>