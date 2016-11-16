<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.biz.dao;

import java.util.List;
import java.util.Map;
import ${basepackage}.biz.entity.${className};

<#include "/java_imports.include">
<#assign classNameQuery = table.className+"Query">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
public interface ${className}Mapping {
	/** 
     * 新增${className}
     * @param ${classNameLower} ${className}
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @return int
     * @history:
     */
    public int insert(${className} ${classNameLower});
    
    /** 
     * 批量新增${className}
     * @param ${classNameLower}s ${className}s
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @return int
     * @history:
     */
    public int insertBatch(List<${className}> ${classNameLower}s);
    
    /** 
     * 根据id删除${className}
     * @param id 主键
     * @return int
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
    public int delete(${table.getPkColumn().javaType} id);
    
    /** 
     * 根据id批量删除${className}
     * @param id 主键
     * @return int
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
    public int deleteBatch(List<${table.getPkColumn().javaType}> ids);
    
	/** 
     * 更新${className}中所有字段
     * @param ${classNameLower} {className}实体，条件属性必须设置
     * @return int
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
    public int update(${className} ${classNameLower});
    
    /** 
     * 更新${className}中不为null的字段，必须设置主键，否则不生效
     * @param ${classNameLower} {className}实体，条件属性必须设置
     * @return int
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history: 
     */
    public int updateSelective(${className} ${classNameLower});
    
    /** 
     * 根据id获取${className}
     * @param id 主键
     * @return ${className}
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
    public ${className} getById(${table.getPkColumn().javaType} id);
    
    /** 
     * 根据条件查询全部${className}列表
     * @param map
     * @return List<${className}>
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
	public List<${className}> queryAll(Map<String, Object> map);
	
	/** 
     * 根据条件分页查询数据
     * @param map Map<String, Object>
     * @return List<${className}>
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
	public List<${className}> queryPage(Map<String, Object> map);
	
	/** 
     * 统计符合条件的数据的数量
     * @param map Map<String, Object>
     * @return int
     * @create: <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
     * @history:
     */
	public int queryCount(Map<String, Object> map);
	
}
