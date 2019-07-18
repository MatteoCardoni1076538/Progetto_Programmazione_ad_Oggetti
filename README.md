# Progetto_Programmazione_ad_Oggetti


Il programma basa il suo funzionamento sull'analisi in termini di considerazioni statistiche di un file .csv, scaricato tramite URL fornito dalla consegna. I dati contenuti all'interno di questo file sono relativi ai Milioni di Euro (MEUR) ed al Prodotto Interno Lordo (GDP), suddivisi tra stati europei (28 totali) ed anni (18 totali).
Per comprendere le considerazioni successive sull'esecuzione del parsing dei dati presenti nel file, bisogna prima capire come è strutturato il file .csv.

La prima cosa che si riporta all'interno del file è l'elenco degli anni, dal 2000 al 2017. In realtà bisogna tenere in considerazione che le primissime stringhe che si scansionano non sono anni, ma sono parole che specificano il tipo di dato che si andrà a trovare successivamente. Chiaramente nel parsing, quest'ultima porzione va evitata, perchè non contiene informazioni concrete, ma solo utili per capire l'organizzazione dei dati dentro il file. 

L'elenco invece degli stati viene strutturato in questo modo: viene riportata nel file una prima stringa che contiene la sigla relativa al nome dello stato ("AT", "BE", "BG"...), e in successione i dati relativi ai MEUR o GDP. Ai fini del parsing è importante notare come in realtà la sigla sia contenuta dentro una stringa contenente, come sopra, informazioni sulla struttura e sul tipo dei dati nel file. Sarà scopo dell'elaborazione estrarre solo in contenuto che interessa.

Inoltre nel file i 28 stati si ripetono due volte. La prima riportano il valore dei MEUR, la seconda, sottostante, i GDP.

Sempre ai fini del parsing si è notato come anni e valori di MEUR e GDP fossero separati da una virgola. Le sigle degli stati, invece, sono inserite nel mezzo di altre parole, e sono comprese tra due punti e virgola.

Il programma viene avviato dalla classe Application che funge da Spring Boot Application.
Una della classi richiamate da Application è la classe Associate, che eredita la superclasse DataParseCollector. 
DataParseCollector implementa l’interfaccia Parse, che dichiara i metodi per il parsing dei dati, sfruttando il sovraccarico.

Questi metodi permettono di:<br/>
•	effettuare il parsing del CSV ottenendo ogni riga come stringa e salvandole in un ArrayList.<br/>
•	effettuare il parsing della prima riga del CSV ottenedo un ArrayList di Integer, corrispondenti agli anni a cui sono relativi i dati.<br/>
•	ottiene la lista degli stati in un TreeSet di Stringhe. Si è scelto la struttura dati del TreeSet poichè permette di non salvare le ripetizioni che si incontrano.<br/>
•	ottenere la matrice contenente i MEUR corrispondenti a ciascun anno e ciascuno stato.<br/>
•	ottenere la matrice contenente i GDP corrispondenti a ciascun anno e ciascuno stato.<br/>

Nell'eseguire il parsing, si è chiaramente tenuto conto di quanto scritto sopra, evitando quindi informazioni "inutili".

N.B. Per matrice si intende una struttura dati del tipo ArrayList<ArrayList<Float>>. L'ArrayList più esterno si riferisce alle "righe", quindi seleziona in un certo senso tutti i valori di GDP o MEUR di un solo stato, per tutti gli anni. L'ArrayList più interno si riferisce ai singoli valori memorizzati, definiti dallo stato e dall'anno relativo. Si è scelta questa struttura dati perchè permette comodamente di associare ad un determinato stato e anno il relativo valore di MEUR o GDP.
  
La classe DataParseCollector contiene anche il metodo per il download del file CSV, il quale richiama quello definito nella classe DownloadCSV. Associate contiene invece il metodo matrixCreation, che crea un ArrayList di oggetti della classe Quadruplet, che salva gli attributi di nome dello stato e dell'anno a cui sono riferiti i dati e MEUR e GDP. Si è quindi utilizzata una struttura dati del tipo ArrayList<Quadruplet> per memorizzare tutti gli attributi, relativi ad una sola "cella". 
Associate contiene inoltre il metodo data_coverter, che mediante chiamata a matrixCreation, trasforma la matrice in un JSON e tramite API REST restituisce i dati (in formato JSON) all’indirizzo localhost:8080/association/data.
 
 
Per quanto riguarda il calcolo delle statistiche, si sono eseguiti tutti i calcoli nella classe statistics_calculus, sottoclasse di Associate. Questa classe restituisce, per ciascun anno e relativamente a MEUR e GDP:<br/>
•	avg<br/>
•	min<br/>
•	max<br/>
•	dev<br/>
•	std<br/>
•	sum<br/>
•	count<br/>

I dati vengono restituiti, come per la classe Quadruplet, in un ArrayList di oggetti della classe statistic_years, che contiene gli attributi elencati sopra.
statistic_calculus contiene anche il metodo per la restituizione delle suddette statistiche in formato JSON tramite API REST all’indirizzo localhost:8080/calculations/stats.

La consegna relativa all'individuazione della ripetizione delle stringhe contenenti le sigle degli stati viene implementata sempre dalla classe statistic_calculus, all'interno del metodo unique_state. La restituzione in formato JSON tramite API REST si trova all'indirizzo localhost:8080/calculations/stats_states.

La parte relativa al filtraggio dei dati è effettuata dalla classe filters.
filters contiene il metodo filter, che sfrutta il sovraccarico. Se vengono inseriti 3 parametri vengono selezionati i dati relativi alle medie sui MEUR interne al periodo contenuto tra due anni a scelta, e maggiori di una data soglia. 
Tutti i dati restituiti dal filtraggio sono poi memorizzati nelle variabili della classe filtered, che compone una cella di un ArrayList di oggetti di questo tipo, utilizzato poi per la conversione in JSON e "stampa" all'indirizzo.
In questo caso i dati filtrati sono restituiti in formato JSON tramite API REST all’indirizzo:

localhost:8080/filter/meur/?first_year=$primo_anno_da_inserire$&second_year=$secondo_anno_da_inserire$&treshold=$soglia_media_da_inserire$

Tutti i valori inseriti nell'indirizzo soprastante (primo_anno_da_inserire, secondo_anno_da_inserire, soglia_media_da_inserire) sono tutti di tipo Integer, come specificato nei parametri di ingresso alla funzione, tramite @RequestParam().

Questo primo filtraggio è in grado di gestire, tramite la definizione delle eccezioni inserita all'interno delle classi rimanenti del package corrente, (start.filters) un inserimento non corretto dei due anni e della soglia della media.
Se viene inserito un anno inferiore a 2000 o maggiore di 2017, oppure una soglia negativa, all'indirizzo sopra indicato si troverà un messaggio di errore, che richiede l'inserimento corretto dei parametri.
Il codice è inoltre in grado di capire quale dei due anni inseriti è il più grande e far partire la scansione dei MEUR di conseguenza. Non è quindi necessario inserire in first_year un anno di "partenza", minore di second_year, il codice è in grado di riconoscerlo da solo.

Gli indicatori riportati sul JSON in output all'indirizzo sono:
•	states: stati ai quali ci si riferisce.<br/>
•	all: relativo agli stati, indica che ci si sta riferendo a tutti gli stati.<br/>
•	logical: indica l'operatore logico al quale ci si è riferiti nel filtraggio<br/>
•	$in: è l'operatore logico, indica che ci si sta riferendo ai valori all'interno di un certo anno<br/>
•	year: riporta di seguito l'anno a cui ci si riferisce.<br/>
•	value: MEUR in questo caso, riporta di seguito il valore relativo.<br/>
•	condition: indica l'operatore condizionale al quale ci si è riferiti nel filtraggio.<br/>
•	$gt: indica di seguito la soglia che si è selezionata nel richiamare l'indirizzo.<br/>


Se invece vengono inseriti 2 parametri vengono selezionati i valori delle medie dei GDP superiori a un primo valore soglia e contemporaneamente inferiori ad un secondo valore soglia, ricavando anche l'anno a cui si fa riferimento con medie comprese tra i valori inseriti.
In questo secondo caso i dati filtrati sono restituiti all’indirizzo:

localhost:8080/filter/gdp?first_treshold=$prima_soglia_inserita$&second_treshold=$seconda_soglia_inserita$

Tutti i valori inseriti nell'indirizzo soprastante (prima_soglia_inserita, seconda_soglia_inserita) sono tutti di tipo Integer, come specificato nei parametri di ingresso alla funzione, tramite @RequestParam().

Come quella descritta sopra, questa funzione è in grado di gestire eccezioni definite nelle rimanenti classi, in particolare riportare la notifica di errore se le soglie inserite sono negative.
Anche in questo caso non è necessario inserire prima e seconda soglia in maniera "ordinata": il programma è in grado di  capire in maniera autonoma quale dei due interi inseriti sia più grande, ed agire di conseguenza.

Gli indicatori riportati sul JSON in output all'indirizzo sono gli stessi che si sono definiti sopra, con in aggiunta:

•	value: medie di GDP in questo caso, riporta di seguito il valore relativo.<br/>
•	$bt: indica i due valori di soglia che si sono inseriti.<br/>

Il diagramma delle attività è il seguente:<br/>

![Activity_diagram](https://user-images.githubusercontent.com/52153086/61204222-ef5f3e80-a6ec-11e9-8c2c-3f235836ab20.png)

Il diagramma delle classi è il seguente:<br/> 

![Class_diagram](https://user-images.githubusercontent.com/52153086/61204405-6563a580-a6ed-11e9-93c7-ee9d7a00ae35.png)


