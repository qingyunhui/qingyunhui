import java.math.BigDecimal;
import java.math.RoundingMode;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年2月6日上午11:04:43
 **/
public class Test {
	
	public static void main(String[] args) throws InterruptedException{
		int investDay=120;//投资天数
		int investMoney=60000;// 投资金额
		BigDecimal rate =new BigDecimal(0.08);//投资年华率
		System.out.println("================>活期<====================");
		System.out.println("投资金额："+investMoney+"元RMB，年化率："+rate.multiply(new BigDecimal(100)).setScale(0,RoundingMode.DOWN)+"%，投资天数为："+investDay+"天。");
		BigDecimal revenue=getRevenue(investDay, investMoney, rate);
		BigDecimal totalRevenue=new BigDecimal(investMoney).add(revenue);
		System.out.println("活期投资"+investDay+"天收益为："+revenue.toString()+"元RMB，总收益为（本金+收益）："+totalRevenue.toString()+"元RMB。");
		Thread.sleep(1000);
		System.out.println("\n================>定期<====================\n");
		BigDecimal regularRevenue=getRegularRevenue(investDay, investMoney, rate);
		BigDecimal totalRegularRevenue=new BigDecimal(investMoney).add(regularRevenue);
		System.out.println("定期投资"+investDay+"天收益为："+regularRevenue.toString()+"元RMB，总收益为（本金+收益）："+totalRegularRevenue.toString()+"元RMB。");
	}
	
	/**
	 * <p>计算每年的收益</p>
	 * @param investMoney 投资金额
	 * @param rate	投资年华率
	 * @return 每年的收益（利息）
	 * */
	public static BigDecimal getInvestYear(BigDecimal investMoney,BigDecimal rate){
		BigDecimal investYear=investMoney.multiply(rate).setScale(2,RoundingMode.DOWN);
		return investYear;
	}
	
	/**
	 * <p>计算每天的收益</p>
	 * @param investYear 年收益(利息)
	 * @return 每天的的收益（利息）
	 * */
	public static BigDecimal getInvestDays(BigDecimal investYear){
		BigDecimal investDays =investYear.divide(new BigDecimal("365"),2,RoundingMode.DOWN);//直接忽略毫厘小数,毫厘之后的一位直接舍去   一年按照365天计算
		return investDays;
	}
	
	/**
	 * <p>活期：1.存入当日即起息，次日结算，2.次日将昨日（本金+收益）自动复投。</p>
	 * @param investDay 投资天数
	 * @param investMoney 投资金额
	 * @param rate	投资年华率
	 * @return 收益+本金
	 * */
	public static BigDecimal getRevenue(int investDay,int investMoney,BigDecimal rate){
		BigDecimal money=new BigDecimal(investMoney);//当前投资金额
		BigDecimal totalRevenue=new BigDecimal("0");//总收益
		BigDecimal yearInvest=new BigDecimal(0);//年收益(利息)
		BigDecimal investday=new BigDecimal(0);//每天的收益
		for(int i=0;i<investDay;i++){
			yearInvest=getInvestYear(money, rate);//年收益
			investday=getInvestDays(yearInvest);//每天的收益
			totalRevenue=totalRevenue.add(investday);//计算复投(本金+前一天的收益)
			money=money.add(totalRevenue);
			System.out.println("第"+(i+1)+"天收益:"+investday+"元。");
		}
		return totalRevenue;
	}
	
	/**
	 * <p>定期</p>
	  * @param investDay 投资天数
	 * @param investMoney 投资金额
	 * @param rate	投资年华率
	 * @return 收益+本金
	 * */
	public static BigDecimal getRegularRevenue(int investDay,int investMoney,BigDecimal rate){
		BigDecimal money=new BigDecimal(investMoney);//当前投资金额
		BigDecimal yearInvest=getInvestYear(money, rate);//年收益(利息)
		BigDecimal investday=getInvestDays(yearInvest);//每天的收益
		BigDecimal totalInvest=investday.multiply(new BigDecimal(investDay));
		return totalInvest;
	}
}
