package qing.yun.hui.common.test.Collections.sort.po;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;				
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-07-15 10:26:07
 * @history
 */
public class SysDictForm {

	//columns START
	/**
	 * @Fields DICT_ID:编码ID：自增加
	 */
	@NotNull(message = "请填写编码ID：自增加")
	@Digits(integer = 20, fraction = 0, message = "数字的值超出了允许范围(只允许在{0}位整数和{1}位小数范围内)")
	private BigDecimal dictId;
	/**
	 * @Fields DICT_CODE:字典编码：一级字典必填，可通过编码找字典。
	 */
	@Length(max = 20, message = "字典编码：一级字典必填，可通过编码找字典。的长度不能超过{1}")
	private String dictCode;
	/**
	 * @Fields DICT_NAME:数据字典名称
	 */
	@NotEmpty(message = "请填写数据字典名称")
	@Length(max = 40, message = "数据字典名称的长度不能超过{1}")
	private String dictName;
	/**
	 * @Fields DICT_VALUE:字典参数值
	 */
	@Length(max = 40, message = "字典参数值的长度不能超过{1}")
	private String dictValue;
	/**
	 * @Fields PARENT_ID:父节点编码
	 */
	@NotNull(message = "请填写父节点编码")
	@Digits(integer = 20, fraction = 0, message = "数字的值超出了允许范围(只允许在{0}位整数和{1}位小数范围内)")
	private BigDecimal parentId;
	/**
	 * @Fields DICT_LEVEL:层级
	 */
	@NotNull(message = "请填写层级")
	@Range(message = "数值范围不正确")
	private Integer dictLevel;
	/**
	 * @Fields SEQ_NUM:排序
	 */
	@NotNull(message = "请填写排序")
	@Range(message = "数值范围不正确")
	private Integer seqNum;
	/**
	 * @Fields IS_USE:是否启用
	 */
	@NotEmpty(message = "请填写是否启用")
	@Length(max = 1, message = "是否启用的长度不能超过{1}")
	private String isUse;
	/**
	 * @Fields REMARK:备注
	 */
	@Length(max = 400, message = "备注的长度不能超过{1}")
	private String remark;
	/**
	 * @Fields REMARK1:备用字段1
	 */
	@Length(max = 200, message = "备用字段1的长度不能超过{1}")
	private String remark1;
	/**
	 * @Fields REMARK2:备用字段2
	 */
	@Length(max = 200, message = "备用字段2的长度不能超过{1}")
	private String remark2;
	/**
	 * @Fields CREATE_PERSON:创建人
	 */
	@NotEmpty(message = "请填写创建人")
	@Length(max = 20, message = "创建人的长度不能超过{1}")
	private String createPerson;
	/**
	 * @Fields CREATE_DATE:创建日期
	 */
	@NotEmpty(message = "请填写创建日期")
	@Length(max = 8, message = "创建日期的长度不能超过{1}")
	private String createDate;
	/**
	 * @Fields CREATE_TIME:创建时间
	 */
	@NotEmpty(message = "请填写创建时间")
	@Length(max = 14, message = "创建时间的长度不能超过{1}")
	private String createTime;
	/**
	 * @Fields UP_PERSON:修改人
	 */
	@NotEmpty(message = "请填写修改人")
	@Length(max = 20, message = "修改人的长度不能超过{1}")
	private String upPerson;
	/**
	 * @Fields UP_DATE:修改日期
	 */
	@NotEmpty(message = "请填写修改日期")
	@Length(max = 8, message = "修改日期的长度不能超过{1}")
	private String upDate;
	/**
	 * @Fields UP_TIME:修改时间
	 */
	@NotEmpty(message = "请填写修改时间")
	@Length(max = 14, message = "修改时间的长度不能超过{1}")
	private String upTime;
	//columns END

	public SysDictForm(){
	}

	public SysDictForm(BigDecimal dictId){
		this.dictId = dictId;
	}

	
	public void setDictId(BigDecimal dictId){
		this.dictId = dictId;
	}
	public BigDecimal getDictId(){
		return dictId;
	}
	
	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}
	public String getDictCode(){
		return dictCode;
	}
	
	public void setDictName(String dictName){
		this.dictName = dictName;
	}
	public String getDictName(){
		return dictName;
	}
	
	public void setDictValue(String dictValue){
		this.dictValue = dictValue;
	}
	public String getDictValue(){
		return dictValue;
	}
	
	public void setParentId(BigDecimal parentId){
		this.parentId = parentId;
	}
	public BigDecimal getParentId(){
		return parentId;
	}
	
	public void setDictLevel(Integer dictLevel){
		this.dictLevel = dictLevel;
	}
	public Integer getDictLevel(){
		return dictLevel;
	}
	
	public void setSeqNum(Integer seqNum){
		this.seqNum = seqNum;
	}
	public Integer getSeqNum(){
		return seqNum;
	}
	
	public void setIsUse(String isUse){
		this.isUse = isUse;
	}
	public String getIsUse(){
		return isUse;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return remark;
	}
	
	public void setRemark1(String remark1){
		this.remark1 = remark1;
	}
	public String getRemark1(){
		return remark1;
	}
	
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}
	public String getRemark2(){
		return remark2;
	}
	
	public void setCreatePerson(String createPerson){
		this.createPerson = createPerson;
	}
	public String getCreatePerson(){
		return createPerson;
	}
	
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	public String getCreateDate(){
		return createDate;
	}
	
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	
	public void setUpPerson(String upPerson){
		this.upPerson = upPerson;
	}
	public String getUpPerson(){
		return upPerson;
	}
	
	public void setUpDate(String upDate){
		this.upDate = upDate;
	}
	public String getUpDate(){
		return upDate;
	}
	
	public void setUpTime(String upTime){
		this.upTime = upTime;
	}
	public String getUpTime(){
		return upTime;
	}

}