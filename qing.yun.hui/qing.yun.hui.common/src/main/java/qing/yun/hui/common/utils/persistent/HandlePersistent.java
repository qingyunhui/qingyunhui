package qing.yun.hui.common.utils.persistent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年8月1日下午11:22:53
 **/
public class HandlePersistent {
	
	public static void main(String[] args){
		/*SysUsers object=new SysUsers();
		List<String> list=initList(4);
		List<SysUsers> users=getDatas(object, list);
		for(SysUsers user:users){
			System.out.println(user.getUserId()+"\tname:"+user.getUserName()+"\ttelephone:"+user.getTelephone()+"\tage:"+user.getAge()+"\tsex:"+user.getSex());
		}*/
	}
	
	/**
	 * 通过java反射，获取指定对象且对list数据集进行处理
	 * @param object
	 * @param list
	 * @return List<T>
	 * **/
	public static <T> List<T> getDatas(Object object,List<String> list){
		Map<String,Clazobj<T>> map=getMap(object);
		Map<String,Clazobj<T>> sortMap=sortMap(map);
		List<T> users=getDatas(object, sortMap, list);
		return users;
	}
	
	
	@SuppressWarnings("unchecked")
	protected static <T> List<T> getDatas(Object object,Map<String,Clazobj<T>> map,List<String> list){
		List<T> datas=new LinkedList<T>();
		if(null==list||list.size()<1) return datas;
		List<Clazobj<T>> objs=mapToList(map);
		/**@1.对list中每一条数据进行spillt(",")分割..
		 * @2.对obj进行反射获取其class，将已经排序的字段clzobj进行set赋值操作..
		 * @3.针对@1，@2操作后，把数据放入list中..
		 * @4.搞定收工..
		 * **/
		for(String str:list){
			if(str.indexOf(",")!=-1){
				String[] strs=str.split(",");
				Object tmpobj=null;
				try {
					tmpobj=object.getClass().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				for(int i=0;i<strs.length;i++){
					if(i<=objs.size()-1){
						//防止下标越界...
						String value=strs[i];
//						System.out.println("value:"+value);
//						Anno anno=objs.get(i).getAnno();
//						System.err.println("anno:"+anno);
						String name=objs.get(i).getName();
//						System.out.println("name:"+name);
						name=StringUtil.firstLetterConvert(name, true);
//						System.err.println("name:"+name);
						String type=objs.get(i).getClzs().getSimpleName();
//						System.out.println("type:"+type);
						try {
							Method method=tmpobj.getClass().getMethod("set"+name, objs.get(i).getClzs());
							if("Integer".equals(type)){
								method.invoke(tmpobj, Integer.parseInt(value));
							}else if("String".equals(type)){
								method.invoke(tmpobj, String.valueOf(value));
							}else{
								method.invoke(tmpobj,value);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				datas.add((T) tmpobj);
			}
		}
		return datas;
	}
	
	/**
	 * map转list
	 * @param map 待转换的map
	 * @return List<Clazobj<T>>
	 * **/
	protected static <T> List<Clazobj<T>> mapToList(Map<String,Clazobj<T>> map) {
		List<Clazobj<T>> datas=new LinkedList<Clazobj<T>>();
		if(null==map||map.isEmpty()) return datas;
		Iterator<Map.Entry<String,Clazobj<T>>> iterator=map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Clazobj<T>> entry=iterator.next();
			String key=entry.getKey();
			Clazobj<T> clazobj=entry.getValue();
			if(null==key||null==clazobj){
				continue;
			}
			datas.add(clazobj);
		}
		return datas;
	}
	
	/**
	 * <P>对map进行排序</p>
	 * @param map 待排序的map
	 * @return Map<String,Clazobj<T>>
	 * **/
	protected static <T> Map<String,Clazobj<T>> sortMap(Map<String,Clazobj<T>> map) {
		Set<Entry<String, Clazobj<T>>> entryset=map.entrySet();
		ArrayList<Map.Entry<String, Clazobj<T>>> list=new ArrayList<Map.Entry<String, Clazobj<T>>>(entryset);
		Collections.sort(list, new Comparator<Map.Entry<String, Clazobj<T>>>() {
			public int compare(Entry<String, Clazobj<T>> o1, Entry<String, Clazobj<T>> o2) {
				return o1.getValue().getAnno().order()-o2.getValue().getAnno().order();
			}
		});
		Map<String,Clazobj<T>> sortmap=new LinkedHashMap<String, Clazobj<T>>();
	    for (int i = 0; i < list.size(); i++) {  
	       	sortmap.put(list.get(i).getKey(), list.get(i).getValue());  
	    }  
		return sortmap;
	}
	
	/**
	 * 如果object对象中的属性含有指定Anno、则放入map中
	 * @param object
	 * **/
	protected static <T> Map<String,Clazobj<T>> getMap(Object object) {
		Map<String,Clazobj<T>> map=new HashMap<String,Clazobj<T>>();
		if(null==object) return map;
		Field[] fields=object.getClass().getDeclaredFields();
		if(null==fields||fields.length<1) return map;
		for(Field field:fields){
			Anno anno=field.getAnnotation(Anno.class);
			if(null!=anno) {
				Clazobj<T> clazobj=new Clazobj<T>(field.getName(),field.getType(),anno);
				map.put(field.getName(), clazobj);
			}
		}
		return map;
	}
	protected static List<String> initList(int count){
		//a.userId-integer b.userName-string c.telephone-string  d.age  e.sex
		List<String> list=new ArrayList<String>();
		for(int i=0;i<count;i++){
			StringBuffer sb=new StringBuffer();
			Integer userId=100+i;
			String userName="张三"+i;
			String telephone="1866530064"+i;
			String age="19"+i;
			Integer sex=i%2==0?1:0;
			sb.append(userId).append(",").append(userName).append(",").append(telephone).append(",").append(age).append(",").append(sex);
			list.add(sb.toString());
		}
		return list;
	}
	

	protected static <T> void printMap(Map<String,Clazobj<T>> map){  
        System.out.println("===================mapStart==================");  
        Iterator<Entry<String, Clazobj<T>>> it = map.entrySet().iterator();  
        while(it.hasNext()){  
        	Entry<String, Clazobj<T>> entry=it.next();  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("===================mapEnd==================");  
    }   
}
 