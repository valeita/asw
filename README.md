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

infoUni

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
