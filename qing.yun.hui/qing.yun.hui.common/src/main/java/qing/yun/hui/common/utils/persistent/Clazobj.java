package qing.yun.hui.common.utils.persistent;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年8月1日下午11:08:04
 **/
public class Clazobj<T> {
	
	/**键、唯一标识*/
	private String key;
	
	/**名称、名称描述*/
	private String name;
	
	private Class<?> clzs;
	
	private Anno anno;
	
	public Clazobj(){}
	
	public Clazobj(String name,Class<?> clzs,Anno anno){
		this.name=name;
		this.clzs=clzs;
		this.anno=anno;
	}
	
	public Clazobj(String key,String name,Class<?> clzs,Anno anno){
		this.key=key;
		this.name=name;
		this.clzs=clzs;
		this.anno=anno;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getClzs() {
		return clzs;
	}

	public void setClzs(Class<?> clzs) {
		this.clzs = clzs;
	}

	public Anno getAnno() {
		return anno;
	}

	public void setAnno(Anno anno) {
		this.anno = anno;
	}
	
}
