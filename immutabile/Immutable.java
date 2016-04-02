import java.util.ArrayList;

public class Immutable {

    private final ArrayList<Integer> list;
    public Immutable(ArrayList<Integer> list) {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		// copia della lista in input 
        for(int i = 0; i < list.size(); i++){
			copy.add(list.get(i)); 
		}
		this.list = copy;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder("");
        for (int i = 0; i < list.size(); i++)
            aux.append(list.get(i)).append(" ");
        return aux.toString();
    }

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        Immutable imm = new Immutable(list);
        System.out.println(imm.toString()); // stampa 1 2 3 4 
        System.out.println("----------------"); 
        list.add(5);
        System.out.println(imm.toString()); // stampa ancora 1 2 3 4 
    }
}