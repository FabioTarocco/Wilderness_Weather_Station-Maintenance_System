

# Wilderness Weather Stations - Maintenance System
Un sistema in grado di gestire contemporaneamente centinaia di dispositivi per il rilevamento di dati relativi a fattori ambientali dotato di sistema di acquisizione/manipolazione dei dati e un sistema di manutenzione delle stazioni metereologiche.  
  
Il compito che è stato affidato al nostro gruppo di lavoro è quello di implementare il sistema di manutenzione e monitoraggio delle condizioni delle weather station.
Era richiesta l'implementazione di un'applicazione che permettesse ai vari tecnici, che lavorano alla manutenzione delle weather station, di avere una visualizzazione completa di tutta la rete degli impianti installati sul territorio.

Le weather station utilizzate dal Laboratorio sono [Ceptu Weather Station](https://ceptu.com/technologies/weather-stations/) che permettono la misurazione di:
- Precipitazioni
- Temperatura dell'aria
- Umidità relativa dell'aria
- Pressione dell'aria
- Temperatura del suolo
- Velocità del vento
- Intensità raggi UV
- Irraggiamento

Di queste solo alcune sono utilizzate per il monitoraggio.

<p align="center">
  <img width="460" height="300" src="https://user-images.githubusercontent.com/54395661/130572962-7ec7d9ef-6cff-45d6-8d3f-2cd890bcc56f.PNG">
</p>
<p align = "center">
  Modello di weather station installata
</p>


Le operazioni che solitamente venivano fatte erano quelle di installazione, monitoraggio e distacco, di queste tre, le prime due sono state mantenute con integrazione e dettagli, poi è stata richiesta l'aggiunta della possibilità di disabilitazione, riattivazione e eliminazione di una delle stazioni. 

Nel caso in cui una delle WS avesse subito un danno, era semplicemente segnalata come *guasto*, ci è stato quindi chiesta la possibilità di introdurre una schermata che visualizzasse quali sensori hanno subito un guasto, oltre che alla normale visualizzazione delle misurazioni dei dati istantanee. La fase di *manutenzione sul sito* avveniva su un sistema separato, è richiesto quindi che il sistema permetta di mandare una richiesta di *manutenzione sul sito*/*manutenzione straordinaria* direttamente dallo stesso applicativo in modo tale da ridurre le tempistiche.

Il nuovo sistema è stato implementato da zero seguendo le linee guida fornite dai tecnici adottando una metodologia mista tra Code&Fix e Agile:
- Code&Fix per la costruzione dello scheletro del programma.

- Agile per l'implementazione di tutte le funzionalità.




  
## Requisiti  
  
 - Il sistema deve esser dotato di multiple intefaccie per la visualizzazione dello stato delle Weather Station della rete.   
 - Interfacce principali: *main_view, detail_view,  integration_view, disable_ws_view, delete_ws_view, reactivate_ws_view* e *report_view*.  
 - La main view deve visualizzare una tabella con all'interno tutte le Weather Station  (WS) della rete.  
 - La tabella delle WS deve visualizzare per ogni ws: Codice_ws, Area_geografica, data_installazione, Status_ws e operazioni che possono essere eseuite su di essa.  
 - Lo stato della WS ( Status_WS) è rappresentato mediante una codifica a colori che verrà specificato in seguito. La codifica di Status_WS comprende a sua volta la codifica dello stato dei sensori (Sensor_status) e delle batterie (Battery_status)  
- Ogni WS è collegata sia al sistema di manutenzione sia al sistema di acquisizione dati (implementato da un altro gruppo di lavoro, questa parte è stata simulata)  
### Main_View:  
- Dalla main_view si deve avere la possibilità di inserire una nuova WS nella rete mediante apposito pulsante: Add Weaher Station.  
- Dalla main_view si ha la possibilità di visualizzare nel dettaglio la WS selezionata.  
- Dalla main_view si deve avere la possibilità di rimuovere una WS dal sistema.  
- Dalla main_view si deve avere la possibilità di disabilitare una WS nel sistema.  
- Dalla main_view si deve avere la possibilità di riattivare una WS nel sistema.  
- Dalla main_view si deve avere la possibilità di richiedere un intervento speciale ad una WS del sistema.  
- Dalla main_view si passa alla integration_view nel momento in cui si vuole inserire una nuova WS nella rete.  
### Integration_view:  
- Nella *integration_view*, il sistema al momento della richiesta di inserimento di una nuova WS deve fornire un codice univoco generato in base alla nazione dove questa verrà installata.  
- La data dell'installazione della WS viene inserita in modo automatico dal sistema.  
- Il sistema deve ricevere, come informazioni per completare la richiesta di installazione, la nazione, la posizione della WS e l'altitudine.  
- Deve essere effettuato un controllo nel caso di campi vuoti o se l'altitudine inserita non è nel range [-30mt, 8000mt]  
- Nel caso venga commesso uno degli errori precedenti il sistema deve rilevare l'errore e passare ad un'interfaccia di errore.
- Dall'*integration_view* completata l'installazione della WS oppure mediante apposito pulsate si deve tornare alla *main_view*.  
### Detail_view:  
- Dalla *main_view* cliccando sul codice della WS si passa alla *detail_view*.  
- Nella *detail_view* devo esser visualizzati i dati della WS relativi a:  
   - Sensor_status  
   - Battery_status  
   - Stato batterie  
   - Numero batteria in uso  
   - Temperatura/temperature  [°C]
   - Pressione/Pressure  [mBar] 
   - Irraggiamento/Sunshine [kW]
   - Quantità di pioggia/rainfall [mm/h]
   - Velocità del vento/wind speed   [Km/h]
   - Direzione del vento/wind direction  [punti cardinali]
   
- Inoltre nella detail_view deve esser visualizzato lo storico dei guasti e delle manutenzioni avvenute sulla WS.  
### Disable_vs_view:  
- Dalla *main_view* si deve aver la possibilità di disattivare una WS mediante un pulsante.  
- Nel momento della disattivazione deve venire richiesta la motivazione per l'operazione e da chi viene richiesta.
- Anche per la richiesta di disabilitazione deve esserci un controllo sui dati inseriti e conseguente schermata di errore in caso di problemi.
- Dalla *disable_view* si può tornare alla *main_view* mediante apposito tasto.  
### Reactivate_ws_view:  
- Dalla *main_view* si deve aver la possibilità di disattivare una WS mediante un pulsante.    
- Dalla *disable_view* si può tornare alla main_view mediante apposito tasto.  
### Report_view:  
- Dalla *detail_view* deve esser presente l'apposito tasto per richiedere un intervento speciale sul sito di installazione a causa di guasto HW o altro.  
- Dalla *detail_view* si passa alla *report_view* dove vengono richiesti i dati per effettuare l'intervento speciale.  
- I dati che devono esser richiesti all'operatore sono:  
   - Localizzazione del dispositivo  
   - Report del guasto  
   - Nome e cognome dell'operatore che effettua la segnalazione  
   - Note aggiuntive  
- Il sistema per la segnalazione deve fornire: codice WS e Data/ora segnalazione.
- Anche per la richiesta di manutenzione straordinaria deve esserci un controllo sui dati inseriti e conseguente schermata di errore in caso di problemi.
- Completata l'invio della segnalazione o premendo l'apposito pulsante si deve esser in grado di tornare alla *main_view*.  
### Delete_ws_view:  
- Dalla *main_view* mediante apposito pulsante deve essere possibile eliminare una WS dalla rete.  
- L'eliminazione deve avvenire solamente dopo una seconda conferma: delete_ws -> conferma -> Eliminazione ws.
- Anche da questa interfaccia, deve essere presente la possibilità di annullare l'operazione e tornare alla *main_view* mediante apposito pulsante.  

- Per tutte le schermate specifiche deve esserci un controllo e conseguente schermata di errore nel caso in cui non venga trovata la WS su cui è richiesta l'operazione.

## Scelte di Design
### Flag per gli stati di sensori e delle batterie:
- Per indicare che uno dei sensori ha subito un guasto o se è perfettamente funzionante si è deciso di introdurre 6 flag, una per ogni sensore, che simulano le rotture dei dispositivi. 
- Le flag in questione son chiamate: *nomeSensore_sens_status*.
- Se poste a True allora il dispositivo/sensore sta funzionando correttamente, altrimenti se a False, vuol dire che il dispostivo è guasto.

- Allo stesso modo sono state introdotte 3 flag per le 3 batterie: *b_1_status, b_2_status, b_3_status*.

### Simulazione misurazione e guasti sensori/batterie:
Avendo scelto di implementare la componente di manutenzione del sistema di monitoraggio, dovevamo dotarci di un metodo per simulare le misurazioni e i vari guasti che ogni dispositivo dovrebbe fornire/subire.

Abbiamo quindi optato per una serie di metodi chiamati *new_infos* ( ognuno relativo alla classe WS, Sensor o Battery) che si occupano della simulazione dei guasti e delle misurazioni, nel caso dei sensori.

Abbiamo prima una parte che mediante l'estrazione di un numero randomico tra 0 e 1, simula la rottura dei sensori/batterie seguendo delle probabilità di rottura, chiamate *failure_rate_nomeSensore/failure_rate_numeroBatteria*: se il numero estratto è maggiore del failure_rate allora lo lo status relativo al sensore/batteria viene messo a False, altrimenti è mantenuto a True.
Per quanto riguarda i sensori le varie soglie soglie sono:
- failure_rate_temperature = 0.98.
- failure_rate_pressure=0.98.
- failure_rate_sunshine=0.99.
- failure_rate_rainfall=0.95.
- failure_rate_windspeed=0.90.
- failure_rate_winddir=0.90.
- failure_rate_total=0.99.

Differenti soglie a seconda dell'esposizione a possibili guasti, supponendo che i sensori che misurano la velocità/direzione del vento sono i più esposti alle intemperie mentre i rimanenti sono interni al dispositivo.
Con l'aggiunta di un *failure_rate_total* che con una probabilità dell'1% causa la rottura della WS.

Mentre per le batterie:
- failure_rate_b1 = 0.985.
- failure_rate_b2 = 0.985.
- failure_rate_b3 = 0.75.

La terza batteria, essendo quella di *riserva* ha una durata minore rispetto alle altre due.


La parte successiva è quella che semplicemente genera delle misurazioni casuali aggiungendo/sottraendo un valore casuale alla misurazione precedente.
Per le fluttuazioni delle misurazioni in contesto standard ( dopo la prima misurazione ) abbiamo:
- temperature +- 1°C. 
- pressure +- 2.5 mBar.
- sunshine +- 1 kW.
- rainfall +- 0.25 mm/h di pioggia.
- windspeed +- 5 km/h.
- winddir presenta un'estrazione casuale di una WindDir.

Invece la prima misurazione avviene estraendo un valore casuale tra un intervallo specfico per ogni misurazione:
- temperature [-40,+40]
- pressure 1000 + [-2, +8]
- sunshine [0, +2]
- rainfall [0, +1]
- windspeed [0, +120];
- winddir presenta un'estrazione casuale di una WindDir

Per le batterie invece avviene solamente la prima parte di simulazione dei guasti.

### Codice colore:  
  Come prima accennato qui viene introdotta la codifica mediante l'enumerazione ColorCode degli status delle WS, dei sensori  
 e delle batterie  
   
 **Sensor Status**: i sensori si possono presentare nelle seguenti codifiche GREEN, YELLOW e BLACK.
 - GREEN: tutti e 6 i sensori sono funzionanti.
 - YELLOW: da 1 a 5 sensori non sono funzionanti.
 - BLACK: TUTTI i sensori non sono funzionanti.
 
 **Battery Status**: i sensori si possono presentare nelle seguenti codifiche GREEN, YELLOW, RED e BLACK. 
 - GREEN: Tutte le batterie sono OK e si sta utilizzando la 1.
 - YELLOW: Batteria 1 in FAIL, utilizzo della batteria 2.
 - RED: Batteria 1 e 2 in FAIL, utilizzo della batteria 3.
 - BLACK: TUTTE le batterie sono in FAIL.
 
 (FAIL = *status_b_numeroBatteria* == False)
 
 **WS Status** :  le WS possono presentarsi nelle seguenti codifiche GREEN, YELLOW, ORANGE, RED, BLACK e GRAY.
- GREEN: Tutto ok, sia batteria che sensori
- YELLOW: Batteria OK, sensori da 1 a 5 non funzionanti *oppure* Batteria 2 in uso, sensori OK
- ORANGE: Batteria 2 in uso, sensori da 1 a 5 non funzionanti *oppure* Batteria 3 in uso, sensori OK
- RED: Batteria 1 in uso, sensori tutti non funzionanti *oppure* Batteria 2 in uso, sensori tutti non funzionanti *oppure* Batteria 3 in uso, sensori tutti non funzionanti *oppure* Batteria 3 in uso, da 1 a 5 sensori non funzionanti
- BLACK: Nessuna batteria in uso, qualsiasi sensor_status
- GREY : WS Disattivata

Lo Status della WS può quindi essere riassunto in questo schema:

![WSStatus](https://user-images.githubusercontent.com/54395661/130207863-6f866c6e-cb8e-4c82-aef2-ec727a0273e3.PNG)



## Scenari
### **Scenario 1:** Visualizzazione da parte di un tecnico dei dettagli di una WS  
  
*Premesse iniziali:* Il sistema deve aver caricato tutte le WS presenti nel sistema e inserite nella tabella della main_view.
  
*Flusso delle operazioni* : Il sistema carica i dati di tutte le WS nella tabella dela main_view. Il tecnico dopo aver cercato la WS di cui vuole visualizzare i dettagli, preme sull'apposito tasto *detail* della riga della tabella corrispondete alla WS voluta e il sistema effettua il cambio di interfaccia da main_view a detail_view. In questa interfaccia vengono visualizzati dettagli aggiuntivi della WS e una volta terminato il controllo/visualizzazione di eventuali problemi, l'utente potrà tornare sulla schermata principale mediante l'apposito tasto della schermata detail_view.  
  
*Eventuali problemi*: Nella selezione della WS potrebbe non risultare più presente nel Database e quindi creare un'inconsistenza nella visualizzazione dei dati. Anche in questo caso verrà visualizzato un messaggio di errore nel quale si specifica che la WS non è più presente nel DB (*not_found*) della rete e non è possibile visualizzare i dati.     
  
*Stato del sistema dopo il completamento*: Il sistema deve aver effettuato il cambio dalla main_view alla detail_view e devono esser caricati in modo corretto i dati della WS insieme allo stato attuale degli strumenti. L'utente deve poter visualizzare la *detail_view*.  
  
### **Scenario 2:** Disattivazione rilevamento di una WS  
  
*Premesse iniziali:* Questa operazione viene eseguita a partire dalla corretta esecuzione come lo *scenario 1*. Le premesse quindi vengono riprese da quelle dello scenario precedente e in aggiunta si ha che il sistema deve aver caricato e mostrato i dati relativi a tutte le WS presenti nel sistema.
  
*Flusso delle operazioni* : Il tecnico deve andare nell'apposita sezione di disattivazione mediante il pulsante *Disable*. Il sistema passa alla schermata *Disable_ws_view* di disattivazione. Il tecnico deve ora inserire la motivazione per disabilitare la WS, il suo nome e cognome. Il tecnico puoi deve confermare l'operazione con l'apposito tasto "Disable". Il sistema inoltra la richiesta al db per effettuare il cambio di stato_ws a GREY e aggiorna lo storico delle operazioni effettuate su di essa. Il sistema poi ritorna alla *main_view*. Durante questa procedura il tecnico potrà comunque tornare alla *main_view* attraverso l'apposito pulsante.  
  
*Eventuali problemi*: Non è possibile caricare la schermata della disabilitazione della WS. Il sistema visualizza un messaggio di errore *not_found* quindi potrà tornare mediante il tasto *indietro* del browser alla *main_view*.   
Il sistema non elabora fino alla fine la richiesta di disabilitazione o la richiesta di intervento non viene correttamente inoltrata al db. Questo sorge nel momento in cui non vengono inseriti i campi richiesti: nome, cognome e motivazione. Quindi il tecnico sarà porato nella *dataError_view* dove verrà mostrato un messaggio di errore e mediante apposito tasto potrà tornare alla *main_view* 
  
*Stato del sistema dopo il completamento*: Il sistema deve aver effettuato il cambio fra *disable_ws_view* a *main_view*. Inoltre, i dati della segnalazione devono esser riportati all'interno delle informazioni contenute nella WS che ha subito la disattivazione.  
  
### **Scenario 3:** Inoltro richiesta intervento speciale su WS  
  
*Premesse iniziali:* Questa operazione viene eseguita a partire dalla corretta esecuzione come lo *scenario 1*. Le premesse quindi vengono riprese da quelle dello scenario precedente e in aggiunta si ha che il sistema deve aver caricato e mostrato i dati relativi a tutte le WS presenti nel sistema.   
  
*Flusso delle operazioni* : Il tecnico deve andare nell'apposita sezione di segnalazione mediante il pulsante *Request maintenance*. Il sistema deve quindi passare alla *report_view* e caricare la localizzazione e il codice della WS. Il tecnico deve ora inserire il report del guasto, il proprio nome e cognome, eventuali note aggiutive. Quando tutti i dati sono stati inseriti deve premere sull'apposito pulsante per inoltrare la richiesta. Con l'inoltro della richiesta il sistema inserisce la data e ora della richiesta. Il sistema aggiorna di dati della WS su cui è stato richiesto l'intervento speciale e poi ritorna alla *main_view*. Durante questa procedura il tecnico potrà comunque tornare alla *main_view* attraverso l'apposito pulsante.  
  
*Eventuali problemi*: Il caricamento dei dati della WS non va a buon fine. Il sistema visualizzerà un messaggio di errore e il tecnico verrà riportato nella schermata *not_found*.   
Il sistema non elabora fino alla fine la richiesta di intervento o la richiesta di intervento non viene correttamente inoltrato al db. Questo sorge nel momento in cui non vengono inseriti i campi richiesti: nome, cognome e motivazione. Le note sono opzionali, quindi non generano errore. Quindi il tecnico sarà porato nella *dataError_view* dove verrà mostrato un messaggio di errore e mediante apposito tasto potrà tornare alla *main_view* 
  
*Stato del sistema dopo il completamento*: Il sistema deve aver effettuato il cambio fra *report_view* e *main_view*. Inoltre, i dati della segnalazione devono esser riportati all'interno delle informazioni contenute nella WS che ha subito l'intervento.  
  
### **Scenario 4:** Aggiunta WS alla rete  
  
*Premesse iniziali:* Il sistema deve aver caricato tutte le WS presenti nel sistema e inserite nella tabella della *main_view*.
  
*Flusso delle operazioni* : Il tecnico deve andare nell'apposita sezione di aggiunta dispositivi mediante il pulsante *Add Weather Station*. Il sistema deve quindi passare alla *integration_view*. Il tecnico quindi inserisce i dati relativi alla zona geografica del sito di installazione della WS, l'altitudine e la localizzazione. Il tecnico deve poi premere il pulsate di *Send request* per inoltrare la richiesta di aggiunta della WS al sistema. Il sistema riceve le informazioni date dal tecnico e aggiunge un Codice univoco identificativo della WS, generato in base alla localizzazione e una sottostring generata casualmente. La nuova WS viene inserita del DB e viene effettuato un nuovo caricamento delle WS nella main_view. Il sistema quindi passa alla *main_view*.  
Durante questa procedura il tecnico potrà comunque tornare alla *main_view* attraverso l'apposito pulsante.  
  
*Eventuali problemi*:  Il sistema non elabora fino alla fine la richiesta di aggiunta o la nuova WS  non viene correttamente inoltrata al db. Questo sorge nel momento in cui non vengono inseriti i campi richiesti: nazione, posizione e altitudine, oppure se il valore dell'altitudine inserita non è nel range [-30 mt, +8000 mt]. Quindi il tecnico sarà porato nella *dataError_view* dove verrà mostrato un messaggio di errore e mediante apposito tasto potrà tornare alla *main_view*In questo caso iil tecnico verrà riportato alla *integration_view*.  
  
*Stato del sistema dopo il completamento*: Il sistema deve aver effettuato il cambio fra *integration_view* e *main_view*. Inoltre, il sistema deve effettuare un refresh delle WS che inizialmente vengono caricate in modo da visualizzare anche quella appena inserita.  
  
### **Scenario 5:** Rimozione WS dalla rete  
  
*Premesse iniziali:* Il sistema deve aver caricato tutte le WS presenti nel sistema e inserite nella tabella della *main_view*. Il tecnico deve aver acceduto al sistema di manutenzione.  
  
*Flusso delle operazioni* : Il tecnico deve premere il tasto *Delete*. Il sistema passerà alla schermata *delete_ws_view*, mostrerà un messaggio in cui viene nuovamente riporato il codice_ws per ulteriori verifiche. Il tecnico dovrà premere sull'apposito pulsate per proseguire con l'eliminazione della WS dalla rete.Il sistema quindi passa alla *main_view*.  
Durante questa procedura il tecnico potrà comunque tornare alla *main_view* attraverso l'apposito pulsante.  

*Eventuali problemi*: Il caricamento dei dati della WS non va a buon fine. Il sistema visualizzerà un messaggio di errore e il tecnico verrà riportato nella schermata *not_found*. Quindi potrà tornare alla schermata mediante il tanto *indietro* del browser di ricerca. 
  
*Stato del sistema dopo il completamento*: Il sistema deve aver effettuato l'eliminazione della WS deve effettuare un refresh delle WS che inizialmente vengono caricate in modo da visualizzare non visualizzare più quella eliminata. Al termine ci si troverà nella *main_view*.
  
  
## Diagrammi e schemi
### Diagramma dei casi d'uso (Use case diagram)
Realizzato mediante [StarUML](https://staruml.io/).

![Cattura](https://user-images.githubusercontent.com/54395661/130348046-4e2c902a-8b0c-42a1-bd99-7cc23a75fb9f.PNG)


### Diagramma delle classi (Class diagram)
La classe WS_Sensor presenta molti metodi e molti campi, quindi la visualizzazione del diagramma intero risulta difficile. Di seguito una prima versione del diagramma delle classi in formato ridotto. In entrambi i casi il diagramma delle classi è stato generato in modo automatico utilizzando il tool *Diagrams* di IntelliJ.

![Reduced_Class_Diagram](https://user-images.githubusercontent.com/54395661/130347331-2a0a7b9c-a7a3-43b4-8862-a90994b52331.png)

Poi la versione *estesa*:

![Extended_Class_Diagram](https://user-images.githubusercontent.com/54395661/130347344-7ff9674f-cf0a-4e4a-b20d-1b1d2d5456e5.png)

### Diagramma delle attività (Activity Diagram)

Per quanto riguarda le attività *Aggiungi WS, Disabilita WS* e *Richiedi Intervento WS* anche loro presentano la possibilità in ogni momento di tornare al menu principale e di annullare l'operazione. Questa operazione è stata omessa per rendere più chiaro lo schema, lasciando il flusso dell'operazione principale. In ogni caso l'operazione di ritorno equivale a quella utilizzata in *Riabilita WS, Elimina WS* e simile a *Visualizza Dettagli Ws*.

Realizzato mediante [StarUML](https://staruml.io/).

![ActivityDiagram1](https://user-images.githubusercontent.com/54395661/130351510-c1cda7c0-c06a-47f1-86a0-f7d02733a962.jpg)

### Diagramma di sequenza (Sequence Diagram)
Per quanto riguarda il diagramma di sequenza abbiamo optato per la visualizzazione delle attività che un tecnico può compiere in quanto non esiste una vera a propria esecuzione generale del sistema, ma tutto gira attorno alle attività che vengono effettuate.

Inoltre come già anticipato nel diagramma delle attività in ogni momento è sempre possibile tornare alla schermata principale mediante l'apposito tasto in ogni interfaccia secondaria.

**Aggiunta WS**:
![WSAdd](https://user-images.githubusercontent.com/54395661/130785648-0cc36769-7c4d-4bc0-8c01-63dfb0a2996d.jpeg)
**Eliminazione WS**:
![WSDelete](https://user-images.githubusercontent.com/54395661/130785655-010feea4-187a-415a-8df5-6a1baf3a3053.jpeg)
**Dettagli WS**:
![WSDetail](https://user-images.githubusercontent.com/54395661/130785662-062e6d53-2d85-4b44-a3c7-ba9c87439091.jpeg)
**Disabilita WS**:
![WSDisable](https://user-images.githubusercontent.com/54395661/130785665-bc479d92-8a65-45bf-96c6-f7c1204f9ce5.jpeg)
**Riattivazione WS**:
![WSReactivate](https://user-images.githubusercontent.com/54395661/130785668-6c5e7a34-3e77-4279-bcf4-3dc972a4df67.jpeg)
**Richiesta intervento speciale WS**:
![WSReport](https://user-images.githubusercontent.com/54395661/130785672-3e778adc-3338-4849-b96f-d22c7a39fecb.jpeg)


## Fase di Testing
Le seguenti component sono state sviluppare per soddisfare i requisiti presentati sopra:
- WSController: responsabile per la gestione delle interfacce utente e la loro interazione. Esegue tutte le operazioni di modifica sulle WS ossia recupero lista WS, aggiunta, eliminazione, disattivazione, riattivazione e richiesta intervento speciale.
- Interfacce / View: per mostrare le funzionalità richieste.
- WeatherStation: Classe che rappresenta le Weather Station del sistema, ognuna formata da WS_Sensor e WS_Battery.
- WS_Sensor: Si occupa della simulazione sia del monitoraggio dei dati sia degli eventuali guasti, ma anche dello stato di ogni singolo sensore.
- WS_Battery: Si occupra della simulazione degli eventuali guasti in cui le batterie possono incorrere e dello stato di ogni singola batteria.
- Enumeration ColorCode e WindDir: per il Codice colore del sistema e delle sue componenti, mentre la seconda per le direzioni del vento.

### Test delle Unità / Unit tests:
I test sono stati scritti per andare a controllare la maggior parte delle classi e del codice. A parte WSController (testate negli Acceptance Tests) e le classi che non potevano essere testate, il sistema è stato interamente controllato/testato.
I risultati ottenuti sono per quanto riguarda il progetto:

![CoverageProject](https://user-images.githubusercontent.com/54395661/130211797-ab46a276-9036-4dfa-b401-457b36d342e5.PNG)

Mentre per le singole classi:

![CoverageCLass](https://user-images.githubusercontent.com/54395661/130211849-ea70aa5e-a47a-4cca-9373-3c0795b1c9fb.PNG)

Essendo il WSController una classe di molte righe/metodi va a impattare molto sulle percentuali di coverage. In ogni caso le rimanenti classi sono state testate su ogni metodo garantendo una coverage del 100% relativa alla classe stessa.

### Test di Accettazione / Acceptance Tests:
Per l'esecuzione dei test si è usufruito del framework JWebUnit.
Per eseguire i test di accettazione bisogna aprire separatamente la cartella contenente gli acceptance test e il progetto attraverso IntelliJ IDEA. Successivamente si deve eseguire il bootRun del progetto vero e proprio e in un secondo momento eseguire i test di accettazione.
Sono stati testati vari scenari cercando di coprire tutto il software, come la creazione di una weather station e le conseguenti azioni che si potevano eseguire:
- Add WS.
- Detail.
- Disable.
- Reactivate.
- Request Maintenance.
- Delete.

I test eseguito hanno dato esito positivo non trovando errori.



## Autori
- **Fabio Tarocco**: [FabioTarocco](https://github.com/FabioTarocco)
- **Leonardo Zecchin**:[leonardozecchin](https://github.com/leonardozecchin)

> Written with [StackEdit](https://stackedit.io/).

