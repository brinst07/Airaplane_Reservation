package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CalendarService {

	public void start(int m) {

		Scanner sc = new Scanner(System.in);

		// 월별 날짜수를 배열에 저장
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // (2020-03-10) 형식으로 저장
		String styear = sdf.format(today).substring(0, 4); // 연도만 빼오기
		String stmon = sdf.format(today).substring(5, 7); // 달만 빼오기		

		int y = Integer.parseInt(styear); // 올해				

		int month[] = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int nalsu, i, week;		

		// 윤년확인
		if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0)
			month[1] = 29;
		else
			month[1] = 28;

		// 1년 1월 1일부터 구하는 날짜까지의 합
		nalsu = (y - 1) * 365 + (y - 1) / 4 - (y - 1) / 100 + (y - 1) / 400;
		for (i = 0; i < m - 1; i++)
			nalsu += month[i];

		nalsu += 1;// 구하고자하는 달의 첫번째 날 수

		// 구하고자 하는 달의 1일이 무슨 요일인지 계산
		week = nalsu % 7;

		// 그리기
		// System.out.println("\n 일 월 화 수 목 금 토");
		System.out.println("             [" + m + "월]");
		System.out.println("     일     월     화     수     목     금     토  ");
		System.out.println("==============================");
		// 특정 요일부터 시작하도록 공백을 지정
		for (i = 0; i < week; i++) {
			System.out.print("    ");// 4칸
		}
		// 해당 월의 날짜만 출력되도록 지정
		for (i = 1; i <= month[m - 1]; i++) {
			System.out.printf("%4d", i);

			// 한 주의 날짜가 출력되면 줄바꿈 처리
			week++;
			if (week % 7 == 0)
				System.out.println();

		}

		// 달력의 마지막 처리
		if (week % 7 != 0)
			System.out.println();
		System.out.println("==============================");
	}

}
