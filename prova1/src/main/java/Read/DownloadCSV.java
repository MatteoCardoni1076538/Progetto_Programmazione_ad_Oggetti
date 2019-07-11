package Read;

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
		
		try { //Si inserisce un try per evitare di incorrere in errore
			
			URL url = new URL(link); 
			//nuova classe di tipo URL
			
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			//classe astratta del tipo HttpURLConnection che crea un oggetto chiamato http, a partire da 
			//quanto salvato nella variabile url (il link vero e proprio).
			//E' questa la parte che serve a creare il collegamento all'URL ed il download.
			FileOutputStream fos = new FileOutputStream(this.out);
			
			//PARTE DEDICATA SOLAMENTE A VISUALIZZARE LA PERCENTUALE DI DOWNLOAD DEL FILE.
			
			double fileSize = (http).getContentLengthLong();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			double downloaded = 0.00;
			int read = 0;
			double percentDownloaded = 0.00;
			
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
				downloaded +=read;
				percentDownloaded = (downloaded*100)/fileSize;
				String percent = String.format("%.4f", percentDownloaded);
				System.out.println("Downloaded -" + percent + "% of a file");
				
			}
			bout.close();
			in.close();
			System.out.println("Download Complete");
			
			//FINE DELLA PARTE DEDICATA ALLA VISUALIZZAZIONE DEL DOWNLOAD.
		}
		//gestione delle eccezioni.
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
		

	
}

