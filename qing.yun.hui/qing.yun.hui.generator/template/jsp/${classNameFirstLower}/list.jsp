<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<title>${table.remarks}列表</title>
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
							<a href="list.htm"></a>
						</li>
						<li>
							<span class="breadcrumb-line">.</span>
							<a href="#">列表</a>
						</li>
					</ol>
				</div>
			</div>
			<!-- 面包屑  结束&&&&&&&&&&&& -->
			
			
			<!-- 查询表单  开始@@@@@@@@@@@@@@@ -->
			<div class="search-outer">
				<sf:form name="searchForm" action="doList.htm" method="post" commandName="${classNameLower}Query">
					<input type="hidden" name="pageNo" value="1"/>
					<input type="hidden" name="pageSize" value="${r'${pager.pageSize}'}"/>
					<ul class="clearfix">
						<#list table.columns as column>
						<#if  column.columnNameFirstLower != 'deleted'>
						<li>
							<label class="query-label">${column.columnAlias}：</label>
							<sf:input path="${column.columnNameFirstLower}"/>
						</li>
						</#if>
						</#list>
						<li>
							<input type="submit" class="black-btn" value="查询">
						</li>
					</ul>
				</sf:form>
			</div>
			<!-- 查询表单  结束&&&&&&&&&&&& -->
			
			<!-- 删除form_开始  用于删除时用  @@@@@@@@@@@@@@@ -->
			<form id="delete-form" action="doDelete.htm" method="POST">
				<input type="hidden" name="ids" id="ids" />
			</form>
			<!-- 删除form 结束 &&&&&&&&&&&& -->
			
			<!-- 表单_开始  @@@@@@@@@@@@@@@-->
			<div class=" clearfix">
				<!-- 工具栏_开始 -->
				<div class="tables-box">
					<div class="tables-count">
						<span class="count-icon-outer">
							<span class="count-icon"></span>${r'${pager.totalCount}'}条
						</span>
						<div class="btns-outer">
							<a href="javascript:void(0);" class="detail-btn">详情</a>
							<a href="${r'${basePath}'}${moduleName}/${classNameLower}/add.htm" class="add-btn">新增</a>
							<a href="javascript:void(0);" class="edit-btn">修改</a>
							<a href="javascript:void(0);" class="delete-btn">删除</a>
						</div>
					</div>
				</div>
				<!-- 工具栏_结束-->
				<!-- 表单数据 开始 -->
				<div class="tables-outer table-scroll">
					<table class="table-sim list">
						<thead>
							<tr>
								<th width="20">
									<input type="checkbox" id="select-all" class="select-all" />
								</th>
								<th>序号</th>
								<#list table.columns as column>
								<th>${column.columnAlias}</th>
								</#list>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${r'${pager.list}'}" varStatus="i">
							<tr class="${r'${(i.index %2==0)?"tr-bg":""}'}">
								<td>
									<input name="id" type="checkbox" class="checkbox" value="${r'${list.'}${table.getPkColumn().columnNameFirstLower}}">
								</td>
								<td>${r'${i.index+1}'}</td>
								<#list table.columns as column>
								<td>${r'${list.'}${column.columnNameFirstLower}}</td>
								</#list>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 表单数据 结束 -->
				<!-- 分页_begin -->
				<jsp:include page="../../common/page_bar.jsp"></jsp:include>
				<!-- 分页_end -->
			</div>
			<!-- 表单 结束 &&&&&&&&&&&& -->
	    </div>
    </div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){
		//tr单击事件
		$(".table-sim>tbody>tr").click(function(){
			//切换被单击的样式，同步复选框状态
			$(this).toggleClass('tr-active').find(":checkbox[name=id]").prop("checked",$(this).is('.tr-active'));
			
			// 如果一但有没有选中的复选框，那么取消勾选“全部复选框” 
			$(".select-all").prop("checked",$(".table-sim>tbody>tr").size()==$(".table-sim>tbody>tr.tr-active").size());
		});
		
		//全选按钮事件
		$(".select-all").change(function(){
			if($(this).prop("checked")){
				//如果是“全选”的话 选中没有选中的复选框
				$(".table-sim>tbody>tr:not(.tr-active)").click();
			}else{
				//如果是“取消全选”的话取消选中的复选框
				$(".table-sim>tbody>tr.tr-active").click();
			}
		});
		
		
		//数据删除
		$(".delete-btn").click(function(){
			var ids=getTableCheckData();
			//如果有数据就删除，没有提示
			if(ids.length){
				$.confirm('您确认要删除该数据吗?',function(){
					$("#ids").val(ids.join(",")).parent().submit();
				});
			}else{
				$.alert('请选择一条记录');
			}
		});
		//编辑数据
		$(".edit-btn").click(function(){
			var ids=getTableCheckData();
			if(ids.length==1){
				$(this).attr("href","edit.htm?${table.getPkColumn().columnNameFirstLower}="+ids[0])
			}else{
				$.alert('请选择一条记录，并且只能选择一条！');
			}
		});
		
		//查看详情
		$(".detail-btn").click(function(){
			var ids=getTableCheckData();
			if(ids.length==1){
				$(this).attr("href","detail.htm?${table.getPkColumn().columnNameFirstLower}="+ids[0])
			}else{
				$.alert('请选择一条记录，并且只能选择一条！');
			}
		});
		
		//初始化一些东西
		init();
	});
	//初始化页面
	function init(){
		showFeedBackMsg();
	}
	
	//展示有回馈的信息
	function showFeedBackMsg(){
		var feedBackMsg="${r'${feedBackMsg}'}";
		if(feedBackMsg){
			$.message(feedBackMsg);
		}
	}
	
	function getTableCheckData(){
		var ids=[];
		//获取选中的数据
		$(".table-sim").find("[name=id]:checked").each(function(i,o){
			ids[i]=$(o).val();
		});
		return ids;
	}
</script>
</html>