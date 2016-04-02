public class Registro_redditi{

	private int[] redditi;
	private int curr = 0;
	private static boolean bonus = true;

	// costruttore
	public Registro_redditi(int dim_famiglia){
		redditi = new int[dim_famiglia];
	}
	
	public int totale(){
		int tot = 0;
		for(int i = 0; i < curr; i++){
			tot = tot + redditi[i];
		}
		return tot;
	}
	
	public int riassunto(){
		int somma = 0;
		for(int i = 0; i < redditi.length; i++){
			somma = somma + redditi[i];
		}
		return somma;
	}
	
	public synchronized void add(int r){
		redditi[curr] = r;
		curr++;
	}
	
	public synchronized int getCurr(){
		return curr;
	}
	
	public synchronized boolean getBonus(){
		return bonus;
	}
	
	public synchronized void setBonus(boolean b){
		bonus = b;
	}
}