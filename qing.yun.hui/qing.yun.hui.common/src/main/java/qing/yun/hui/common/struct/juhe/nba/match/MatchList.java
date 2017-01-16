package qing.yun.hui.common.struct.juhe.nba.match;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月16日上午10:42:33
 **/
@Getter
@Setter
public class MatchList {
	
	private String list;
	
	/*
	 "list": [
	            {
	                "title": "01-15 周日", 
	                "tr": [
	                    {
	                        "time": "01/15 04:30", 
	                        "player1": "湖人", 
	                        "player2": "快船", 
	                        "player1logo": "http://p7.qhmsg.com/t018b634f8a252b4177.png?size=78x78", 
	                        "player2logo": "http://p8.qhmsg.com/t01ac22731d284b6005.png?size=78x78", 
	                        "player1logobig": "http://p7.qhmsg.com/t018b634f8a252b4177.png?size=78x78", 
	                        "player2logobig": "http://p9.qhmsg.com/t01ac22731d284b6005.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=13", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=12", 
	                        "link1url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469236", 
	                        "m_link1url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469236%26title%3D%25E6%25B9%2596%25E4%25BA%25BAVS%25E5%25BF%25AB%25E8%2588%25B9%26ptag%3D360.onebox.schedule.nba&m=d25ade&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "link2text": "技术统计", 
	                        "link2url": "http://nba.stats.qq.com/nbascore/?mid=1469236", 
	                        "m_link2url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469236%26title%3D%25E6%25B9%2596%25E4%25BA%25BAVS%25E5%25BF%25AB%25E8%2588%25B9%26ptag%3D360.onebox.schedule.nba&m=d25ade&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "status": 2, 
	                        "score": "97-113", 
	                        "link1text": "视频集锦"
	                    }, 
	                    {}, 
	                    {}, 
	                    {}, 
	                    {}
	                ], 
	                "bottomlink": [
	                    {
	                        "text": "常规赛赛程", 
	                        "url": "http://sports.qq.com/nba/schedule/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "球队排名", 
	                        "url": "http://sports.qq.com/nba/standings/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "球员排名", 
	                        "url": "http://nba.stats.qq.com/stats/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "社区讨论", 
	                        "url": "http://sports.qq.com/fans/group.htm?mid=69"
	                    }
	                ]
	            }, 
	            {
	                "title": "01-16 周一", 
	                "live": [
	                    {
	                        "title": "10:30开赛 比赛未开始", 
	                        "player1": "活塞", 
	                        "player2": "湖人", 
	                        "player1info": "胜18负24东部11名", 
	                        "player2info": "胜15负29西部13名", 
	                        "player1logobig": "http://p0.qhmsg.com/t01a80ea5beeff44608.png?size=78x78", 
	                        "player2logobig": "http://p5.qhmsg.com/t018b634f8a252b4177.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=8", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=13", 
	                        "player1location": "(客)", 
	                        "player2location": "(主)", 
	                        "status": 0, 
	                        "score": "VS", 
	                        "liveurl": ""
	                    }
	                ], 
	                "livelink": [
	                    {
	                        "text": "视频直播", 
	                        "url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469247"
	                    }, 
	                    {
	                        "text": "技术统计", 
	                        "url": ""
	                    }
	                ], 
	                "tr": [
	                    {
	                        "time": "01/16 10:00", 
	                        "player1": "雷霆", 
	                        "player2": "国王", 
	                        "player1logo": "http://p8.qhmsg.com/t0198101dee56f2e9e6.png?size=78x78", 
	                        "player2logo": "http://p0.qhmsg.com/t0179411022c3587ba2.png?size=78x78", 
	                        "player1logobig": "http://p1.qhmsg.com/t0198101dee56f2e9e6.png?size=78x78", 
	                        "player2logobig": "http://p1.qhmsg.com/t0179411022c3587ba2.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=25", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=23", 
	                        "link1url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469245", 
	                        "m_link1url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469245%26title%3D%25E9%259B%25B7%25E9%259C%2586VS%25E5%259B%25BD%25E7%258E%258B%26ptag%3D360.onebox.schedule.nba&m=7e18c1&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "link2text": "技术统计", 
	                        "link2url": "http://nba.stats.qq.com/nbascore/?mid=1469245", 
	                        "m_link2url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469245%26title%3D%25E9%259B%25B7%25E9%259C%2586VS%25E5%259B%25BD%25E7%258E%258B%26ptag%3D360.onebox.schedule.nba&m=7e18c1&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "status": 1, 
	                        "score": "0-0", 
	                        "link1text": "视频直播"
	                    }, 
	                    {
	                        "time": "01/16 03:00", 
	                        "player1": "森林狼", 
	                        "player2": "小牛", 
	                        "player1logo": "http://p1.qhmsg.com/t0101b9d3f51b24df5c.png?size=78x78", 
	                        "player2logo": "http://p3.qhmsg.com/t016809e551c18d1ad5.png?size=78x78", 
	                        "player1logobig": "http://p0.qhmsg.com/t0101b9d3f51b24df5c.png?size=78x78", 
	                        "player2logobig": "http://p5.qhmsg.com/t016809e551c18d1ad5.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=16", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=6", 
	                        "link1url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469243", 
	                        "m_link1url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469243%26title%3D%25E6%25A3%25AE%25E6%259E%2597%25E7%258B%25BCVS%25E5%25B0%258F%25E7%2589%259B%26ptag%3D360.onebox.schedule.nba&m=f05107&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "link2text": "技术统计", 
	                        "link2url": "http://nba.stats.qq.com/nbascore/?mid=1469243", 
	                        "m_link2url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469243%26title%3D%25E6%25A3%25AE%25E6%259E%2597%25E7%258B%25BCVS%25E5%25B0%258F%25E7%2589%259B%26ptag%3D360.onebox.schedule.nba&m=f05107&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "status": 2, 
	                        "score": "87-98", 
	                        "link1text": "视频集锦"
	                    }, 
	                    {}, 
	                    {
	                        "time": "01/16 10:00", 
	                        "player1": "公牛", 
	                        "player2": "灰熊", 
	                        "player1logo": "http://p0.qhmsg.com/t0190ed8c80f7525b15.png?size=78x78", 
	                        "player2logo": "http://p3.qhmsg.com/t01c6cd06b91021cabb.png?size=78x78", 
	                        "player1logobig": "http://p6.qhmsg.com/t0190ed8c80f7525b15.png?size=78x78", 
	                        "player2logobig": "http://p1.qhmsg.com/t01c6cd06b91021cabb.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=4", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=29", 
	                        "link1url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469246", 
	                        "m_link1url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469246%26title%3D%25E5%2585%25AC%25E7%2589%259BVS%25E7%2581%25B0%25E7%2586%258A%26ptag%3D360.onebox.schedule.nba&m=6e6428&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "link2text": "技术统计", 
	                        "link2url": "", 
	                        "m_link2url": "", 
	                        "status": 0, 
	                        "score": "VS", 
	                        "link1text": "视频直播"
	                    }, 
	                    {}
	                ], 
	                "bottomlink": [
	                    {
	                        "text": "常规赛赛程", 
	                        "url": "http://sports.qq.com/nba/schedule/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "球队排名", 
	                        "url": "http://sports.qq.com/nba/standings/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "球员排名", 
	                        "url": "http://nba.stats.qq.com/stats/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {
	                        "text": "社区讨论", 
	                        "url": "http://sports.qq.com/fans/group.htm?mid=69"
	                    }
	                ]
	            }, 
	            {
	                "title": "01-17 周二", 
	                "tr": [
	                    {
	                        "time": "01/17 02:00", 
	                        "player1": "老鹰", 
	                        "player2": "尼克斯", 
	                        "player1logo": "http://p6.qhmsg.com/t015e29cacbb39ab2ea.png?size=78x78", 
	                        "player2logo": "http://p2.qhmsg.com/t01eb9fe49a7bfa9614.png?size=78x78", 
	                        "player1logobig": "http://p6.qhmsg.com/t015e29cacbb39ab2ea.png?size=78x78", 
	                        "player2logobig": "http://p0.qhmsg.com/t01eb9fe49a7bfa9614.png?size=78x78", 
	                        "player1url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=1", 
	                        "player2url": "http://kbs.sports.qq.com/kbsweb/teams.htm?tid=18", 
	                        "link1url": "http://sports.qq.com/kbsweb/game.htm?mid=100000:1469248", 
	                        "m_link1url": "http://j.www.haosou.com/?u=http%3A%2F%2Fsports.qq.com%2Fkbsweb%2Fkbsshare%2Fgame.htm%3Fmid%3D100000%3A1469248%26title%3D%25E8%2580%2581%25E9%25B9%25B0VS%25E5%25B0%25BC%25E5%2585%258B%25E6%2596%25AF%26ptag%3D360.onebox.schedule.nba&m=0b681a&from=juhe&type=nba_new&juid=JH5cadd57a5c7d7baf60abfe76545935b6", 
	                        "link2text": "技术统计", 
	                        "link2url": "", 
	                        "m_link2url": "", 
	                        "status": 0, 
	                        "score": "VS", 
	                        "link1text": "视频直播"
	                    }, 
	                    {}, 
	                ], 
	                "bottomlink": [
	                	 {
                        	"text": "常规赛赛程", 
                        	"url": "http://sports.qq.com/nba/schedule/?ptag=360.onebox.schedule.nba"
	                     }, 
	                    {
	                        "text": "球队排名", 
	                        "url": "http://sports.qq.com/nba/standings/?ptag=360.onebox.schedule.nba"
	                    }, 
	                    {}, 
	                    {}
	                ]
	            }
	        ]
	       */
}
