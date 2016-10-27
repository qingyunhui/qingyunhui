package qing.yun.hui.poitool.vo;

import java.util.Date;

/**
 * @author qing.yunhui 
 * @Since 2010-2015
 * @create 2015-10-29 22:17:48
 * @history
 */
public class SysUser {
	
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields accountId:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields name:姓名
	 */
	private String name;
	
	/**
	 * @Fields sex:性别：0，女；1，男
	 */
	private Integer sex;
	
	/**
	 * @Fields age:年龄
	 */
	private Integer age;
	
	/**
	 * @Fields telephone:联系电话
	 */
	private String telephone;
	
	/**
	 * @Fields mobilephone:移动电话
	 */
	private String mobilephone;
	
	/**
	 * @Fields job:职务
	 */
	private String job;
	
	/**
	 * @Fields qq:qq
	 */
	private String qq;
	
	/**
	 * @Fields weixin:微信
	 */
	private String weixin;
	
	/**
	 * @Fields weibo:微博
	 */
	private String weibo;
	
	/**
	 * @Fields email:电子邮箱
	 */
	private String email;
	
	/**
	 * @Fields officeAddr:办公地址
	 */
	private String officeAddr;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields createrId:createrId
	 */
	private Integer createrId;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editorId:修改人
	 */
	private Integer editorId;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields comment:备注
	 */
	private String comment;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	public SysUser(){
	}

	public SysUser(Integer id){
		this.id = id;
	}

	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}
	
	public Integer getAccountId(){
		return accountId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
	public Integer getSex(){
		return sex;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
	
	public String getMobilephone(){
		return mobilephone;
	}
	
	public void setJob(String job){
		this.job = job;
	}
	
	public String getJob(){
		return job;
	}
	
	public void setQq(String qq){
		this.qq = qq;
	}
	
	public String getQq(){
		return qq;
	}
	
	public void setWeixin(String weixin){
		this.weixin = weixin;
	}
	
	public String getWeixin(){
		return weixin;
	}
	
	public void setWeibo(String weibo){
		this.weibo = weibo;
	}
	
	public String getWeibo(){
		return weibo;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setOfficeAddr(String officeAddr){
		this.officeAddr = officeAddr;
	}
	
	public String getOfficeAddr(){
		return officeAddr;
	}
	
	public void setCtime(Date ctime){
		this.ctime = ctime;
	}
	
	public Date getCtime(){
		return ctime;
	}
	
	public void setCreaterId(Integer createrId){
		this.createrId = createrId;
	}
	
	public Integer getCreaterId(){
		return createrId;
	}
	
	public void setCreater(String creater){
		this.creater = creater;
	}
	
	public String getCreater(){
		return creater;
	}
	
	public void setEtime(Date etime){
		this.etime = etime;
	}
	
	public Date getEtime(){
		return etime;
	}
	
	public void setEditorId(Integer editorId){
		this.editorId = editorId;
	}
	
	public Integer getEditorId(){
		return editorId;
	}
	
	public void setEditor(String editor){
		this.editor = editor;
	}
	
	public String getEditor(){
		return editor;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}

}