package clienteP2P;

import java.io.File;

public class Delete {

	public static void main(String[] args) {
		
		File m=new File("./entrada/testl.wmv");
		File l=new File("./entrada/testm.wmv");
		
		if(m.delete()){
			System.out.println("Se elimino el archivo grande");
		}
		
		if(l.delete()){
			System.out.println("Se eliminino el archivo mediano");
		}
	}
}
