import java.lang.Math;

public class Genitore extends Membro_Famiglia{
	
	private int numFigli = 0;
	
	public Genitore(Registro_redditi r, int nf){
		super(r);
		numFigli = nf;
	}
	
	public void run(){
		try{
		int redditoGenitore = (int)(Math.random()*2000);
		System.out.println("redditoGenitore: " + redditoGenitore + "\n");
		synchronized(registro){
			while(registro.getCurr() < numFigli) registro.wait();
			if(registro.getBonus()){ 
				registro.add(redditoGenitore - ((registro.totale()*5)/100)); 
				registro.setBonus(false); 
			}
			else registro.add(redditoGenitore);
		}}
		catch(InterruptedException e){}
	}
}