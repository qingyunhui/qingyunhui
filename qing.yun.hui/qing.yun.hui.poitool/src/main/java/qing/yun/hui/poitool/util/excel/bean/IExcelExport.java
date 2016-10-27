package qing.yun.hui.poitool.util.excel.bean;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import qing.yun.hui.poitool.util.excel.bean.impl.ExcelStyle;

/***
 ** @Description: 导出接口规范
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:20:12 PM
 ** @version: V1.0
 ***/
public interface IExcelExport {

	<T> void execute(OutputStream os, List<T> data ,Class<T> clazz) throws Exception;
	/**
	 * 为了保证顺序，可以使用 LinkedHashMap 
	 * @param titleAndField
	 */
	void setCustomTitleRow(Map<String,String> titleAndField);
	ExcelStyle getTitleStyel();
	void setTitleStyel(ExcelStyle titleStyel) ;

	ExcelStyle getDataStyel() ;
	void setDataStyel(ExcelStyle dataStyel) ;
	
}
