package qing.yun.hui.common.struct.juhe.bus.busline;

import java.util.List;

import qing.yun.hui.common.struct.juhe.JuheResponse;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午9:22:45
 **/
@Getter
@Setter
public class BuslineResponse extends JuheResponse{
	
	List<BuslineData>  buslineDatas;
	
/*
	{
	    "reason": "success", 
	    "result": [
	        {
	            "terminal_name": "民心路新业路口", 
	            "front_spell": "", 
	            "line_id": "330100YWL15601", 
	            "key_name": "１５６路", 
	            "time_desc": "", 
	            "front_name": "翠苑四区", 
	            "stationdes": [
	                {
	                    "code": "330100", 
	                    "stationNum": "1", 
	                    "name": "翠苑四区", 
	                    "xy": "120.119137,30.287073"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "27", 
	                    "name": "民心路新业路口", 
	                    "xy": "120.211710,30.248831"
	                }
	            ], 
	            "description": "", 
	            "start_time": "0500", 
	            "photo_folder": "", 
	            "gpsfile_id": "", 
	            "data_source": "", 
	            "total_price": "2.000000", 
	            "company": "杭州市公共交通集团有限公司", 
	            "speed": "", 
	            "length": "14.914000", 
	            "loop": "0", 
	            "auto": "", 
	            "ic_card": "", 
	            "double_deck": "", 
	            "express_way": "", 
	            "status": "1", 
	            "basic_price": "0.000000", 
	            "end_time": "2230", 
	            "air": "", 
	            "terminal_spell": "", 
	            "type": "list", 
	            "paper_table_id": "", 
	            "name": "１５６路（翠苑四区－民心路新业路口）", 
	            "commutation_ticket": "0"
	        }, 
	        {
	            "terminal_name": "翠苑四区", 
	            "front_spell": "", 
	            "line_id": "330100YWL15602", 
	            "key_name": "１５６路", 
	            "time_desc": "", 
	            "front_name": "民心路新业路口", 
	            "stationdes": [
	                {
	                    "code": "330100", 
	                    "stationNum": "1", 
	                    "name": "民心路新业路口", 
	                    "xy": "120.211850,30.248933"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "2", 
	                    "name": "民心路丹桂街口", 
	                    "xy": "120.213379,30.250875"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "3", 
	                    "name": "新塘路杭海路口", 
	                    "xy": "120.208315,30.255921"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "4", 
	                    "name": "庆春广场东", 
	                    "xy": "120.208116,30.259100"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "5", 
	                    "name": "新塘路凤起东路口", 
	                    "xy": "120.207967,30.262819"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "6", 
	                    "name": "凤起东路景昙路口", 
	                    "xy": "120.204951,30.263801"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "7", 
	                    "name": "凤起东路秋涛北路口", 
	                    "xy": "120.198578,30.263631"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "8", 
	                    "name": "双菱路北口", 
	                    "xy": "120.193986,30.263525"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "9", 
	                    "name": "凤起立交", 
	                    "xy": "120.191453,30.265265"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "10", 
	                    "name": "浙大华家池校区", 
	                    "xy": "120.191381,30.268943"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "11", 
	                    "name": "公交总公司", 
	                    "xy": "120.183064,30.275186"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "12", 
	                    "name": "艮山门", 
	                    "xy": "120.176404,30.274588"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "13", 
	                    "name": "中北桥南", 
	                    "xy": "120.165142,30.273657"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "14", 
	                    "name": "杭州大厦", 
	                    "xy": "120.160941,30.273324"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "15", 
	                    "name": "武林门湖墅路口", 
	                    "xy": "120.158630,30.273155"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "16", 
	                    "name": "市府大楼", 
	                    "xy": "120.156097,30.272879"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "17", 
	                    "name": "密渡桥", 
	                    "xy": "120.153345,30.276279"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "18", 
	                    "name": "文三路马塍路口", 
	                    "xy": "120.145910,30.277623"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "19", 
	                    "name": "上宁桥", 
	                    "xy": "120.136571,30.277197"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "20", 
	                    "name": "九莲新村", 
	                    "xy": "120.134039,30.276956"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "21", 
	                    "name": "东方通信大厦", 
	                    "xy": "120.127210,30.276701"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "22", 
	                    "name": "天苑花园", 
	                    "xy": "120.121164,30.276353"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "23", 
	                    "name": "翠苑五区", 
	                    "xy": "120.119472,30.283051"
	                }, 
	                {
	                    "code": "330100", 
	                    "stationNum": "24", 
	                    "name": "翠苑四区", 
	                    "xy": "120.119142,30.287197"
	                }
	            ], 
	            "description": "", 
	            "start_time": "0550", 
	            "photo_folder": "", 
	            "gpsfile_id": "", 
	            "data_source": "", 
	            "total_price": "2.000000", 
	            "company": "杭州市公共交通集团有限公司", 
	            "speed": "", 
	            "length": "13.493000", 
	            "loop": "0", 
	            "auto": "", 
	            "ic_card": "", 
	            "double_deck": "", 
	            "express_way": "", 
	            "status": "1", 
	            "basic_price": "0.000000", 
	            "end_time": "2320", 
	            "air": "", 
	            "terminal_spell": "", 
	            "type": "list", 
	            "paper_table_id": "", 
	            "name": "１５６路（民心路新业路口－翠苑四区）", 
	            "commutation_ticket": "0"
	        }
	    ], 
	    "error_code": 0
	}
*/
	
}
