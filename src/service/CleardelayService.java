package service;

import java.util.Scanner;

public class CleardelayService {
	
	public void Clear(){
		for(int i = 0 ; i < 40 ; i++) {
			System.out.println();
		}
	}
	
	public void delay(int a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void pause() {
		System.out.println("계속하시려면 엔터키를 눌러주세요..");
		Scanner sc =  new Scanner(System.in);
		String a = sc.nextLine();
	}

}
