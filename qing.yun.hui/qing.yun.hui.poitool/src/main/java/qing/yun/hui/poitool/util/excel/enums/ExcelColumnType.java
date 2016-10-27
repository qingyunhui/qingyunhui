package qing.yun.hui.poitool.util.excel.enums;
/***
 ** @Description: Excel 字段的类型，用于控制字段在导入导出时候是否进行性处理
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:27:45 PM
 ** @version: V1.0
 ***/
public enum ExcelColumnType {

	ALL(0,"导入和导出"),IMP(1,"仅导入"),EXP(2,"仅导出");
	
	public String name;
	public int value;
	
	private ExcelColumnType(int value,String name){
		this.value=value;
		this.name=name;
	}
	
}
