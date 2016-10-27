package qing.yun.hui.poitool.util.excel.bean;

import java.io.InputStream;
import java.util.List;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:21:03 PM
 ** @version: V1.0
 ***/
public interface IExcelImport {

	<T> List<T> execute(InputStream is, Class<T> clazz) throws Exception ;
	
}
