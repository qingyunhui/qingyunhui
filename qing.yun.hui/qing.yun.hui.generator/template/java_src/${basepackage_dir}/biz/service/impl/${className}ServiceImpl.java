<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.smiles8.common.bean.WebPager;
import ${basepackage}.biz.dao.${className}Mapping;

import java.util.List;
import java.util.Map;

import ${basepackage}.biz.po.${className};
import ${basepackage}.biz.service.${className}Service;

<#include "/java_imports.include">
<#assign classNameQuery = table.className+"Query">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service{
      
      @Autowired
      private ${className}Mapping ${classNameLower}Mapping;
    
      public int add${className}(${className} ${classNameLower}){
          return ${classNameLower}Mapping.insert(${classNameLower});
      }
      
      public int add${className}Batch(List<${className}> ${classNameLower}s) {
    	  return ${classNameLower}Mapping.insertBatch(${classNameLower}s);
      }
      
      public int del${className}(${table.getPkColumn().javaType} id){
    	  return ${classNameLower}Mapping.delete(id);
      }
      
      public int update${className}(${className} ${classNameLower}){
    	  return ${classNameLower}Mapping.update(${classNameLower});
      }
      
      public int update${className}Selective(${className} ${classNameLower}){
    	  return ${classNameLower}Mapping.updateSelective(${classNameLower});
      }
     
      public WebPager queryPage(Map<String, Object> map) {
          WebPager webPager = new WebPager();
          webPager.init(map);
		
          webPager.setList(${classNameLower}Mapping.queryPage(map));
          webPager.paging(webPager.getPageNo(), webPager.getPageSize(), ${classNameLower}Mapping.queryCount(map));
		
          return webPager;
      }
      
      public ${className} get${className}ById(${table.getPkColumn().javaType} id){
          return ${classNameLower}Mapping.getById(id);
      }
      
      public int del${className}(List<${table.getPkColumn().javaType}> ids){
		return ${classNameLower}Mapping.deleteBatch(ids);
      }
}