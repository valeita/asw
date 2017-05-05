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


Il servizio principale ***infoUni*** fornisce informazioni (casuali) relative ad università, facoltà e corsi ai suoi client. Il servizio infoUni fornisce tre operazioni:

/infoUni/<università>/<facoltà>/<_corso_> restituisce informazioni (casuali) sulla <università>, e informazioni(sempre casuali) per quella <facoltà> e per quel <corso>.

/infoUni/<università>/<facoltà> restituisce invece informazioni (casuali) sulla <università>, e informazioni (sempre casuali) generiche per quella <facoltà> includendo il totale dei crediti che comprende tale <facoltà>.

/infoUni/<università> restituisce invece informazioni (casuali) sulla <università>, e informazioni (sempre casuali) generiche per le facoltà contenute in quella <università>.

ad esempio:

http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
* romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
* romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
* romaTre è stata fondata nel 962 e ha 78 facoltà.

Il servizio infoUni va implementato come client di tre servizi secondari univeristy, faculty e course, con le caratteristiche descritte nel seguito.


Il servizio ***university*** fornisce informazioni (sempre casuali) relative all' università. Il servizio university fornisce una sola operazione:

/infoUni/<università> restituisce informazioni (casuali) sulla <università> relativa alla sua data di fondazione.

ad esempio:

http://localhost:8081/university/romaTre
* 1088


il servizio ***faculty*** fornisce informazioni (sempre casuali) relative alle facoltà presenti in una certa università, e al loro numero di esami. il servizio faculty fornisce due operazioni:

/faculty/<università> restituisce informazioni (casuali) sul numero delle facoltà presenti in una certa università.

/faculty/<università>/<facoltà> restituisce informazioni (casuali) sul numero degli esami presenti in una certa facoltà.

ad esempio:

http://localhost:8082/faculty/romaTre
* 78

http://localhost:8082/faculty/romaTre/Ingegneria
* 13



il servizio ***course*** fornisce informazioni (sempre casuali) relative ai corsi presenti in una certa università o facoltà. il servizio faculty fornisce due operazioni:

/course/<università>/<facoltà> restituisce informazioni (casuali) sul numero dei crediti totali di una certa facoltà.

/course/<università>/<facoltà>/<corso> restituisce informazioni (casuali) sul numero dei crediti di un certo corso.

ad esempio:

http://localhost:8083/course/romaTre/Ingegneria
* 180

http://localhost:8083/course/romaTre/Ingegneria/Architetture
* 6


Il servizio infoUni deve rispondere al suo client usufruendo dei servizi university, faculty e course integrando le loro risposte.
