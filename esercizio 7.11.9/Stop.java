import java.util.LinkedList;

public class Stop extends Thread{
	// rappresenta una corsia interrotta
	private LinkedList<Automobile> coda;
	private Passaggio passaggio;
	public Stop(Passaggio p){ coda = new LinkedList<Automobile>(); passaggio = p; }
	public synchronized void add(Automobile a){
		coda.add(a); notify();
	}
	public synchronized Automobile remove(){
		return coda.poll();
	}
	public void run(){
		try{
			while(true){
				synchronized(this){
					while(coda.isEmpty()) wait();
				}
				synchronized(passaggio){
				Automobile a = coda.remove(); 
				System.out.println("Transito di " + a.num);
			}}
		}
		catch(InterruptedException e){}
	}
}