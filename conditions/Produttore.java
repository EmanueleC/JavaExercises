import java.util.concurrent.locks.*;

class Produttore extends Thread{
	BoundedBuffer b;
	Produttore(BoundedBuffer buff, String s){ super(s); b = buff; }
	public void run(){
		int i = 0;
		while(i < 200){
			b.l.lock();
			try{
				b.put("Pippo");
				System.out.println("messo numero" + i);
				i++;
			} catch(InterruptedException e){}
			finally{ b.l.unlock(); }
		}
	}
}