# ASW
Repository di Architetture Software

* [Descrizione seconda parte del progetto](#descrizione-seconda-parte-del-progetto)
* [Build](#build)
* [Casi d'uso](#casi-d'uso)

## Descrizione seconda parte del progetto:

Lo scopo di questa seconda parte del progetto si occupa della modifica dell'applicazione distribuita realizzata nella prima parte del progetto, composta da alcuni servizi stateless, che comunicano tra loro tramite invocazioni remote con REST.
Per concludere, tale applicazione è stata rilasciata sullo swarm dell'università di RomaTre.

## Tecnologie:

La modifica in questione, tratta l'aggiunta di nuovi elementi facenti parte del progetto Spring Cloud Netflix quali:
* Eureka, per implementare la funzionalità di service discovery dei servizi.
* Ribbon, per implementare le funzionalità di load balancer.
* Feign, per semplificare la scrittura del codice, in particolare delle chiamate REST.
* Hystrix, per implementare un intermediario il cui scopo è di evitare fallimenti a cascata di servizi.
* Zuul, per implementare il mapping tra path/URI dei sottoservizi e il nostro servizio principale.

Parte fondamentale per la realizzazione della seconda parte del progetto, è l'utilizzo di dipendenze starter, che permette di utilizzare gli strumenti che Spring Cloud mette a disposizione.

## Applicazione e servizi:

Questa seconda parte del progetto è ancora composta da:
* Un servizio principale S, che può ricevere richieste da un client HTTP/REST esterno, ed in particolare da un qualunque browser web, e può effettuare richieste ai suoi servizi secondari (descritti qui sotto).
* Due o più servizi secondari (S1, S2, ...), che possono ricevere richieste dal servizio principale S e che possono scambiarsi richieste tra di loro.
* In aggiunta, sarà presente un server Eureka che implementa le funzionalità di service discovery, e permetterà ai vari servizi (S,S1,S2,S3) di scoprirsi tra loro.
  
In questo secondo progetto, i diversi servizi vanno ancora mandati in esecuzione tutti in uno stesso nodo (host), ma in application server Tomcat separati, collegati a porte diverse. In particolare, il servizio principale S è esposto sulla porta 8080, i servizi secondari questa volta su delle porte casuali.
Inoltre, il server Eureka, sarà esposto sulla porta 8761. I vari sottoservizi, pur essendo esposti su porte casuali, riescono a comunicare con il servizio principale grazie a Eureka che permette ai servizi di localizzarsi.

Ciascuno dei servizi è realizzato come una applicazione Spring Boot separata, integrata con il progetto Spring Cloud.

Ciascun servizio è realizzato come una applicazione separata e indipendente dagli altri servizi.

## Note

il codice relativo a Ribbon nel servizio principale S è stato lasciato inalterato, e volontariamente non decommentato, cosi come i relativi import vari, in quanto era stato richiesto di mostrare il nostro lavoro riguardante quella parte.
Infatti il codice è stato modificato e sviluppato in maniera incrementale pur sapendo in anticipo che l'utilizzo di Feign avrebbe permesso la semplificazione del codice a tal punto.

## Struttura (cartelle):

Il progetto è costituito da una cartella principale "Software Architecture's Project".
Al suo interno sono contenute cinque sotto-cartelle:

* "InfoUniService", contenente il servizio principale dell'applicazione.
* "CourseService","FacultyService","UniversityService", ciascuna contenente un sottoservizio.
* "EurekaService", contenente il server Eureka.

## Build:

Posizionarsi nella cartella principale del servizio EurekaService ed eseguire:

    gradle build
    ./run-eureka-service.sh

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
       
In alternativa:

Posizionarsi della cartella Software Architecture's Project

    ./build-all-projects.sh
    
## Casi d'uso:

### Il servizio principali InfoUni:

Il servizio principale infoUni fornisce informazioni (casuali) relative ad università, facoltà e corsi ai suoi client. Il servizio infoUni fornisce tre operazioni:

**/_infoUni_/<_università>_/<_facoltà_>/<_corso_>** restituisce informazioni (casuali) sulla <_università_>, e informazioni(sempre casuali) per quella <_facoltà_> e per quel <_corso_>.

**/_infoUni_/<_università_>/<_facoltà_>** restituisce invece informazioni (casuali) sulla <_università_>, e informazioni (sempre casuali) generiche per quella <_facoltà_> includendo il totale dei crediti che comprende tale <_facoltà_>.

**/_infoUni_/<_università_>** restituisce invece informazioni (casuali) sulla <_università_>, e informazioni (sempre casuali) generiche per le facoltà contenute in quella <_università_>.

ad esempio:

http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
* romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
* romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
* romaTre è stata fondata nel 962 e ha 78 facoltà.

Il servizio infoUni va implementato come client di tre servizi secondari univeristy, faculty e course, con le caratteristiche descritte nel seguito.


Per quanto riguarda i vari sottoservizi, è stato possibile grazie a Zuul, sviluppare l'applicazione garantendo un solo punto di accesso, quello sulla porta 8080 del servizio principale.
Questo ha garantito che non è possibile invocare le funzionalità dei vari sottoservizi (a meno che non si conosca la porta casuale) in modo diretto.

è stato aggiunto il prefisso **/_info_** nel file yaml del servizio principale, per distinguere l'invocazione di un sottoservizio rispetto al servizio principale.
Ogni sottoservizio è inoltre mappato in automatico in questo modo **/_Nome_Servizio_**.
L'invocazione di un sottoservizio tramite Zuul, ha acquisito dunque questa forma **/_info_/_Nome_Sottoservizio_/<..>/<..>/..**

### Il servizio University:

Il servizio university fornisce informazioni (sempre casuali) relative all' università. Il servizio university fornisce una sola operazione:

**/_info_/_university_/<_università_>** restituisce informazioni (casuali) sulla <_università_> relativa alla sua data di fondazione.

ad esempio:

http://localhost:8080/info/university/romaTre
* 1088


### Il servizio Faculty:

il servizio faculty fornisce informazioni (sempre casuali) relative alle facoltà presenti in una certa università, e al loro numero di esami. il servizio faculty fornisce due operazioni:

**/_info_/_faculty_/<_università_>** restituisce informazioni (casuali) sul numero delle facoltà presenti in una certa <_università_>.

**/_info_/_faculty_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero degli esami presenti in una certa <_facoltà_>.

ad esempio:

http://localhost:8080/info/faculty/romaTre
* 78

http://localhost:8080/info/faculty/romaTre/Ingegneria
* 13


### Il servizio Course:

il servizio course fornisce informazioni (sempre casuali) relative ai corsi presenti in una certa università o facoltà. il servizio faculty fornisce due operazioni:

**/_info_/_course_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero dei crediti totali di una certa <_facoltà_>.

**/_info_/_course_/<_università_>/<_facoltà_>/<_corso_>** restituisce informazioni (casuali) sul numero dei crediti di un certo <_corso_>.

ad esempio:

http://localhost:8080/info/course/romaTre/Ingegneria
* 180

http://localhost:8080/info/course/romaTre/Ingegneria/Architetture
* 6

Il servizio infoUni deve rispondere al suo client usufruendo dei servizi university, faculty e course integrando le loro risposte.

## Rilascio:

L'applicazione è stata rilasciata nello swarm dell'università di RomaTre.
A tale scopo, sono state eseguite le seguenti istruzioni:

1) Scaricare e installare Docker.
2) Avviare il demone Docker.
3) Posizionarsi nella cartella Second Part Of The Project ed eseguire i seguenti script.

    `./build-all-projects.sh`
    
    `./build-all-images.sh`
    
    `./push-all-images.sh`
    
    `./start-infoUni-stack.sh`
    
   
    
    
