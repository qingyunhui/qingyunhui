<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.biz.dao;

import cn.com.yuzhushui.movie.common.base.BaseDao;
import ${basepackage}.biz.entity.${className};

<#include "/java_imports.include">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
public interface ${className}Dao extends BaseDao<${className},Integer>{
	
	
}
