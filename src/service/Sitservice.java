package service;

import java.util.Scanner;

import dao.SitDao;
import data.Database;
import vo.SitVO;

public class Sitservice {
	public static void main(String[] args) {
		Database database = Database.getInstance();
		
		SitDao sitdao = SitDao.getInstance();
		
		
		//좌석표를 담았다
		String[][] first = sitdao.first();
		String[][] business = sitdao.business();
		String[][] eco = sitdao.eco();
		
		Scanner sc = new Scanner(System.in);
		// 좌석을 입력받는 메소드이다.

		System.out.println("인원수를 입력해주세요");
		
		SitVO sitvo = new SitVO();
		int people = Integer.parseInt(sc.nextLine());
		sitvo.setSit_num(people);
		System.out.println("좌석 클래스를 선택해주세요");
		System.out.println("[1] First Class");
		System.out.println("[2] Business Class");
		System.out.print("[3] Economy Class");
		System.out.print(">>");
		int sit = Integer.parseInt(sc.nextLine());
		char alpha;
		
		switch (sit) {
		case 1:
			String temp = "";
			sitvo.setSit_class("First Class");
			//좌석표를 보여주는 반복문
			for(int q = 0; q<people; q++) {
			for(int i = 0; i<first.length; i++) {
				for(int j = 0; j<first[i].length; j++) {
					System.out.printf("%s\t",first[i][j]);
				}
				System.out.println();
			}
			
			System.out.println("원하시는 좌석을 입력해주세요");
			String answer = sc.nextLine();
			
			a : for(int i = 0; i<first.length; i++) {
				for(int j = 0; j<first[i].length; j++) {
					if(answer.equals(first[i][j])) {
						
						temp += (first[i][j]+"  ");
						sitvo.setSit_number(temp);
						first[i][j] = "X";
						
						break a;
					}
				}
				
			}
			
			}
			System.out.println(sitvo.getSit_number());
			break;
			
		case 2:
			temp = "";
			sitvo.setSit_class("BusinessClass");
			for(int q = 0; q<people; q++) {
				for(int i = 0; i<business.length; i++) {
					for(int j = 0; j<business[i].length; j++) {
						System.out.printf("%s\t",business[i][j]);
					}
					System.out.println();
				}
				
				System.out.println("원하시는 좌석을 입력해주세요");
				String answer = sc.nextLine();
				
				a : for(int i = 0; i<business.length; i++) {
					for(int j = 0; j<business[i].length; j++) {
						if(answer.equals(business[i][j])) {
							
							temp += (business[i][j]+"  ");
							sitvo.setSit_number(temp);
							business[i][j] = "X";
							
							break a;
						}
					}
					
				}
				
				}
				System.out.println(sitvo.getSit_number());
				break;
		case 3:
			temp = "";
			sitvo.setSit_class("Economy Class");
			for(int q = 0; q<people; q++) {
				for(int i = 0; i<eco.length; i++) {
					for(int j = 0; j<eco[i].length; j++) {
						System.out.printf("%s\t",eco[i][j]);
					}
					System.out.println();
				}
				
				System.out.println("원하시는 좌석을 입력해주세요");
				String answer = sc.nextLine();
				
				a : for(int i = 0; i<eco.length; i++) {
					for(int j = 0; j<eco[i].length; j++) {
						if(answer.equals(eco[i][j])) {
							
							temp += (eco[i][j]+"  ");
							sitvo.setSit_number(temp);
							eco[i][j] = "X";
							
							break a;
						}
					}
					
				}
				
				}
				System.out.println(sitvo.getSit_number());
				break;
		}
		
		
	}

}