<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.service.impl;

import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;

import ${basepackage}.biz.entity.${className};
import ${basepackage}.biz.service.${className}Service;

<#include "/java_imports.include">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className},Integer> implements ${className}Service{
      
}