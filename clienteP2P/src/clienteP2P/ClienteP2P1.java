package clienteP2P;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;

import org.apache.log4j.BasicConfigurator;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class ClienteP2P1 {

public static void main(String[] args) throws Exception{
		
		BasicConfigurator.configure();
		
		String torrentLarge="/Users/josedanielcardenasrincon/Desktop/torrent2/testl.torrent";
		String torrentMedium="/Users/josedanielcardenasrincon/Desktop/torrent2/testm.torrent";
		String rutaEntrada="/Users/josedanielcardenasrincon/Desktop/entrada";
		
		System.out.println("Precione l o m para torrent grande o pequeño");
		String torrent=null;
		
		while(torrent==null)
		{	
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String entrada=br.readLine();
		
		if(entrada.equalsIgnoreCase("l")){
			torrent=torrentLarge;
		}
		else if(entrada.equalsIgnoreCase("m")){
			torrent=torrentMedium;
		}
		else {
			System.out.println("Precione l o m para torrent grande o pequeño");
			}
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		
		Client cliente=new Client(InetAddress.getLocalHost(), 
				SharedTorrent.fromFile(new File(torrent),new File(rutaEntrada)));
		
		cliente.download();
		
		cliente.waitForCompletion();
		
	}
}
