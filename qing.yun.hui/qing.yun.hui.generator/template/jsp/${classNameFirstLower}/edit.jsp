<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<title>编辑</title>
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
							<a href="#">编辑</a>
						</li>
					</ol>
				</div>
			</div>
			<!-- 面包屑  结束&&&&&&&&&&&& -->
			
			<!-- 主表单 开始@@@@@@@@@@@@@@@@@@ -->
			<div class="create-wrapper">
	            <sf:form name="addForm" action="doEdit.htm" method="post" commandName="${classNameLower}Form" cssClass="create-form">
	            	<ul class="clearfix">
	            		<#list table.columns as column>
						<#if  column.columnNameFirstLower != 'deleted'>
						<li class="width-percent50">
		                    <label class="bold-label" for="${column.columnNameFirstLower}">${column.columnAlias}：</label>
		                    <sf:input path="${column.columnNameFirstLower}"/>
		                    <sf:errors path="${column.columnNameFirstLower}"/>
		                </li>
						</#if>
						</#list>
	            	</ul>
	                <p class="break-line forms-submit-outer">
	                    <input class="sure-btn submit-layout small-hand" type="submit" value="确定"/>
                       	<a id="back" href="list.htm" class="return-btn">返回</a>
                    	<a id="reset" href="javascript:reset();"  class="reset-btn">重置</a>
	                </p>
	            </sf:form>
            </div>
            <!-- 主表单 结束&&&&&&&&&&&& -->
	    </div>
    </div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){
		//页面加载完毕了，做点什么呢？	
	})
	
	/**
	* 表单重置按钮事件，因为要初始化执行时间状态，所以抽出
	*/
	function reset(){
		document.addForm.reset();
	}
</script>
</html>