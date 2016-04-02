public class Cliente extends Thread {
	private TavolaCalda mensa;
	private static int nC = 0;
	private int numero;
	
	Cliente(TavolaCalda m){
		mensa = m;
		numero = nC++;
	}
	
	public void pagamento(){
		System.out.println(numero + " ha pagato");
	}
	
	public void run(){
		mensa.prendi_primo(numero);
		mensa.prendi_secondo(numero);
		mensa.paga(this);
	}

}
