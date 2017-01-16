package qing.yun.hui.common.struct.juhe.video.searching;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月16日下午2:15:12
 **/
@Setter
@Getter
public class SearchingData {
	
	 private String title;// "闪电侠第一季", 
	 
	 private String tag;// "科幻 / 动作", 
	 
	 private String act;// "格兰特·古斯汀 埃涅·赫德森 汤姆·卡瓦纳夫", 
	 
	 private String year;//"2014", 
	 
	 private String rating;// null, 
	 
	 private String area;//"美国", 
	 
	 private String dir;//"大卫·努特尔", 
	 
	 private String desc;// "《闪电侠》精彩看点：二次元超级英雄再登电视荧屏，《闪电侠》无缝对接《绿箭侠》闪耀登场。《闪电侠》剧情梗概：《闪电侠》的漫画连载开始于1940年，讲述了一名拥有超级速度的学生的故事。50年代起，这个角色则被重新诠释，成为了巴里·艾伦，一名为警署工作的科学家，使用他的超级速度来对抗超级反派们。", 
	 
	 private String cover;// "http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/0/0l01jm9yobh4xo4.jpg", 
	 
	 private String vdo_status;// "play", 
	 
	 //json串
	 private String playlinks;//json串，播放连接地址列表
	 
	 private String video_rec;//json串，视频资源列表集合
	 
	 private String act_s;//json串，导演相关的作品列表集合
	 
	 //json串-转换成javaBean
	 private Playlink playlink;//播放连接地址列表
	 
	 private List<VideoRec> videoRecList;//视频资源列表集合
	 
	 private List<ActSource> actSourceList;//导演相关的作品列表集合
	 
	 //==============处理json，josn～javaBean的转换...
	 
	 /**json串到javaBean的转换*/
	 public Playlink getPlaylink(){
		 if(!StringUtil.isEmpty(playlinks)){
			 if(null==playlink) playlink=new Playlink();
			 playlink=JSONObject.parseObject(playlinks, Playlink.class);
		 }
		 return playlink;
	 }
	 
	 /**json串到javaBean的转换*/
	 public List<VideoRec> getVideoRecList(){
		 if(!StringUtil.isEmpty(video_rec)){
			 if(null==videoRecList) videoRecList=new ArrayList<VideoRec>();
			 videoRecList=JSONObject.parseArray(video_rec, VideoRec.class);
		 }
		 return videoRecList;
	 }
	 
	 /**json串到javaBean的转换*/
	 public List<ActSource> getActSourceList(){
		 if(!StringUtil.isEmpty(act_s)){
			 if(null==actSourceList) actSourceList=new ArrayList<ActSource>();
			 actSourceList=JSONObject.parseArray(act_s, ActSource.class);
		 }
		 return actSourceList;
	 }
	 
	/***
	{
	    "reason": "查询成功", 
	    "result": {
	        "title": "闪电侠第一季", 
	        "tag": "科幻 / 动作", 
	        "act": "格兰特·古斯汀 埃涅·赫德森 汤姆·卡瓦纳夫", 
	        "year": "2014", 
	        "rating": null, 
	        "area": "美国", 
	        "dir": "大卫·努特尔", 
	        "desc": "《闪电侠》精彩看点：二次元超级英雄再登电视荧屏，《闪电侠》无缝对接《绿箭侠》闪耀登场。《闪电侠》剧情梗概：《闪电侠》的漫画连载开始于1940年，讲述了一名拥有超级速度的学生的故事。50年代起，这个角色则被重新诠释，成为了巴里·艾伦，一名为警署工作的科学家，使用他的超级速度来对抗超级反派们。", 
	        "cover": "http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/0/0l01jm9yobh4xo4.jpg", 
	        "vdo_status": "play", 
	        "playlinks": {
	            "youku": "http://v.youku.com/v_show/id_XODQ1NTAzNDE2.html?tpa=dW5pb25faWQ9MTAyMjEzXzEwMDAwNl8wMV8wMQ", 
	            "qq": "http://v.qq.com/cover/0/0l01jm9yobh4xo4/g0015dn2fw1.html", 
	            "leshi": "http://www.letv.com/ptv/vplay/21416940.html", 
	            "pptv": "http://v.pptv.com/show/2uhW1T2jE1G0Mr4.html", 
	            "sohu": "http://tv.sohu.com/20141210/n406824703.shtml?txid=4e4df35dda9d8ed32c874b1ad590ef59"
	        }, 
	        "video_rec": [
	            {
	                "detail_url": "http://www.360kan.com/tv/PrVtaX7kRzXsMn.html", 
	                "cover": "http://p2.qhimg.com/t01f969930fae67d1ec.jpg", 
	                "title": "神盾局特工 第2季"
	            }, 
	            {
	                "detail_url": "http://www.360kan.com/tv/Q4RvaqOoRmDuMX.html", 
	                "cover": "http://p6.qhimg.com/t0160a8a6f5b768034a.jpg", 
	                "title": "遗失的世界"
	            }, 
	            {
	                "detail_url": "http://www.360kan.com/tv/Q4Frc3GoRmbuMX.html", 
	                "cover": "http://p7.qhimg.com/t01513514907831e055.jpg", 
	                "title": "浩劫余生 第一季"
	            }, 
	            {
	                "detail_url": "http://www.360kan.com/tv/QrFob33oRGboMX.html", 
	                "cover": "http://p6.qhimg.com/d/_hao360/video/img200909_18_145544738.jpg", 
	                "title": "新绿野仙踪之铁皮人"
	            }, 
	            {
	                "detail_url": "http://www.360kan.com/tv/QrRtbaOpRz4nOH.html", 
	                "cover": "http://p1.qhimg.com/t01d2996b3305923b91.jpg", 
	                "title": "陨落星辰第三季"
	            }
	        ], 
	        "act_s": [
	            {
	                "name": "格兰特·古斯汀", 
	                "url": "http://baike.so.com/doc/2041872.html", 
	                "image": "http://p3.qhimg.com/dmsmty/120_110_100/t019f2fb2f92c6cb2cf.jpg"
	            }, 
	            {
	                "name": "埃涅·赫德森", 
	                "url": "http://baike.so.com/doc/3938849.html", 
	                "image": "http://p2.qhimg.com/dmsmty/120_110_100/t0169332727e692e9fa.jpg"
	            }, 
	            {
	                "name": "汤姆·卡瓦纳夫", 
	                "url": "http://baike.so.com/doc/7521211.html", 
	                "image": "http://p0.qhimg.com/dmsmty/120_110_100/t01d271d8c090330ae2.jpg"
	            }
	        ]
	    }, 
	    "error_code": 0
	}
**/
	
}
