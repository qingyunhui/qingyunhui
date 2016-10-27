package qing.yun.hui.poitool.util.excel.bean.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import qing.yun.hui.poitool.util.excel.annotaion.ExcelColumn;
import qing.yun.hui.poitool.util.excel.annotaion.ExcelSheet;
import qing.yun.hui.poitool.util.excel.enums.ExcelColumnType;
import qing.yun.hui.poitool.util.excel.exception.ExcelAnnotationParseException;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:22:18 PM
 ** @version: V1.0
 ***/
public class ExcelAnnParse {
	
	@SuppressWarnings("rawtypes")
	private static Map<Class,ExcelAnnParse> excelAnnInfo = new LinkedHashMap<Class, ExcelAnnParse>();
	private Map<String,PropertyDescriptor> fieldInfo;
	private Map<String,ExcelColumn> fieldAnnInfo;
	private ExcelSheet sheetInfo; 
	private ArrayList<ExcelColumn> titleOrder; 
	
	private ExcelAnnParse(){}
	
	/**
	 * 对类注解的解析
	 * 
	 * @param clazz
	 * @return
	 * @throws ExcelAnnotationParseException
	 * @throws IntrospectionException
	 */
	@SuppressWarnings("rawtypes")
	public synchronized static <T> ExcelAnnParse parse(Class<T> clazz) throws ExcelAnnotationParseException, IntrospectionException{
		ExcelAnnParse eap = excelAnnInfo.get(clazz);
		if(eap != null){
			if(excelAnnInfo.size()>1000){//超过一定大小 就删除一些对象
				Iterator<Class> it =excelAnnInfo.keySet().iterator();
				for(int i = 0 ; i< 500 ; i++){
					excelAnnInfo.remove(it.next());
				}
			}
			excelAnnInfo.remove(clazz);//如果找到就放到链表尾部
		}else{
			eap = new ExcelAnnParse();
			eap.init(clazz);
		}
		excelAnnInfo.put(clazz, eap);//放置在最后
		return eap;
	}
	private  <T> void init(Class<T> clazz) throws ExcelAnnotationParseException, IntrospectionException{
		this.sheetInfo = clazz.getAnnotation(ExcelSheet.class);
		if(sheetInfo == null){
			throw new ExcelAnnotationParseException(clazz.getName()+"没有声明@ExcelSheet注解，不能进行解析");
		}
		fieldInfo = new HashMap<String, PropertyDescriptor>();
		fieldAnnInfo = new HashMap<String, ExcelColumn>();
		titleOrder = new ArrayList<ExcelColumn>();//有序的， 导出时候字段的顺序
		Field[] fields = clazz.getDeclaredFields();//所有私有属性
		for (Field field : fields) {
			ExcelColumn aec =field.getAnnotation(ExcelColumn.class);//获取注解信息
			if(aec == null){//忽略掉没有注解的字段
				continue;
			}
			fieldInfo.put(aec.title(), new PropertyDescriptor(field.getName(), clazz));//title对应的字段相关信息，用于获取get OR set 方法
			fieldAnnInfo.put(aec.title(), aec);
			if(aec.type() != ExcelColumnType.IMP){
				titleOrder.add(aec);
			}
		}
		
	/*	titleOrder.sort(new Comparator<ExcelColumn>() {//进行字段排序 jdk8支持的写法
			public int compare(ExcelColumn o1, ExcelColumn o2) {
				return o1.order()>o2.order()?-1:0;
			};
		});*/
		
	}
	public Map<String, PropertyDescriptor> getFieldInfo() {
		return fieldInfo;
	}
	
	public Map<String, ExcelColumn> getFieldAnnInfo() {
		return fieldAnnInfo;
	}
	
	public ArrayList<ExcelColumn> getTitleOrder() {
		return titleOrder;
	}

	public ExcelSheet getSheetInfo() {
		return sheetInfo;
	}
	
}
