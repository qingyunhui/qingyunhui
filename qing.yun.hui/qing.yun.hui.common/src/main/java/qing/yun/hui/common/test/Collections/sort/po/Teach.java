package qing.yun.hui.common.test.Collections.sort.po;

import java.util.Date;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Jan 27, 2016 9:53:13 AM
 ** @version: V1.0
 ***/
public class Teach extends Base{
	
	
	public Teach(){
		super();
	}
	
	public Teach(int userId,String userName,double money,Date birthday){
		this.setUserId(userId);
		this.setUserName(userName);
		this.setMoney(money);
		this.setBirthday(birthday);
	}
	
	public Teach(int userId,String userName,double money,Date birthday,String job){
		this.setUserId(userId);
		this.setUserName(userName);
		this.setMoney(money);
		this.setBirthday(birthday);
		this.job=job;
	}
	
	
	private String job;//职位

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	

}
