public class Persona extends Thread{
	private String nome;
	private Viaggio viaggio;
	public Persona(String n, Viaggio v){
		this.nome = n;
		this.viaggio = v;
	}
	public void run(){
		viaggio.l.lock();
		try{
			if(viaggio.prenota(this)){
				System.out.println("Confermato" + nome);
			}
		}
		catch(InterruptedException e){}
		finally{ viaggio.l.unlock(); }
	}
	public String getNome(){
		return nome;
	}
}