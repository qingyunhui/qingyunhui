package qing.yun.hui.common.test.Collections.sort.po;

import java.text.SimpleDateFormat;
import java.util.Date;


/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Jan 27, 2016 9:53:07 AM
 ** @version: V1.0
 ***/
public class User extends Base{
	
	public User(){
		super();
	}
	
	public User(int userId,String userName,double money,Date birthday){
		this.setUserId(userId);
		this.setUserName(userName);
		this.setMoney(money);
		this.setBirthday(birthday);
	}
	
	public User(int userId,String userName,double money,Date birthday,String grade){
		this.setUserId(userId);
		this.setUserName(userName);
		this.setMoney(money);
		this.setBirthday(birthday);
		this.grade=grade;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("userId:").append(this.getUserId()).append("  ");
		sb.append("userName:").append(this.getUserName()).append("  ");
		sb.append("money:").append(this.getMoney()).append("  ");
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("birthday:").append(format.format(this.getBirthday())).append("  ");
		sb.append("grade:").append(this.getGrade());
		return sb.toString();
	}
	
	private String grade;	//年级

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
