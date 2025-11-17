[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=21705115)
|Attenzione|
|----------|
|Per leggere correttamente questo documento, click destro su README.md e selezionare Open Preview.|

# Un esempio completo: temperature estive
Utilizziamo adesso le funzioni che abbiamo realizzato per risolvere un problema più articolato, che richiede l’uso delle matrici per la memorizzazione dei dati.

## Il problema
Una stazione metereologica esegue ogni giorno un insieme di letture per le ore più calde (11:00-16:00). Si vuole sapere, relativamente alla settimana di ferragosto, in quale giorno e ora si è registrata la temperatura massima e quale è stata la temperatura media per ogni fascia oraria.
Data quindi la seguente tabella con le temperature registrate:
![image](assets/image.png)

Si desidera cacolare l'output come segue:
![image](assets/image_2.png)

## L’analisi e la strategia risolutiva
Per prima cosa determiniamo la dimensione della matrice che conterrà tutti i dati: dobbiamo effettuare un insieme di letture per una settimana (7 giorni) a intervalli orari (5 letture).<br/>
Definiamo quindi due costanti GIORNI=7 e ORE=5 come dimensione della matrice T, di dimensione GIORNIxORE. Ogni riga è relativa alle temperature registrate in una stessa giornata. Per ogni colonna memorizziamo le temperature nelle 5 fasce orarie. Per comodità memorizziamo le temperature in gradi kelvin, quindi con numeri interi.<br/>
Bisogna individuare:
* la temperatura più alta, cioè l’elemento di valore maggiore presente nella tabella;
* la temperatura media per ciascuna fascia oraria, cioè la media per ogni colonna.

Memorizzeremo i dati relativi alla temperatura più alta in tre variabili: la prima deve contenere il valore di temperatura, mentre le altre due le “coordinate” giorno-ora in cui si è registrata questa temperatura.<br/>
A tale scopo, definiremo una funzione *calcolaMassimo()* che  calcola i valori per le variabili temperaturaMAX, giornoMAX, orarioMAX.

Per calcolare la temperatura media per ogni fascia oraria definiremo la funzione *calcolaMedia()* che lavorerà su ogni colonna della matrice.

## Implementazione della soluzione

Nel file *Esercizio.java*, completa il contenuto della funzione *calcolaMassimo*, che trova la temperatura massima contenuta nella matrice, insieme alle sue coordinate riga-colonna. La coordinata riga corrispende al giorno e la coordinata colonna corrisponde alla fascia oraria in cui si è registrata la temperatura massima.

La funzione deve avere i seguenti parametri:
* Parametro di input: matrice M su cui trovare il valore massimo.
* Parametri di input: RIGHE e COLONNE, che rappresentano di quante righe e colonne è composta la matrice.
* Parametro di output Max:
  * il valore massimo trovato.
  * giornoMAX, cioè la riga (il giorno) in cui è stato trovato il massimo.
  * orarioMAX, cioè la colonna (la fascia oraria) in cui è stato trovato il massimo.

<hr/>
<details>
  <summary>Solo dopo aver svolto l'esercizio, apri qui per vedere la soluzione</summary>

```Java
    //Valori multipli di ritorno per il metodo calcolaMassimo
    public static record Max(int massimo, int rIdx, int cIdx) { }

    // Metodo per calcolare il massimo nella matrice
    public static Max calcolaMassimo(int[][] M, int RIGHE, int COLONNE) {
        
        // Assumo che il massimo sia il primo elemento della matrice, cioè alle coordinate (0,0)
        int rIdx = 0;
        int cIdx = 0;
        // Scansione della matrice per righe:
        for (int i = 0; i <= RIGHE - 1; i = i + 1) {
            for (int j = 0; j <= COLONNE - 1; j = j + 1) {
                // Se la cella corrente è maggiore del massimo corrente...
                if (M[i][j] > M[rIdx][cIdx]) {
                    // ... aggiorno il massimo: Annoto in quale riga e colonna ho trovato il massimo
                    rIdx = i;
                    cIdx = j;
                }
            }
        }
        int massimo = M[rIdx][cIdx];
        return new Max(massimo, rIdx, cIdx);
    }
```
</details>
<hr/>

Nel file *Esercizio.java*, completa il contenuto della funzione *calcolaMedia*, che calcola la media di una singola fascia oraria, cioè di una singola colonna. 

La funzione deve avere i seguenti parametri:
* Parametro di input: matrice M su cui calcolare la media.
* Parametri di input: RIGHE e COLONNE, che rappresentano di quante righe e colonne è composta la matrice.
* Parametro di input: cIdx, l'indice della colonna su cui si vuole calcolare la media
* Parametri di input/output: nessuno.
* Parametri di output: la media calcolata sulla colonna jFasciaOraria.

<hr/>
<details>
  <summary>Solo dopo aver svolto l'esercizio, apri qui per vedere la soluzione</summary>

```Java
    // Metodo per calcolare la media di una colonna
    public static float calcolaMedia(int[][] M, int RIGHE, int COLONNE, int cIdx) {
        // Accumulo i valori della colonna jFasciaOraria
        float somma = 0;
        
        // Scorro la matrice lungo la colonna jFasciaOraria
        for (int i = 0; i <= RIGHE - 1; i = i + 1) {
            // Accumulo i valori della colonna jFasciaOraria
            somma = somma + M[i][cIdx];
        }
        
        // calcolo la media
        float media = somma / RIGHE;
        return media;
    }
```
</details>
<hr/>

Avendo tutte le funzioni a disposizione, è possibile ora scrivere la funzione *main*.

In *main()*, dichiarare le variabili GIORNI=7 e ORE=5 e dichiarare quindi matrice T di dimensione GIORNIxORE.
Riempire la matrice con numeri casuali usando la funzione già disponibile nell'esercizio *riempiCasuale*.<br/>
Visualizzare il contenuto della matrice con l'istruzione *UtilsMatrice.visualizza(matrice);*<br/>

<hr/>
<details>
  <summary>Solo dopo aver svolto l'esercizio, apri qui per vedere la soluzione</summary>

```Java
    public static void main(String args[]) {
        int GIORNI = 7;
        int ORE = 5;
        int[][] T = new int[GIORNI][ORE];
        
        riempiCasuale(T, GIORNI, ORE, 290, 300);
        System.out.println("Temperature registrate:");
        UtilsMatrice.visualizza(T);
    }
```
</details>
<hr/>


Invocare le funzioni *calcolaMassimo* per calcolare la temperatura massima, insieme al giorno e fascia oraria in cui si è registrata.
Visualizzare i dati calcolati.

<hr/>
<details>
  <summary>Solo dopo aver svolto l'esercizio, apri qui per vedere la soluzione</summary>

```Java
    public static void main(String args[]) {
        ...        
        Max risultato = calcolaMassimo(T, GIORNI, ORE);
        System.out.println("Temperatura massima: " + risultato.massimo);
        System.out.println("Giorno: " + (risultato.rIdx() + 1) + "°");
        System.out.println("Fascia oraria: " + (risultato.cIdx() + 1) + "°");
        System.out.println();
    }
```
</details>
<hr/>

Per ogni fascia oraria, invocare la funzione *calcolaMedia* per calcolare la media delle temperature su quella fascia oraria.

<hr/>
<details>
  <summary>Solo dopo aver svolto l'esercizio, apri qui per vedere la soluzione</summary>

```Java
    public static void main(String args[]) {
        ...        
        System.out.println("Temperature medie per fasce orarie:");
        for (int j = 0; j <= ORE - 1; j = j + 1) {
            float mediaFasciaOraria = calcolaMedia(T, GIORNI, ORE, j);
            System.out.print(mediaFasciaOraria + " ");
        }
        System.out.println();
    }
```
</details>
<hr/>

Eseguire il programma per controllare che l'output sia analogo al seguente:
```
Temperature registrate: 
[282 285 281 295 286]
[285 292 294 290 290]
[287 293 289 287 294]
[284 294 292 286 291]
[293 286 282 293 285]
[290 290 283 284 291]
[286 285 295 293 291]

Temperatura massima: 295
Giorno: 1°
Fascia oraria: 4°

Temperature medie per fasce orarie:
286.714 289.286 288 289.714 289.714 
```
