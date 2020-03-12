package service;

import java.util.Scanner;

import dao.SitDao;
import data.Database;
import vo.SitVO;

public class Sitservice {
	Database database = Database.getInstance();

	Scanner sc = new Scanner(System.in);
	
	String[][] first = new String[4][3];
	String[][] business = new String[15][3];
	String[][] eco = new String[25][9];

	public int classcho() {
		int sit = 0;
		while (true) {
			System.out.println("    [좌석 선택]");
			System.out.println("[1] First Class");
			System.out.println("[2] Business Class");
			System.out.println("[3] Economy Class");
			System.out.println("좌석 클래스를 선택해주세요");
			System.out.print(">> ");
			sit = Integer.parseInt(sc.nextLine());

			if (sit > 3 || sit < 1) {
				continue;
			} else {
				break;
			}
		}

		return sit;
	}

	public int Human() {
		int people;
		while (true) {
			System.out.print("인원수를 입력해주세요\n>> ");
			people = Integer.parseInt(sc.nextLine());
			if (people < 1) {
				System.out.println("최소 1명 이상은 예약하셔야합니다.");
				continue;
			}

			if (people > 10) {
				System.out.println("최대 10명까지 가능합니다.");
				continue;
			} else {
				break;
			}
		}
		return people;
	}
	
	public void start() { // 배열 초기화
		
		char alpha;

		for (int i = 0; i < first.length; i++) {
			alpha = 'A';
			for (int j = 0; j < first[i].length; j++) {
				first[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}

		}
		
		//비즈니스
		
		for (int i = 0; i < business.length; i++) {
			alpha = 'A';
			for (int j = 0; j < business[i].length; j++) {
				business[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}

		}
		
		//이코노미
		
		for (int i = 0; i < eco.length; i++) {
			alpha = 'A';
			for (int j = 0; j < eco[i].length; j++) {
				eco[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}
		}
	}
	
	public String start1(int sitnum) {		
		
		String temp = "";

		SitVO sitvo = new SitVO();
		
		int sit = sitnum;

		char alpha;

		switch (sit) {
		case 1:
			temp = "";			
			// 좌석표를 보여주는 반복문

			for (int i = 0; i < first.length; i++) {
				for (int j = 0; j < first[i].length; j++) {
					System.out.printf("%s\t", first[i][j]);
				}
				System.out.println("\n\n\n");
			}

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer = sc.nextLine();

			a: for (int i = 0; i < first.length; i++) {
				for (int j = 0; j < first[i].length; j++) {
					if (answer.equals(first[i][j])) {
						temp += (first[i][j] + "  ");
						
						first[i][j] = " X ";

						break a;
					}
				}
			}

			break;

		case 2:
			temp = "";			

			for (int i = 0; i < business.length; i++) {
				for (int j = 0; j < business[i].length; j++) {
					if (i < 9) {
						System.out.printf(" %s\t", business[i][j]);
					} else {
						System.out.printf("%s\t", business[i][j]);
					}
				}
				System.out.println("\n\n");
			}

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer2 = sc.nextLine();

			a: for (int i = 0; i < business.length; i++) {
				for (int j = 0; j < business[i].length; j++) {
					if (answer2.equals(business[i][j])) {

						temp += (business[i][j] + "  ");
						
						if (i < 9) {
							business[i][j] = "X ";
						} else {
							business[i][j] = " X ";
						}

						break a;
					}
				}

			}

			break;
		case 3:
			temp = "";			

			for (int i = 0; i < eco.length; i++) {
				for (int j = 0; j < eco[i].length; j++) {
					if (j % 3 == 2) {
						if (i < 9) {
							System.out.printf(" %s\t", eco[i][j]);
						} else {
							System.out.printf("%s\t", eco[i][j]);
						}
					} else {
						if (i < 9) {
							System.out.printf(" %s ", eco[i][j]);
						} else {
							System.out.printf("%s ", eco[i][j]);
						}
					}

				}
				System.out.println();
			}

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer3 = sc.nextLine();

			a: for (int i = 0; i < eco.length; i++) {
				for (int j = 0; j < eco[i].length; j++) {
					if (answer3.equals(eco[i][j])) {
						temp += (eco[i][j] + "  ");
						
						if (i < 9) {
							eco[i][j] = "X ";
						} else {
							eco[i][j] = " X ";
						}

						break a;
					}
				}
			}

			break;
		}
		return temp;
	}

}