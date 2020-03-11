package service;

import java.util.Scanner;

import dao.SitDao;
import data.Database;
import vo.SitVO;

public class Sitservice {

	Scanner sc = new Scanner(System.in);

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
			
			if(sit>3 || sit<1) {
				continue;
			}
			else {
				break;
			}
		}

		return sit;
	}

	public String start(int sitnum) {
		Database database = Database.getInstance();

		SitDao sitdao = SitDao.getInstance();

		// 좌석표를 담았다
		String[][] first = sitdao.first();
		String[][] business = sitdao.business();
		String[][] eco = sitdao.eco();

		// 좌석을 입력받는 메소드이다.

		SitVO sitvo = new SitVO();
		// System.out.print("인원수를 입력해주세요\n>> ");
		// int people = Integer.parseInt(sc.nextLine());

		int people = 1;

		sitvo.setSit_num(people);

		int sit = sitnum;

		char alpha;

		switch (sit) {
		case 1:
			String temp = "";
			sitvo.setSit_class("First Class");
			// 좌석표를 보여주는 반복문
			for (int q = 0; q < people; q++) {
				for (int i = 0; i < first.length; i++) {
					for (int j = 0; j < first[i].length; j++) {						
						System.out.printf("%s\t", first[i][j]);						
					}
					System.out.println();
				}

				System.out.print("원하시는 좌석을 입력해주세요\n>> ");
				String answer = sc.nextLine();

				a: for (int i = 0; i < first.length; i++) {
					for (int j = 0; j < first[i].length; j++) {
						if (answer.equals(first[i][j])) {
							temp += (first[i][j] + "  ");
							sitvo.setSit_number(temp);
							first[i][j] = "X";

							break a;
						}
					}
				}
			}			
			break;

		case 2:
			temp = "";
			sitvo.setSit_class("BusinessClass");
			for (int q = 0; q < people; q++) {
				for (int i = 0; i < business.length; i++) {
					for (int j = 0; j < business[i].length; j++) {
						if(i < 9) {
							System.out.printf(" %s\t", business[i][j]);
						}else {
							System.out.printf("%s\t", business[i][j]);
						}
					}
					System.out.println();
				}

				System.out.print("원하시는 좌석을 입력해주세요\n>> ");
				String answer = sc.nextLine();

				a: for (int i = 0; i < business.length; i++) {
					for (int j = 0; j < business[i].length; j++) {
						if (answer.equals(business[i][j])) {

							temp += (business[i][j] + "  ");
							sitvo.setSit_number(temp);
							business[i][j] = "X";

							break a;
						}
					}

				}

			}			
			break;
		case 3:
			temp = "";
			sitvo.setSit_class("Economy Class");
			for (int q = 0; q < people; q++) {
				for (int i = 0; i < eco.length; i++) {
					for (int j = 0; j < eco[i].length; j++) {
						if (j % 3 == 2) {
							if(i < 9) {
								System.out.printf(" %s\t", eco[i][j]);
							}else {
								System.out.printf("%s\t", eco[i][j]);
							}
						} else {
							if(i < 9) {
								System.out.printf(" %s ", eco[i][j]);
							}else {
								System.out.printf("%s ", eco[i][j]);
							}							
						}

					}
					System.out.println();
				}

				System.out.print("원하시는 좌석을 입력해주세요\n>> ");
				String answer = sc.nextLine();

				a: for (int i = 0; i < eco.length; i++) {
					for (int j = 0; j < eco[i].length; j++) {
						if (answer.equals(eco[i][j])) {
							temp += (eco[i][j] + "  ");
							sitvo.setSit_number(temp);
							eco[i][j] = "X";

							break a;
						}
					}
				}
			}			
			break;
		}
		System.out.println("예약이 완료되었습니다.\n이용해주셔서 감사합니다.\n");
		return sitvo.getSit_number();
	}

}