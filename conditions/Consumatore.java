import java.util.concurrent.locks.*;

class Consumatore extends Thread{
	BoundedBuffer b;
	Consumatore(BoundedBuffer buff, String s){ super(s); b = buff; }
	public void run(){
		int i = 0;
		while(i < 200){
			b.l.lock();
			try{
				Object o = b.take();
				System.out.println("preso numero" + o + i);
				i++;
			} catch(InterruptedException e){}
			finally{ b.l.unlock(); }
		}
	}
}