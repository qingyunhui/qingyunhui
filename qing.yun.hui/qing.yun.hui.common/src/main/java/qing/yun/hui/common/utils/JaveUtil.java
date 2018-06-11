package qing.yun.hui.common.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;
/***
 ** @category <p>音频、视频、工具类、编码、解码、压缩等等..</p>
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2018年6月11日上午10:15:00
 **/
public class JaveUtil {

	static Logger logger=LoggerFactory.getLogger(JaveUtil.class);
	
	private static final String defaultFormat="mp4";//默认视频输出编码格式
	
	public static void main(String[] args) {  
		String sourcePath="F:/test/javaTest/video/helloword.mp4";
		String targetPath="F:/test/javaTest/video/11.wav";
		/*Audio audio=new Audio("libfaac", 128000, 44100);
		Video video=new Video("mpeg4", 800000);*/
//		defaultEncoder(sourcePath, targetPath,audio, video);
		extractAudioInformationFromVideo(sourcePath, targetPath,"wav",new Audio(""));
    }
	
	/**
	 * <p>从视频中提取音频信息并存储到指定文件中</p>
	 * @param sourcePath 待处理的文件
	 * @param targetPath 处理后的文件
	 * @param audioCodec 设置音频编码
	 * @param format     设置音频输出编码格式
	 * */
	public static void extractAudioInformationFromVideo(String sourcePath,String targetPath,String format,Audio audio){
		File source = new File(sourcePath); 
		File target = new File(targetPath);
		try {
			EncodingAttributes attrs = new EncodingAttributes();  
			attrs.setFormat(format);  
			attrs.setAudioAttributes(audio);  
			Encoder encoder = new Encoder();  
			encoder.encode(source, target, attrs); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>默认转码</p> 
	 * @param sourceFilePath 待处理的文件
	 * @param targetFilePath 处理后的文件
	 * @param audio  设置音频需要的值
	 * @param video  设置视频需要的值
	 * */
	public static void defaultEncoder(String sourcePath,String targetPath,Audio audio,Video video){
		defaultEncoder(sourcePath, targetPath, defaultFormat, audio, video);
	}
	
	/**
	 * <p>默认转码</p> 
	 * @param sourceFilePath 待处理的文件
	 * @param targetFilePath 处理后的文件
	 * @param format 输出编码格式
	 * @param audio  设置音频需要的值
	 * @param video  设置视频需要的值
	 * */
	public static void defaultEncoder(String sourcePath,String targetPath,String format,Audio audio,Video video){
		System.out.println("==================>sourceFilePath="+sourcePath+"\targetFilePath="+targetPath);
		if(StringUtil.isEmpty(sourcePath))return;
		File sourceFile=new File(sourcePath);
		if(!sourceFile.exists())return;
		if(StringUtil.isEmpty(audio,video)) return;
		File targetFile=new File(StringUtil.isEmpty(targetPath)?sourcePath:targetPath);
		try {
			long startTime=System.currentTimeMillis();
			//获取视频总长度
			Encoder encoder=new Encoder();
			MultimediaInfo multimediaInfo = encoder.getInfo(sourceFile);  
            long duration = multimediaInfo.getDuration();//获取视频的持续时间(毫秒)
            long second=duration/1000;
            System.out.println("视频总时长:"+second+"s\n================>开始转码....");
			//@1.设置转码属性
			EncodingAttributes encodingAttr = new EncodingAttributes();  
			encodingAttr.setFormat(format);//设置转码格式
			encodingAttr.setAudioAttributes(audio);//设置转码音频所需的音频属性
			encodingAttr.setVideoAttributes(video);//设置转码视频所需的视频属性
			//@2.开始转码
			encoder.encode(sourceFile, targetFile, encodingAttr);
			long endTime=System.currentTimeMillis();
			System.out.println("=================>转码结束，耗时:"+(endTime-startTime)+"ms.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//==================================> 以下是封装好的音频、视频、属性字段 ==================================>
	
	public static class Audio extends AudioAttributes{
		private static final long serialVersionUID = 1L;
		private static final Integer defaultChannels=1;//音频-默认频道
		/**<p>音频-属性</p>*/
		public Audio(){super();}
		/**
		 * @param code 音频-编码器
		 * @param bitRate 音频-比特率
		 * @param samplingRate 音频-节录率
		 * @param channels 音频-频道
		 * */
		public Audio(String codec,Integer bitRate,Integer samplingRate,Integer channels){
			setCodec(codec);
			setBitRate(bitRate);
			setSamplingRate(samplingRate);
			setChannels(channels);
		}
		/**
		 * @param code 音频-编码器
		 * @param bitRate 音频-比特率
		 * @param samplingRate 音频-节录率
		 * */
		public Audio(String codec,Integer bitRate,Integer samplingRate){
			this(codec, bitRate, samplingRate, defaultChannels);
		}
		
		/**
		 * @param code 音频-编码器
		 * */
		public Audio(String codec){
			setCodec(codec);
		}
		
		/**
		 * @param code 音频-编码器
		 * @param bitRate 音频-比特率
		 * @param samplingRate 音频-节录率
		 * @param channels 音频-频道
		 * @param volume 音频-音量 
		 * */
		public Audio(String codec,Integer bitRate,Integer samplingRate,Integer channels,Integer volume){
			this(codec, bitRate, samplingRate, channels);
			setVolume(volume);
		}
	}

	public static class Video extends VideoAttributes{
		private static final long serialVersionUID = 1L;
		private static final Integer defaultFrameRate=15;//视频-默认每秒15帧.
		public Video(){super();}
		/**
		 * @param codec 视频-编码器
		 * @param bitRate 视频-比特率
		 * @param frameRate 视频-帧率
		 * */
		public Video(String codec,Integer bitRate,Integer frameRate){
			setCodec(codec);
			setBitRate(bitRate);
			setFrameRate(frameRate);
		}
		/**
		 * <p>帧率-默认每秒/15帧</p>
		 * @param codec 视频-编码器
		 * @param bitRate 视频-比特率
		 * */
		public Video(String codec,Integer bitRate){
			this(codec, bitRate, defaultFrameRate);
		}
		/**
		 * <p>帧率-默认每秒/15帧</p>
		 * @param codec 视频-编码器
		 * @param bitRate 视频-比特率
		 * @param videoSize 视频大小(尺寸大小)
		 * */
		public Video(String codec,Integer bitRate,VideoSize videoSize){
			this(codec, bitRate, defaultFrameRate);
			setSize(videoSize);
		}
	}
	
}
