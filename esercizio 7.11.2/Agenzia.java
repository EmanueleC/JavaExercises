public class Agenzia{
	public static void main(String args[]){
		Viaggio v = new Viaggio("Namibia",4);
		Persona p1 = new Persona("Alice", v);
		Persona p2 = new Persona("Bob", v);
		Persona p3 = new Persona("Carl", v);
		Persona p4 = new Persona("Doug", v);
		Persona p5 = new Persona("Eric", v);
		Persona p6 = new Persona("Frank", v);
		
		p1.start(); p2.start(); p3.start(); 
		p4.start(); p5.start(); p6.start();
		
		try{ Thread.sleep(200); } 
		catch(InterruptedException e){}
		
		v.chiudi();
	}
}