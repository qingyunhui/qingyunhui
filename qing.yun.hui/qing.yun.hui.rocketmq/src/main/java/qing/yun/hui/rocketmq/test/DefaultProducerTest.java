package qing.yun.hui.rocketmq.test;

import java.util.concurrent.TimeUnit;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日上午11:01:56
 **/
public class DefaultProducerTest {
	
	/*
	 *  rocket 3.5.9 版本 出现的异常：Send [3] times, still failed：
		默认开启了VIP通道，VIP通道端口为10911-2=10909。若Rocket服务器未启动端口10909，则报connect to <：10909> failed。
		解决办法：添加：producer.setVipChannelEnabled(false);
		
		消费者接收不到消息
	     （1）同理，如果消费者能够启动成功，但是没有接收到消息，原因可能同上。
	     （2）解决方式：增加一行代码： consumer.setVipChannelEnabled(false);  
	*/

	public static void main(String[] args) throws MQClientException,InterruptedException {
		
		/*
		 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ProducerGroupName需要由应用来保证唯一<br>
		 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
		 * 因为服务器会回查这个Group下的任意一个Producer
		 */
		
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("112.124.117.151:9876");
		producer.setInstanceName("DefaultProducer");
		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		producer.start();
		producer.setVipChannelEnabled(false);
		/**
		 * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
		 * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
		 * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
		 * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
		 */
		for (int i = 0; i < 1; i++) {
			try {
				{
					Message msg = new Message("TopicTest1","TagA",("Hello MetaQ").getBytes());
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
				{
					Message msg = new Message("TopicTest2","TagB",("Hello MetaQ").getBytes());
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
				{
					Message msg = new Message("TopicTest3","TagC",("Hello MetaQ").getBytes());
					SendResult sendResult = producer.send(msg);
					System.out.println(sendResult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		producer.shutdown();
	}
}
