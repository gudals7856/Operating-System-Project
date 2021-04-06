package report;

import java.util.Scanner;
import java.util.Vector;

// 메인 클래스
public class Crawling{
	public static Vector<String> save = new Vector<String>();
	public static void main(String[] args) {
		//Jsoup을 이용해서 네이버 해외축구 사이트 크롤링

		String keyword;
		System.out.println("*** 2010 ~ 2019년도 사이에 프리미어리그,라리가,분데스리가,세리에 리그에서 1-20위를 했던 선수들의 시즌기록입니다. ***\n"
				+ "- 축구선수명을 입력하시면 2010 ~ 2019년도 사이 그 선수가 달성한 기록이 검색됩니다.\n"
				+ "- 구단명을 입력하시면 2010 ~ 2019년도 사이에 구단 내에서 1-20위를 했던 선수들의 기록들이 모두 검색됩니다.\n"
				+ "*****************************************************************************************\n"
				+ "검색을 원하는 축구선수명 혹은 구단명을 입력하세요(ex.손흥민,메시 ,리버풀,첼시 등) : ");
		Scanner sc = new Scanner(System.in);
		keyword = sc.next();
		System.out.println("\n*** 크롤링 및 검색 중입니다. 잠시만 기다려주세요. ***\n");

		// 2010 ~ 2019년도 해외축구리그 선수들의 시즌마다의 기록 URL
		String[] url = new String[] {
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2019&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2018&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2017&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2016&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2015&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2014&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2013&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2012&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2011&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=epl&year=2010&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2019&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2018&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2017&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2016&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2015&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2014&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2013&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2012&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2011&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&year=2010&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2019&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2018&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2017&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2016&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2015&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2014&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2013&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2012&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2011&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=bundesliga&year=2010&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2019&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2018&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2017&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2016&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2015&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2014&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2013&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2012&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2011&tab=player",
				"https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&year=2010&tab=player",

		};

		// 페이지를 크롤링하는 스레드 40개 생성
		for(int i=0; i<40; i++)
		{
			try {
				PageThread pageTh = new PageThread(url[i]);
				pageTh.start();
				pageTh.join();
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		// 단어를 검색하는 쓰레드 40개 생성
		for(int i=0; i<40; i++)
		{
			try {
				Thread searchTh = new WordSearch(keyword,i);
				searchTh.start();
				searchTh.join();
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println("<" + keyword + ">은(는) 총 " + WordSearch.count + "번 검색되었습니다.");
		sc.close();
	}

}