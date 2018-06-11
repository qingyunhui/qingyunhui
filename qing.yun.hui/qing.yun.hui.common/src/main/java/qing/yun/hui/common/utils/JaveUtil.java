package qing.yun.hui.common.utils;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;
/***
 ** @category <p>音频、视频、工具类、编码、解码、压缩等等..</p>
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2018年6月11日上午10:15:00
 **/
public class JaveUtil {

	public static void main(String[] args) {  
		String sourceFile="F:\\test\\javaTest\\helloword.mp4";
		String targetFile="F:\\test\\javaTest\\modfiyHelloword.mp4";
        File source = new File(sourceFile);  
        File target = new File(targetFile);  
        try {  
            AudioAttributes audio = new AudioAttributes();  
            audio.setCodec("libmp3lame");  
            audio.setBitRate(new Integer(56000));  
            audio.setChannels(new Integer(1));  
            audio.setSamplingRate(new Integer(22050));  
            VideoAttributes video = new VideoAttributes();  
            video.setCodec("mpeg4");  
            video.setBitRate(new Integer(800000));  
            video.setFrameRate(new Integer(15));  
            EncodingAttributes attrs = new EncodingAttributes();  
            attrs.setFormat("mp4");  
            attrs.setAudioAttributes(audio);  
            attrs.setVideoAttributes(video);  
            Encoder encoder = new Encoder();  
            encoder.encode(source, target, attrs);  
            System.out.println("success..");
        }  
        catch (EncoderException e) {  
            e.printStackTrace();  
        }  
    } 
	
}
