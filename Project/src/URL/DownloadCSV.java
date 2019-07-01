package URL;

import java.io.*;
import java.net.*;

public class DownloadCSV {
	private String link;
	private File out;
	
	public DownloadCSV(String link, File out) {
		this.link = link;
		this.out = out;
	}
	
	public void FileCreation() {
		try {
			URL url = new URL(link); 
			//nuovo elemento di tipo URL
			
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			//classe astratta del tipo HttpURLConnection che crea un oggetto chiamato http, a partire da 
			//quanto salvato nella variabile url (il link vero e proprio).
			//E' questa la parte che serve a creare il collegamento all'URL ed il download.
			
			//PARTE DEDICATA SOLAMENTE A VISUALIZZARE LA PERCENTUALE DI DOWNLOAD DEL FILE.
			
			double fileSize = (http).getContentLengthLong();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(this.out);
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
