package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.AirplaneDao;
import data.Database;
import vo.AirplaneVO;
import vo.AirportVO;
import vo.CountryVO;
import vo.SitVO;

public class Sitservice {
	Database database = Database.getInstance();

	AirplaneDao airplanedao = new AirplaneDao();
	AirplaneVO airplanevo = new AirplaneVO();

	CleardelayService cds = new CleardelayService();

	Scanner sc = new Scanner(System.in);

	String[][] first = new String[4][3];
	String[][] business = new String[15][3];
	String[][] eco = new String[25][9];

	String[] date = new String[100];
	String[] time = new String[100];
	String[] sitclass = new String[100];
	String[] sitnum = new String[100];

	public void getticket() { //infolist 는 예약되어있는 티켓 정보를 저장한 array
		for (int i = 0; i < airplanedao.infoList().size(); i++) {
			AirplaneVO exairplane = airplanedao.infoList().get(i);
			date[i] = exairplane.getDate();
			time[i] = exairplane.getTime();
			sitclass[i] = exairplane.getSitclass();
			sitnum[i] = exairplane.getSitnum();
		}
	}

	//컴퓨터가 사용자가 예약한 날짜를 확인해서 X표시를 입력
	public void userchoi(String userdate, String usertime, String usersitclass) {
		for(int i = 0 ; i < airplanedao.infoList().size(); i++) {
			AirplaneVO exairplane = airplanedao.infoList().get(i);
			if(date[i].equals(userdate)) {				
				if(time[i].equals(usertime)) {					
					if(sitclass[i].equals(usersitclass)) {						
						if(usersitclass.equals("First")) {
							for(int k = 0 ; k < first.length ; k++) {
								for(int z = 0 ; z < first[k].length ; z++) {
									if(sitnum[i].equals(first[k][z])) {
										first[k][z] = " X ";
										break;
									}
								}
							}
						}else if(usersitclass.equals("Business")) {							
							for(int k = 0 ; k < business.length ; k++) {
								for(int z = 0 ; z < business[k].length ; z++) {									
									if(sitnum[i].equals(business[k][z])) {
										if (k < 9) {
											business[k][z] = "X ";
										} else {
											business[k][z] = " X ";
										}										
										break;
									}
								}
							}
						}else if(usersitclass.equals("Economy")){
							for(int k = 0 ; k < eco.length ; k++) {
								for(int z = 0 ; z < eco[k].length ; z++) {
									if(sitnum[i].equals(eco[k][z])) {
										if (k < 9) {
											eco[k][z] = "X ";
										} else {
											eco[k][z] = " X ";
										}
									}
								}
							}														
						}
					}				
				}
			}			
		}
	}

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

	public void pomat() { // 배열 초기화
		for (int i = 0; i < date.length; i++) {
			date[i] = " "; //티켓에 있는 정보를 담는 배열변수
			time[i] = " ";
			sitclass[i] = " ";
			sitnum[i] = " ";
		}
			//좌석 번호 저장해줌 (보여줄려고)
		char alpha;
		for (int i = 0; i < first.length; i++) {
			alpha = 'A';
			for (int j = 0; j < first[i].length; j++) {
				first[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}
		}

		// 비즈니스
		for (int i = 0; i < business.length; i++) {
			alpha = 'A';
			for (int j = 0; j < business[i].length; j++) {
				business[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}
		}

		// 이코노미
		for (int i = 0; i < eco.length; i++) {
			alpha = 'A';
			for (int j = 0; j < eco[i].length; j++) {
				eco[i][j] = Integer.toString(i + 1) + alpha;
				alpha = (char) (alpha + 1);
			}
		}
	}

	public String start1(int sitnum, boolean error) {
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
			System.out.println("-------------------------------------------");
			if(error)
				System.out.println("[System] 선택할 수 없는 좌석입니다.");

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer = sc.nextLine();
			
			a: for (int i = 0; i < first.length; i++) {
				for (int j = 0; j < first[i].length; j++) {
					if (answer.equals(first[i][j])) {
						temp += (first[i][j]);

						first[i][j] = " X ";
						boolean check = true;
						break a;
						
					}else {
						if(i==first.length-1&&j==first[0].length-1) {
							System.out.println("잘못입력하셨습니다.");
							
							break a;
						}
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
			System.out.println("-------------------------------------------");
			if(error)
				System.out.println("[System] 선택할 수 없는 좌석입니다.");

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer2 = sc.nextLine();

			a: for (int i = 0; i < business.length; i++) {
				for (int j = 0; j < business[i].length; j++) {
					if (answer2.equals(business[i][j])) {

						temp += (business[i][j]);

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
			System.out.println("-------------------------------------------");
			if(error)
				System.out.println("[System] 선택할 수 없는 좌석입니다.");

			System.out.print("원하시는 좌석을 입력해주세요\n>> ");
			String answer3 = sc.nextLine();

			a: for (int i = 0; i < eco.length; i++) {
				for (int j = 0; j < eco[i].length; j++) {
					if (answer3.equals(eco[i][j])) {
						temp += (eco[i][j]);

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