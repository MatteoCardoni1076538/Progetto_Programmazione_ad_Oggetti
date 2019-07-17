package start;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadCSV {
	
	public String link; //Contiene il link all'url dove è salvato il CSV da decodificare. (URL ricavato dalla decodifica del JSON).
	
	public File out; //File di output che sarà salvato nel pc, contenente il file di testo scaricato.
	
	
	//Costruttore della classe Download.
	
	public DownloadCSV(String link, File out) {
		this.link = link;
		this.out = out;
	}

	
	public void method() {
		
		try {
			URL url = new URL(link); 
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			FileOutputStream fos = new FileOutputStream(this.out);
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
			}
			bout.close();
			in.close();
		}

		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
		

	
}

