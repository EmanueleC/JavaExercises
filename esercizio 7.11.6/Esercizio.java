public class Esercizio{
	
	public static void main(String arg[]){
		
		Registro_redditi r = new Registro_redditi(6);
		
		Figlio f1 = new Figlio(r);
		Figlio f2 = new Figlio(r);
		Figlio f3 = new Figlio(r);
		Figlio f4 = new Figlio(r);
		
		Genitore g1 = new Genitore(r,4);
		Genitore g2 = new Genitore(r,4);
		
		f1.start(); f2.start();
		f3.start(); f4.start();
		g1.start(); g2.start();
		
		try{ g1.join(); g2.join(); }
		catch(InterruptedException e){}
		
		int totale = r.riassunto();
		
		System.out.println("reddito totale famiglia: " + totale);
	}
	
}