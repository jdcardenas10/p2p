package clienteP2P;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
//import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.BasicConfigurator;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class ClienteP2P {

	public static void main(String[] args) throws Exception{
		
		BasicConfigurator.configure();
		
		String torrentLarge="./torrent/testl.torrent";
		String torrentMedium="./torrent/testm.torrent";
		String rutaEntrada="./entrada";
		
		//System.out.println("Cuantos clientes va a haber en la prueba ?");
		//int prueba=-1;
		
//			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//			String entrada=br.readLine();
		
		
			int prueba=Integer.parseInt(args[0]);
		
		//System.out.println("Precione L o M para el torrent grande o pequeño");
		String torrent=null;
		
		
		//while(torrent==null)
		//{	
			String entrada1=args[1];
		
		if(entrada1.equalsIgnoreCase("l")){
			torrent=torrentLarge;
		}
		else if(entrada1.equalsIgnoreCase("m")){
			torrent=torrentMedium;
		}
		else {
			System.out.println("Dato no valido");
		}
		//System.out.println("¿cual maquina virtual cliente?");
		
		String ruta="172.24.101.";	
		String end=args[2];
		
		ruta=ruta+end;


		Client cliente=new Client(InetAddress.getByName(ruta), 
				SharedTorrent.fromFile(new File(torrent),new File(rutaEntrada)));
		
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		long t1=System.currentTimeMillis();
		
		cliente.share();
		
		while(!cliente.isSeed()){}
		long t2=System.currentTimeMillis();
		
		Double tiempo=(double) ((t2-t1)/1000);
		
		File file = new File("./log.txt"); 
		FileWriter salida = new FileWriter(file.getAbsoluteFile(), true);
		
		salida.write("\n" +"Prueba con "+prueba+" clientes y torrent "+ torrent);
		salida.write("\n" +timeLog);
		salida.write("\n" +tiempo);
		salida.close();
		
		
		cliente.waitForCompletion();
		
	}
}
