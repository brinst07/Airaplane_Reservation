package service;

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

}
