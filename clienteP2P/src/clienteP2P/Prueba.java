package clienteP2P;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import com.turn.ttorrent.tracker.Tracker;

public class Prueba {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Tracker tracker=new Tracker(InetAddress.getLocalHost());
		

		System.out.println(tracker.getAnnounceUrl().toURI());
		System.out.println(InetAddress.getLocalHost());
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		System.out.println(Inet6Address.getLocalHost().getHostAddress());
		System.out.println(new URI("http://air-de-jose-2.uniandes.edu.co:6969/announce"));
	}
}
