package qing.yun.hui.poitool.util.excel;

import qing.yun.hui.poitool.util.excel.bean.IExcelExport;
import qing.yun.hui.poitool.util.excel.bean.IExcelImport;
import qing.yun.hui.poitool.util.excel.bean.impl.SimpleExcelExport;
import qing.yun.hui.poitool.util.excel.bean.impl.SimpleExcelImport;

/***
 ** @Description: 导出工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:45:06 PM
 ** @version: V1.0
 ***/
public class ExcelUtil {
	
	public final static int EXP_MODE_ANN = 1;// 注解
	public final static int EXP_MODE_OBJ = 2;// 对象 
	public final static int EXP_MODE_MAP = 3;// 集合 

	public static IExcelImport getImport() {
		return new SimpleExcelImport();
	}

	public static IExcelExport getExport() {
		return getExport(EXP_MODE_ANN);
	}

	public static IExcelExport getExport(int mode) {
		IExcelExport export = null;
		switch (mode) {
		case ExcelUtil.EXP_MODE_ANN:
			export = new SimpleExcelExport();
			break;
		case EXP_MODE_OBJ:
			break;
		case EXP_MODE_MAP:
			break;
		default:
			break;
		}

		return export;
	}

}
