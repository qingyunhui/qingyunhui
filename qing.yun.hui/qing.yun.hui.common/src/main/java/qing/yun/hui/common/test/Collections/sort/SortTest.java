package qing.yun.hui.common.test.Collections.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import qing.yun.hui.common.test.Collections.sort.po.User;

/***
 ** @Description: Collections.sort(..) 类实现排序功能...
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Jan 27, 2016 9:52:53 AM
 ** @version: V1.0
 ***/
public class SortTest {
	
	private static List<User> userList;

	
	public static void main(String[] args){
		
		System.out.println("----------before排序之前-----------");
		printList(userList);
		Collections.sort(userList, new Comparator<User>() {
			public int compare(User o1, User o2) {
				//return o1.getUserId()>o2.getUserId()?1:o1.getUserId()<o2.getUserId()?-1:0;	//按userId排序
				//return o1.getBirthday().compareTo(o2.getBirthday());//按Date 进行排序
				return o1.getMoney()>o2.getMoney()?1:o1.getMoney()<o2.getMoney()?-1:0;//double 排序..
			}
		});
		Collections.reverse(userList);//反转（可以实现升序或降序..）//TODO
		
		//Collections.sort(userList,Collator.getInstance(java.util.Locale.CHINA));//注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
		
		System.out.println("----------after排序之后-----------");
		printList(userList);
	}
	
	public static void printList(List<User> userList){
		for(User user:userList){
			System.out.println(user);
		}
	}
	
	static {
		if(null==userList){
			userList=new ArrayList<User>();
			try {
				init();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void init() throws ParseException{
		User userZ=new User(3,"zhang.shang",5000.50,new Date());
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateL=format.parse("2015-12-13 22:12:13");
		User userL=new User(2,"li.si",6000.50,dateL);
		Date dateW=format.parse("2015-11-25 13:44:21");
		User userW=new User(6,"li.si",4500.00,dateW);
		userList.add(userZ);
		userList.add(userL);
		userList.add(userW);
	}
	
	public List<User> getUserList() {
		return userList;
	}

}
