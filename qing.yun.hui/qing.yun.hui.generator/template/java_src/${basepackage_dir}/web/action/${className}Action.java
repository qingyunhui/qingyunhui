package ${basepackage}.web.action;

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basepackage}.biz.entity.${className};
import ${basepackage}.web.vo.${className}Form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.wesocket.common.base.BaseAction;

<#include "/java_imports.include">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
@Controller
@RequestMapping(${className}Action.ACTION_PATH)
public class ${className}Action extends BaseAction<${className}, ${className}Form, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(${className}Action.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/${table.sqlName?split("_")[0]}/${classNameLower}";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}