<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.service;

import java.util.List;
import java.util.Map;

import cn.com.yuzhushui.movie.common.bean.WebPager;
import ${basepackage}.biz.entity.${className};
<#include "/java_imports.include">
<#assign classNameQuery = table.className+"Query">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
public interface ${className}Service{
    
      int add${className}(${className} ${classNameLower});
      
      int add${className}Batch(List<${className}> ${classNameLower}s);
      
      int del${className}(${table.getPkColumn().javaType} id);
      
      int update${className}(${className} ${classNameLower});
      
      int update${className}Selective(${className} ${classNameLower});
      
      WebPager queryPage(Map<String, Object> map);
      
      ${className} get${className}ById(${table.getPkColumn().javaType} id);
      
      int del${className}(List<${table.getPkColumn().javaType}> ids);
}