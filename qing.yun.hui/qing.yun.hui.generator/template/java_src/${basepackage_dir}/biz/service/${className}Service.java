<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.service;

import cn.com.yuzhushui.wesocket.common.base.BaseService;

import ${basepackage}.biz.entity.${className};
<#include "/java_imports.include">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
public interface ${className}Service extends BaseService<${className},Integer>{
    
}