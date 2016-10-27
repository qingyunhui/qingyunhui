package qing.yun.hui.common.test.Collections.sort.po;

import java.util.Date;

import qing.yun.hui.common.utils.persistent.Anno;

public class SysUsers{
	
	@Anno(order=1,name="userId",remark="自增主键")
	private Integer userId;
	
	private Integer accountId;
	
	@Anno(order=2,name="userName",remark="姓名")
	private String userName;
	
	@Anno(order=5,name="sex",remark="性别")
	private Integer sex;
	
	@Anno(order=4,name="age",remark="年龄")
	private Integer age;
	
	@Anno(order=3,name="telephone",remark="telephone")
	private String telephone;
	
	
	private String mobilephone;
	
	private String userJob;
	
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
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END
	
	private String account;//账号
	private String password;//密码
	private Integer status;//账号状态

	public SysUsers(){
	}

	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}
	
	public Integer getAccountId(){
		return accountId;
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
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
	
	public String getMobilephone(){
		return mobilephone;
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
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}