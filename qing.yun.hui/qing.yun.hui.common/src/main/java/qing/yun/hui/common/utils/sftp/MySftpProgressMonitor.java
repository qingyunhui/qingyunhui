package qing.yun.hui.common.utils.sftp;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpProgressMonitor;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月9日上午11:10:47
 **/
public class MySftpProgressMonitor extends TimerTask implements SftpProgressMonitor{

	
	Logger log=LoggerFactory.getLogger(MySftpProgressMonitor.class);
	
	/**
	 * 步骤：
		1.根据Jsch创建Session;
		2.设置Session密码、超时时间和属性等；
		3.连接session；
		4.使用Session创建ChannelSftp通道；
		5.接下来就可以使用ChannelSftp进行各种操作了：如文件上传、文件下载；
		6.最后，关系各种资源：如Session、ChannelSftp等；
		其他：还可以设置监听器，监控文件上传和下载的进度；
	 * */
	
 	private long progressInterval = 1 * 1000; // 默认间隔时间为5秒
    
    private boolean isEnd = false; // 记录传输是否结束
    
    private long transfered; // 记录已传输的数据总大小
    
    private long fileSize; // 记录文件总大小
    
    private Timer timer; // 定时器对象
    
    private boolean isScheduled = false; // 记录是否已启动timer记时器
    
    public MySftpProgressMonitor(long fileSize) {
        this.fileSize = fileSize;
    }
    
    public MySftpProgressMonitor() {}
    
    @Override
    public void run() {
        if (!isEnd()) { // 判断传输是否已结束
        	log.info("文件传输进行中.");
            long transfered = getTransfered();
            if (transfered != fileSize) { // 判断当前已传输数据大小是否等于文件总大小
                log.info("文件当前已传输: " + transfered + " bytes");
                sendProgressMessage(transfered);
            } else {
                log.info("文件传输完成.");
                setEnd(true); // 如果当前已传输数据大小等于文件总大小，说明已完成，设置end
            }
        } else {
            log.info("文件传输完成. 取消定时器.");
            stop(); // 如果传输结束，停止timer记时器
            return;
        }
    }
    
    public void stop() {
        log.info("准备停止文件传输监视器.");
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
            isScheduled = false;
        }
        log.info("文件传输监视器已停止.");
    }
    
    public void start() {
        log.info("准备打开文件传输监视器.");
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(this, 500, progressInterval);
        isScheduled = true;
        log.info("文件传输监视器已启动.");
    }
    
    /**
     * 打印progress信息
     * @param transfered
     */
    private void sendProgressMessage(long transfered) {
        if (fileSize != 0) {
            double d = ((double)transfered * 100)/(double)fileSize;
            DecimalFormat df = new DecimalFormat( "#.##"); 
            log.info("文件传输进展: " + df.format(d) + "%");
        } else {
            log.info("文件传输进展: " + transfered);
        }
    }

    /**
     * 实现了SftpProgressMonitor接口的count方法
     */
    public boolean count(long count) {
        if (isEnd()) return false;
        if (!isScheduled) {
            start();
        }
        add(count);
        return true;
    }

    /**
     * 实现了SftpProgressMonitor接口的end方法
     */
    public void end() {
        setEnd(true);
        log.info("文件传输结束.");
    }
    
    private synchronized void add(long count) {
        transfered = transfered + count;
    }
    
    private synchronized long getTransfered() {
        return transfered;
    }
    
    public synchronized void setTransfered(long transfered) {
        this.transfered = transfered;
    }
    
    private synchronized void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    
    private synchronized boolean isEnd() {
        return isEnd;
    }

    public void init(int op, String src, String dest, long max) {
    }

}
