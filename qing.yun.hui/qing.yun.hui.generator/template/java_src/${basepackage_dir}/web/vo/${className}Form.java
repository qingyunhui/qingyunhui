package ${basepackage}.web.vo;

import java.util.Date;

<#include "/java_imports.include">
<#assign classNameQuery = table.className+"Form">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
public class ${className}Form {

	//columns START
	<#list table.columns as column>
	<#if  column.columnNameFirstLower != 'deleted'>
	
	/**
	 * @Fields ${column.sqlName}:${column.columnAlias}
	 */
	private ${column.simpleJavaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
	//columns END

<@generateConstructor classNameQuery/>
<@generateJavaColumnsWithoutDeleted/>

}