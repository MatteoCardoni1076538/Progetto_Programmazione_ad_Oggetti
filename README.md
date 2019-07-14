# Progetto_Programmazione_ad_Oggetti
Progetto di Matteo Cardoni e Saverio Pellegrini


Il programma prende in ingresso un CSV riguardante i dati di milioni di euro (MEUR) e prodotto interno lordo (GDP) relativo ai paesi dell’unione europea con frequenza annuale.
I dati vanno dall’anno 2000 all’anno 2017.

Il programma viene avviato dalla classe Application che funge da Sping Boot Applicaiton.
Una della classi richiamate da Application è la classe Associate, che eredita la superclasse DataParseCollector. 
DataParseCollector implementa l’interfaccia Parse, che dichiara i metodi per il parsing dei dati.

Questi metodi permettono di:
•	effettuare il parsing del CSV ottenendo ogni riga come stringa e salvandole in un ArrayList
•	effettuare il parsing della prima riga del CSV ottenedo un ArrayList di Integer, corrispondenti agli anni a cui sono relativi i dati
•	ottiene la lista degli stati in un TreeSet di Stringhe
•	ottenere la matrice contenente i MEUR corrispondenti a ciascun anno e ciascuno stato
•	ottenere la matrice contenente i GDP corrispondenti a ciascun anno e ciascuno stato

La sottoclasse Associate contiene il metodo per il download del file CSV. Associate contiene anche il metodo matrixCreation, che crea un ArrayList di oggetti della classe Quadruplet, che contiene gli attributi di nome del Paese, anno a cui sono riferiti i dati e MEUR e GDP relativi all’anno in questione. 
Associate contiene inoltre il metodo data_coverter, che mediante chiama matrixCreation, trasforma la matrice in un JSON e tramite API REST restituisce i dati (in formato JSON) all’indirizzo /association/data.

Le statistiche relative ai dati sono effettuate nella classe statistics_calculus, sottoclasse di Associate. Questa classe restituisce, per ciascun anno e relativamente a MEUR e GDP:
•	avg 
•	min
•	max
•	dev
•	std
•	sum
•	count
I dati vengono restituiti in un ArrayList di oggetti della classe statistic_years, che contiene gli attributi elencati sopra.
statistic_calculus contiene anche il metodo per la restituizione delle suddette statistiche in formato JSON tramite API REST all’indirizzo /calculations/stats.

La parte relativa al filtro dei dati è effettuata dalla classe filters.
filters contiene il metodo filter, che sfrutta il sovraccarico. Se vengono inseriti 3 parametri vengono selezionati i dati dei MEUR realivi al periodo contenuto tra due anni a scelta e maggiori di un dato soglia. 
In questo caso i dati filtrati sono restituiti in formato JSON tramite API REST all’indirizzo /filter/meur.

Se invece vengono inseriti 2 parametri vengono selezionati i valori dei GDP superiori a un primo valore soglia e contemporaneamente inferiori ad un secondo valore soglia.
In questo secondo caso i dati filtrati sono restituiti all’indirizzo /filter/gdp.
