<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web.action;

import ${basepackage}.biz.po.${className};
import ${basepackage}.biz.service.${className}Service;
import ${basepackage}.web.vo.${className}Query;
import ${basepackage}.web.vo.${className}Form;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.yuzhushui.smiles8.common.bean.Constant;
import cn.com.yuzhushui.smiles8.common.bean.WebPager;
import qing.yun.hui.common.utils.BeanUtil;
import cn.com.yuzhushui.smiles8.common.bean.vo.MySessionInfo;

<#include "/java_imports.include">
<#assign classNameQuery = table.className+"Query">
<#include "/macro.include"/>
<#include "/java_copyright.include"><#assign className = table.className><#assign classNameLower = className?uncap_first> 
@Controller
@RequestMapping(${className}Action.ACTION_PATH)
@SessionAttributes({Constant.MY_SESSION_INFO})
public class ${className}Action {
	//用于指定jsp页面的相对位置
	//protected static final String MODEL_PATH="/${table.sqlName?split("_")[0]}/${classNameLower}";
	//一般用于重定向用
	protected static final String ACTION_PATH="/${table.sqlName?split("_")[0]}/${classNameLower}";
	
	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping(value="list", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView list(${className}Query query){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/list");
		
		Map<String, Object> map = BeanUtil.pojoToMap(query);
		
		WebPager pager = ${classNameLower}Service.queryPage(map);
		
		modelAndView.addObject(WebPager.PAGE_NAME, pager);
		modelAndView.addObject(Constant.QUERY, query);
		return modelAndView;
	}
	
	/**
	 * 查询,解决查询刷新问题
	 * @return
	 */
	@RequestMapping(value="doList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doList(${className}Query query,RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
		redirectAttributes.addFlashAttribute(query);
		return modelAndView;
	}
	
	/**
	 * 提交删除
	 * @return
	 */
	@RequestMapping("doDelete")
	public ModelAndView doDelete(${table.getPkColumn().javaType}[] ids,RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
		${classNameLower}Service.del${className}(Arrays.asList(ids));
		redirectAttributes.addFlashAttribute("feedBackMsg","删除成功！");
		return modelAndView;
	}
	
	/**
	 * 详情
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(${table.getPkColumn().javaType} ${table.getPkColumn().columnNameFirstLower}){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/detail");
		
		${className} ${classNameLower} = ${classNameLower}Service.get${className}ById(${table.getPkColumn().columnNameFirstLower});
		${className}Form ${classNameLower}Form = new ${className}Form();
		
		BeanUtils.copyProperties( ${classNameLower},${classNameLower}Form);
		modelAndView.addObject("${classNameLower}Form", ${classNameLower}Form);
		return modelAndView;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/add");
		modelAndView.getModel().put("${classNameLower}Form", new ${className}Form());
		return modelAndView;
	}
	
	/**
	 * 提交新增
	 * @return
	 */
	@RequestMapping(value="doAdd", method = {RequestMethod.POST})
	public ModelAndView doAdd(@Valid ${className}Form ${classNameLower}Form, BindingResult result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
			RedirectAttributes redirectAttributes){
		${className} ${classNameLower} = new ${className}();
		BeanUtils.copyProperties(${classNameLower}Form, ${classNameLower});
		${classNameLower}Service.add${className}(${classNameLower});
		redirectAttributes.addFlashAttribute("feedBackMsg","保存成功！");
		return new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping("edit")
	public ModelAndView edit(${table.getPkColumn().javaType} ${table.getPkColumn().columnNameFirstLower}){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/edit");
		${className} ${classNameLower} = ${classNameLower}Service.get${className}ById(${table.getPkColumn().columnNameFirstLower});
		${className}Form ${classNameLower}Form = new ${className}Form();
		
		BeanUtils.copyProperties( ${classNameLower},${classNameLower}Form);
		modelAndView.addObject("${classNameLower}Form", ${classNameLower}Form);
		return modelAndView;
	}
	/**
	 * 编辑保存
	 * @return
	 */
	@RequestMapping(value="doEdit", method = {RequestMethod.POST})
	public ModelAndView doEdit(@Valid ${className}Form ${classNameLower}Form, BindingResult result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
			RedirectAttributes redirectAttributes){
		${className} ${classNameLower} = new ${className}();
		BeanUtils.copyProperties(${classNameLower}Form, ${classNameLower});
		${classNameLower}Service.update${className}Selective(${classNameLower});
		redirectAttributes.addFlashAttribute("feedBackMsg","修改成功！");
		return new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
	}
}