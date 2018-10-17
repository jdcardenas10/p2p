package clienteP2P;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.BasicConfigurator;

//import org.apache.log4j.BasicConfigurator;

import com.turn.ttorrent.common.Torrent;
import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class ServidorP2P {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException, URISyntaxException {
		
		BasicConfigurator.configure();
		
		String fileLarge="testl.wmv";
		String fileMedium="testm.wmv";
		
		String torrentLarge="testl.torrent";
		String torrentMedium="testm.torrent";
		
		String rutaTorrent="./torrent";
		String rutaSalida="./salida";
		
		Tracker tracker=new Tracker(InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress()));
		
		File torrentFileL=new File(rutaTorrent+"/"+torrentLarge);
		File torrentFileM=new File(rutaTorrent+"/"+torrentMedium);
		
		if(!torrentFileL.exists()){
			System.out.println( "Creando el torrent para el archivo grande" );
			Torrent torrent = Torrent.create(new File(rutaSalida+"/"+fileLarge), 
					tracker.getAnnounceUrl().toURI(), "yo");
			FileOutputStream fos = new FileOutputStream(rutaTorrent+"/"+torrentLarge);
            torrent.save( fos );            
            fos.close();
		}
		
		if(!torrentFileM.exists()){
			System.out.println( "Creando el torrent para el archivo mediano" );
			Torrent torrent = Torrent.create(new File(rutaSalida+"/"+fileMedium),
					tracker.getAnnounceUrl().toURI(), "yo");
			FileOutputStream fos = new FileOutputStream(rutaTorrent+"/"+torrentMedium);
            torrent.save( fos );            
            fos.close();
		}
		
		
		
		FilenameFilter filter = new FilenameFilter() {
			  @Override
			  public boolean accept(File dir, String name) {
			    return name.endsWith(".torrent");
			  }
			};

			for (File f : new File(rutaTorrent).listFiles(filter)) {
			  tracker.announce(TrackedTorrent.load(f));
			}

			tracker.start();						
	}
}
