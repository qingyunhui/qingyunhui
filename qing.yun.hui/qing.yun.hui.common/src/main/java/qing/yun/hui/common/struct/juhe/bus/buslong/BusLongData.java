package qing.yun.hui.common.struct.juhe.bus.buslong;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午8:45:31
 **/
@Getter
@Setter
public class BusLongData {

	/**汽车站名称*/
	private String name;
	
	/**联系电话*/
	private String tel;
	
	/**地址*/
	private String adds;
	
	/*{
    "reason": "查询成功",
    "result": {
        "title": "温州市长途汽车站_时刻表",
        "list": [
            {
                "name": "温州市双屿客运中心汽车站",
                "tel": "0577-96035/88280050",
                "adds": "温州市温金路西方向高速路口西出口（双屿菜场对面）"
            },
            {
                "name": "温州市客运中心站",
                "tel": "0577--96035",
                "adds": "温州市牛山北路52号"
            },
            {
                "name": "温州市新城汽车站",
                "tel": "0577-88911927",
                "adds": "温州市新城大道219号"
            },
            {
                "name": "温州市汽车南站",
                "tel": "0577--96035",
                "adds": "温州市梧田南塘大道与疏港公路交叉口东北侧"
            },
            {
                "name": "温州市苍南县长运汽车站（西站）",
                "tel": "0577-64773111",
                "adds": "温州市苍南县建兴西路455号"
            }
        ]
    },
    "error_code": 0
}
*/
}
