package qing.yun.hui.common.struct.juhe.bus.busline;

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
 ** @createTime: 2017年1月13日下午2:26:33
 **/
@Getter
@Setter
public class BuslineData {
	
	private String terminal_name;	//"民心路新业路口"
	private String front_spell; 
	private String line_id;		   //"330100YWL15601" 
	private String key_name;	  //"１５６路"
	private String time_desc; 
	private String front_name;	 //"翠苑四区"
	
	private String stationdes;//json数组...
	
	private List<Stationdes> stationdesList;
	
	private String description; 
	private String start_time;
	private String photo_folder;
	private String gpsfile_id; 
	private String data_source;
	private String total_price;
	private String company;// "杭州市公共交通集团有限公司", 
	private String speed; 
	private String length;//"14.914000", 
	private String loop; 
	private String auto;
	private String ic_card;
	private String double_deck;
	private String express_way;
	private String status;// "1", 
	private String basic_price;
	private String end_time;// "2230", 
	private String air;
	private String terminal_spell;
	private String type;// "list", 
	private String paper_table_id;
	private String name;// "１５６路（翠苑四区－民心路新业路口）", 
	private String commutation_ticket;// "0"
	
	public List<Stationdes> getStationdesList(){
		if(null==stationdesList)stationdesList=new ArrayList<Stationdes>();
		if(!StringUtil.isEmpty(stationdes)){
			stationdesList=JSONObject.parseArray(stationdes, Stationdes.class);
		}
		return stationdesList;
	}
	
}
