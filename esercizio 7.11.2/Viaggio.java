import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Viaggio{
	
	private String destinazione;
	private int min;
	private ArrayList<Persona> lista = new ArrayList<Persona>();
	private boolean annullato = false;
	
	final Lock l = new ReentrantLock();
	final Condition nC = l.newCondition();
	
	public Viaggio(String dest, int num){
		this.destinazione = dest;
		this.min = num;
	}
	
	public boolean prenota(Persona p) throws InterruptedException{
		l.lock();
		try{
			lista.add(p);
			while(!annullato && lista.size() < min){
				System.out.println("Attesa"+p.getNome());
				nC.await();
			}
			nC.signalAll();
			if(annullato) return false;
			else{
				if(lista.size() < min) return false; 
				else return true;
			}
		}
		finally{ l.unlock(); }
	}
	
	public void chiudi(){
		l.lock();
		try{
		System.out.println("PRENOTAZIONI CHIUSE");
		if(lista.size() >= min){
			System.out.print("VIAGGIO CONFERMATO\n");
			System.out.print("LISTA VIAGGIATORI:\n");
			for(int i = 0; i<lista.size(); i++){ 
				System.out.println(lista.get(i).getNome()); lista.get(i).interrupt(); // interrompe i thread associati alle persone in lista
			}
		}
		else{
			for(int i = 0; i<lista.size(); i++){ lista.get(i).interrupt(); annullato = true; }
			System.out.print("VIAGGIO ANNULLATO");
		}
		}
		finally{ l.unlock(); }
	}
}