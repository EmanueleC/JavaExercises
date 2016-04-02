
public class TavolaCalda {
	private int prox_primo_da_servire = 0;
	private int prox_secondo_da_servire = 0;
	public int n_conti_terminati = 0;
	private Object primo = new Object();
	private Object secondo = new Object();
	private Object cassa = new Object();
	
	void prendi_primo(int i) {
		try {
			synchronized(primo) {
				while(prox_primo_da_servire != i) {
					primo.wait();
				}
				System.out.println("Servito primo piatto al numero " + i);
				prox_primo_da_servire++;
				primo.notifyAll();
			}
		}
		catch(InterruptedException e) { } 
	}
	
	void prendi_secondo(int i) {
		try {
			synchronized(secondo) {
				while(prox_secondo_da_servire != i) {
					secondo.wait();
				}
				System.out.println("Servito secondo piatto al numero " + i);
				prox_secondo_da_servire++;
				secondo.notifyAll();
			}
		}
		catch(InterruptedException e) { }
	}
	
	void paga(Cliente c){
		synchronized(cassa){
			c.pagamento();
			n_conti_terminati++;
			cassa.notifyAll();
		}
	}
	
	private void generaClienti(final int n) {
		Thread t = new Thread() { // classe anonima
			public void run() {
				for(int i = 0; i < n; i++) {
					Cliente c = new Cliente(TavolaCalda.this);
					c.start();
				}
			}
		};
		t.start();
		return;
	}
	
	private void attendiClienti(final int n) { 
		try{
			synchronized(cassa){
				while(n_conti_terminati != n) cassa.wait();
				System.out.println("Finito");
			}
		}
		catch(InterruptedException e) { }
	} 

	public static void main(String[] args) {
		TavolaCalda m = new TavolaCalda();
		m.generaClienti(100);
		m.attendiClienti(100);
	}
}
