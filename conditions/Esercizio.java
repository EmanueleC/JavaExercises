import java.util.concurrent.locks.*;

class Esercizio{
	public static void main(String args[]){
		BoundedBuffer b = new BoundedBuffer();
		Produttore p = new Produttore(b, "Produttore");
		Consumatore c = new Consumatore(b, "Consumatore");
		p.start(); c.start();
	}
}