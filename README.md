# ASW
Repository di Architetture Software

## Info generali:

Il progetto è costituito da una cartella principale "Architecture's Software Project".
Al suo interno sono contenute quattro sotto-cartelle:

* "InfoUniService", contenente il servizio principale dell'applicazione.
* "CourseService","FacultyService","UniversityService", ciascuna contenente un sottoservizio.

## Build:

Posizionarsi nella cartella principale del servizio InfoUniService ed eseguire:

    gradle build
    ./run-infoUni-service.sh

Posizionarsi nella cartella principale del servizio UniversityService ed eseguire:

    gradle build
    ./run-university-service.sh

Posizionarsi nella cartella principale del servizio FacultyService ed eseguire:

    gradle build
    ./run-faculty-service.sh
    
Posizionarsi nella cartella principale del servizio CourseService ed eseguire:

    gradle build
    ./run-course-service.sh
    

## Casi d'uso:


Il servizio principale infoUni fornisce informazioni relative ad università, facoltà (casuali) ai suoi client. Il servizio infoUni fornisce tre operazioni:

/infoUni/<università>/<facoltà>/<corso> restituisce informazioni (casuali) sulla <università>, e informazioni(sempre casuali) per quella <facoltà> e per quel <corso>.

/infoUni/<università>/<facoltà> restituisce invece informazioni (casuali) sulla <università>, e informazioni (sempre casuali) generiche per quella <facoltà> includendo il totale dei crediti che comprende tale <facoltà>.

/infoUni/<università> restituisce invece informazioni (casuali) sulla <università>, e informazioni (sempre casuali) generiche per le facoltà contenute in quella <università>.

ad esempio:

http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
* romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
* romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
* romaTre è stata fondata nel 962 e ha 78 facoltà.


Ad esempio,
  la richiesta /S/Roma/giugno potrebbe restituire “Roma è famosa per la pizza e il tempo a giugno è caldo”
  la richiesta /S/Roma potrebbe restituire “Roma è famosa per la pizza e il tempo è generalmente mite”
Il servizio S va implementato come client di due servizi secondari S1 e S2, con le caratteristiche descritte nel seguito.
Il servizio S1 fornisce un’operazione:
  S1/<citta> restituisce un motivo (casuale) per cui una <citta> è famosa
Ad esempio,
  la richiesta /S1/Roma potrebbe restituire “la pizza”
Inoltre, il servizio S2 fornisce due operazioni:
  S2/<citta>/<mese> restituisce il tempo (casuale) che fa nella <citta> in quel <mese>
  S2/<citta> restituisce il tempo (sempre casuale) che generalmente fa nella <citta> Ad esempio,
  la richiesta /S2/Roma/giugno potrebbe restituire “caldo”
  la richiesta /S2/Roma potrebbe restituire “mite”
Il servizio S deve rispondere al suo client usufruendo dei servizi S1 e S2 e integrando le loro risposte.



http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
romaTre è stata fondata nel 962 e ha 78 facoltà.

università
http://localhost:8081/university/romaTre
1088

facoltà
http://localhost:8082/faculty/romaTre
78 (numero delle facoltà presenti a romatre)

http://localhost:8082/faculty/romaTre/Ingegneria
13 (esami)

Corso

http://localhost:8083/course/romaTre/Ingegneria
180(crediti totali della facoltà di ingegneria)

http://localhost:8083/course/romaTre/Ingegneria/Architetture
6(crediti)
