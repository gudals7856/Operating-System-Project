package report;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// URL에 있는 정보들을 크롤링하는 클래스
public class PageThread extends Thread {
	private String address;									// URL을 저장하는 멤버변수
	public static Vector<String> save=new Vector<String>();		// 정보를 저장하는 Vector
	public PageThread(String url) {
		address = url;
	}


	public void run() {
		// 크롤링을 진행할 URL을 가져옴

		Document doc = null;
		try {
			doc = Jsoup.connect(address).get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Elements season = doc.select(".select_box .btn_toggle");			// 현재 URL에서 보여주는 시즌
		Elements table = doc.select("div#wfootballPlayerRecordBody");		// 축구선수들의 이번 시즌 기록을 보여주는 전체 창
		Elements result = table.select("tbody tr");				// 한 선수마다의 기록

		// season에서 필요한 부분만 남기기 위해 substring 이용
		String season_s = season.text().substring(6);

		// rank : 선수의 이번시즌 순위
		// achievement : 선수가 이번시즌 달성한 기록
		// name : 선수명
		// team : 선수의 소속팀
		for(Element plr_element : result)
		{
			Elements rank = plr_element.select(".num");
			Elements achievement = plr_element.select("tr span");
			Elements name = plr_element.select(".name");
			Elements team = plr_element.select(".team");

			// acheivement.text()에 포함되어 있는 한글단어의 개수 추출
			String hanguel = name.text() + " " + team.text();
			String[] hanguelArr = hanguel.split(" ");
			int hanguelNum = hanguelArr.length;

			// 선수가 달성한 기록들이 19 4 23 ... 이런 형태로 저장되므로 따로 저장하기 위해 split 함수를 사용한다.
			String[] ach = achievement.text().split(" ");

			String goal = ach[hanguelNum];			// 득점
			String assist = ach[hanguelNum+1];		// 도움
			String stPoint = ach[hanguelNum+2];		// 공격포인트
			String shoot = ach[hanguelNum+3];		// 슈팅
			String foul = ach[hanguelNum+4];		// 파울
			String warning = ach[hanguelNum+5];		// 경고
			String ejection = ach[hanguelNum+6];	// 퇴장
			String corner = ach[hanguelNum+7];		// 코너킥
			String penalty = ach[hanguelNum+8];		// 페널티킥
			String offside = ach[hanguelNum+9];		// 오프사이드
			String vld_shoot = ach[hanguelNum+10];	// 유효슈팅
			String gameNum = ach[hanguelNum+11];	// 경기수

			String threadresult = season_s + " 시즌을 검색하였습니다.\n"
					+ "(검색을 진행한 URL :" + address + ")\n"
					+ "선수명 : " + name.text() + "\n"
					+ "소속팀 : " + team.text() + "\n"
					+ "시즌 성적 : " + rank.text() + "위" + "\n"
					+ "<전체 기록>\n득점 : " + goal + "   도움  : " + assist + "   공격포인트 : " + stPoint + "   슈팅 : " + shoot
					+ "   파울 : " + foul + "   경고  : " + warning + "   퇴장 : " + ejection + "   코너킥 : " + corner
					+ "   페널티킥 : " + penalty + "   오프사이드  : " + offside + "   유효슈팅 : " + vld_shoot + "   경기수 :" 					+gameNum + "\n\n";

			save.add(threadresult);
		}
	}
}