public class T2 extends Thread{
	boolean sospendi = false;
	public void run(){
		while(true){
		try{
			synchronized(this){
					while(sospendi == true){ wait(); } 
					System.out.println("Topolino");
			}
			Thread.sleep(150);
		}
		catch(InterruptedException e){}
		}
	}
}