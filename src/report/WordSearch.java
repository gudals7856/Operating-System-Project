package report;

// 크롤링된 정보에서 검색한 선수 혹은 팀명에 관한 정보를 검색하고 검색된 횟수를 계산하는 클래스
public class WordSearch extends Thread{
	public String word;				// 검색한 단어
	int number;						// 스레드가 탐색하는 인덱스
	public static int count = 0;	// 단어가 검색된 횟수

	public WordSearch(String word,int num)
	{
		number = num * 20;
		this.word = word;
	}

	public void run() {
		for(int i=number; i<number+20; i++)
		{
			if(PageThread.save.get(i).contains(word)) {
				System.out.println(PageThread.save.get(i));
				count++;
			}
		}
	}
}




