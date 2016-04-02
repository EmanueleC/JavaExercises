abstract class Membro_Famiglia extends Thread{
	
	protected Registro_redditi registro;
	
	public Membro_Famiglia(Registro_redditi r){
		registro = r;
	}
	
	abstract public void run();
}