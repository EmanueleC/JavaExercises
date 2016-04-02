import java.lang.Math;

public class Figlio extends Membro_Famiglia{

	public Figlio(Registro_redditi r){
		super(r);
	}

	public void run(){
		int redditoFiglio = (int)(Math.random()*1000);
		System.out.println("redditoFiglio: " + redditoFiglio + "\n");
		synchronized(registro){
			registro.add(redditoFiglio);
			registro.notifyAll();
		}
	}
}