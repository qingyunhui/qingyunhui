package qing.yun.hui.common.utils.test;

/**
 * @Description:连连对账状态枚举
 * 
 * @Date Create on 2015-11-17
 * @author <a href="mailto:zhangguo@zuozh.com">zhangguo</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class BillLianlian {
	
	/**
	 * 余额投资流水的状态1：成功，2：失败
	 * 
	 * 
	 */
	public enum BalanceStreamStatus {
		SUCCESS("1", "成功"), FAIL("2", "失败");
		
		private String code;
		
		private String name;
		
		private BalanceStreamStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	/**
	 * 支付流水的状态1：成功，2：失败
	 * 
	 */
	public enum PayStatus implements ICommonEnum{
		SUCCESS("1", "成功"), FAIL("2", "失败");
		
		private String code;
		
		private String name;
		
		private PayStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return name;
		}
	}
	
	/**
	 * 连连账单状态，1：成功，2：失败
	 */
	public enum LianLianStatus {
		SUCCESS("1", "成功"), FAIL("2", "失败");
		
		private String code;
		
		private String name;
		
		private LianLianStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 1：平账，2：不平账，3：挂起（不平账后，操作人可以执行挂起 操作）
	 */
	public enum CheckStatus {
		
		BALANCEACCOUNT("1", "平账"), NOT_BALANCEACCOUNT("2", "不平账"), HAND_UP("3", "挂起");
		
		private String code;
		
		private String name;
		
		private CheckStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 0：初始，1：完成，2：不平账后挂起
	 */
	public enum OperateStatus {
		
		INITIAL("0", "初始"), FINISH("1", "完成"), HAND_UP("2", "不平账后挂起");
		
		private String code;
		
		private String name;
		
		private OperateStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 连连账单的交易状态，0：成功，5：退款
	 */
	public enum LianBillDetailStatus {
		
		SUCCESS(0, "成功"), NOT_EXIST(2, "不存在"), REFUND(5, "退款");
		
		private Integer code;
		
		private String value;
		
		public static LianBillDetailStatus getByValue(Integer key) {
			if (null != key) {
				for (LianBillDetailStatus type : values()) {
					if (type.getCode().equals(key)) {
						return type;
					}
				}
			}
			return null;
		}
		
		private LianBillDetailStatus(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
	}
}
