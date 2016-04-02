public class T1 extends Thread{
	int i = 0;
	T2 t2;
	T1(T2 t2){
		this.t2 = t2;
	}
	public void run(){
		while(i<1000){
		try{
			synchronized(t2){
				i++;
				if(i == 200){ t2.sospendi = true; }
				if(i == 600){
					t2.sospendi = false;
					t2.notify();
				}
			}
			Thread.sleep(20);
		}
		catch(InterruptedException e){}
		}
	}
}