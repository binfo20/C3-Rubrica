/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author francescofdd5
 */
public class ReadWriteFile {
    
    public ReadWriteFile() {
    } //Costruttore

    /**
     * Crea un file nel percorso e col nome passato dalla variabile nome //E ci
     * inserisce in append la stringa passata dalla veriabile cosa
     *
     * @param cosa
     * @param nomeFile
     */
    public void salvaFile(String cosa, String nomeFile) {
        FileWriter f = null;
        BufferedWriter salva;
        
        try {
            f = new FileWriter(nomeFile, true);
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        salva = new BufferedWriter(f);
        try {
            salva.write(cosa);
            salva.newLine();
            salva.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Crea un file nel percorso e col nome passato dalla variabile nome E ci
     * inserisce NON IN APPEND la stringa passata dalla veriabile cosa
     *
     * @param cosa
     * @param nomeFile
     */
    public void scriviFile(String cosa, String nomeFile) {
        FileWriter f = null;
        BufferedWriter salva;
        
        try {
            f = new FileWriter(nomeFile, false);
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        salva = new BufferedWriter(f);
        try {
            salva.write(cosa);
            salva.newLine();
            salva.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scriviMatriceSuFile(String[][] cosa, String nomeFile) {
        cancellaFile(nomeFile);
        for (String[] i : cosa) {
            String out = null;
            for (String element : i) {
                out = element + " " + out;

            }
            salvaFile(out, nomeFile);
        }
    }

    /**
     * Cerca se la stringa passata come primo parametro è nel file il cui
     * percorso è passato come secondo parametro
     *
     * @param s
     * @param nomeFile
     * @return
     * @retun La posizione della striga passata
     */
    public int cercaStringa(String s, String nomeFile) {
        String cerca = null;
        int i = 0;
        int trova = -1;
        FileReader f = null;
        BufferedReader leggi = null;
        
        try {
            f = new FileReader(nomeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        leggi = new BufferedReader(f);
        if (cerca != null) {
            do {
                try {
                    cerca = leggi.readLine();
                    
                    if (cerca.equals(s)) {
                        trova = i;
                    }
                    i++;
                } catch (IOException ex) {
                    Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } while (!cerca.equals(s));
        }
        try {
            leggi.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trova;
    }

    /**
     * Data la posizione di una stringa
     *
     * @param nomeFile
     * @param lin
     * @return Ritorna la Striga alla n'esima riga
     */
    public String caricaElemento(String nomeFile, int lin) {
        
        int i = 0;
        String carica = null;
        FileReader f = null;
        BufferedReader leggi = null;
        
        try {
            f = new FileReader(nomeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        leggi = new BufferedReader(f);
        for (i = 0; i <= lin; i++) {
            try {
                carica = leggi.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            leggi.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carica;
        
    }

    /**
     * Verifica se il nome del file passato come primo parametro esiste nella
     * directory passata come secondo parametro
     *
     * @param fileName
     * @param dir
     * @return true se trovata, false se il file non esiste
     */
    public boolean esisteFile(String fileName, String dir) {
        File f = new File(dir);
        String files[] = null;
        int i = 0;
        
        if (f.isDirectory()) {
            files = f.list();
        }
        if (files != null) {
            for (i = 0; i < files.length; i++) {
                if (files[i].equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Data una directory passata come primo parametro, cerca i file che hanno
     * la stessa estenzione passata come secondo parametro
     *
     * @param dir
     * @param ext
     * @return Un array di Strign che corrispondono ai file trovati
     */
    public String[] listaFile(String dir, String ext) {
        
        File f = new File(dir);
        if (!f.isDirectory()) {
            return null;
        }
        String files[] = f.list();
        String fileCerca[] = new String[files.length];
        int i = 0;
        int k = 0;
        
        k = 0;
        for (i = 0; i < files.length; i++) {
            if (files[i].contains(ext)) {
                fileCerca[k] = files[i];
                k++;
            }
        }
        return fileCerca;
    }

    /**
     * Dato come parametro il nome e percorso di un file, lo elimina
     *
     * @param nomeFile
     */
    public void cancellaFile(String nomeFile) {
        File f = new File(nomeFile);
        if (f.isFile()) {
            f.delete();
        }
    }

    /**
     * Dato come parametro il nome e percorso di un file
     *
     * @param nomeFile
     * @return Un array di String che ha per ogni posizione una riga del file
     * passato come parametro
     * @throws java.io.IOException
     */
    public String[] stampaFile(String nomeFile) throws IOException {
        //String out[];
        int i = 0;
        FileReader f = null;
        BufferedReader leggi = null;
        
        try {
            f = new FileReader(nomeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        leggi = new BufferedReader(f);
        i = 0;
        do {
            i++;
            
        } while (leggi.readLine() != null);
        leggi.close();
        
        try {
            f = new FileReader(nomeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        leggi = new BufferedReader(f);
        
        String out[] = new String[i];
        for (i = 0; i < out.length; i++) {
            out[i] = leggi.readLine();
        }
        leggi.close();
        return out;
    }

    /**
     * Dato la posizione dell'oggetto e il nome del file Ricordo che il file
     * deve essere formattato in questo modo ----- (dati) (dati) ----- NELLA
     * PRIMA RIGA DEL FILE NON BISOGNA METTERCI I SEPARATORI
     *
     * @param posizione
     * @param nomeFile
     * @return Ritorna un array con tutte le righe comprese dai due spaziatori
     * @throws java.io.IOException
     */
    public String[] stampaNumeroPersone(int posizione, String nomeFile) throws IOException {
        String contenutoFile[] = stampaFile(nomeFile);
        String arrayStampa[] = new String[contenutoFile.length];
        int i = 0;
        int k = 0;
        int j = 0;
        while (i != posizione) {
            if (contenutoFile[k].equals("-----")) {
                i++;
            }
            k++;
        }
        while (!contenutoFile[k].equals("-----")) {
            arrayStampa[j] = contenutoFile[k];
            k++;
            j++;
        }
        
        return arrayStampa;
    }

    /**
     * ritorna quanti blocchi di dati sono presenti all'interno del file
     * formattato nel modo descritto nel metodo stampaNumeroPersone
     *
     * @param nomeFile
     * @return
     * @throws java.io.IOException
     */
    public int numeroPersone(String nomeFile) throws IOException {
        String contenutoFile[] = stampaFile(nomeFile);
        
        int i = 0;
        int k = 0;
        
        while (i != stampaFile(nomeFile).length - 1) {
            if (contenutoFile[i].equals("-----")) {
                k++;
            }
            i++;
        }
        k = k - 1;
        System.out.println(k);
        return k;
    }

    /**
     * Parametri da passare: String nome del file di testo String[] dei titoli
     * String nome del file HTML da generare String nome della pagina html
     * String titolo della pagina metodo di traferimento dati da txt a html
     *
     * @param nomeFile
     * @param blocchi
     * @param nomePagina
     * @param nomeFileHTML
     * @param titolo
     * @throws java.io.IOException
     */
    public void fileToHTML(String nomeFile, String[] blocchi, String nomeFileHTML, String nomePagina, String titolo) throws IOException {
        int i = 0;
        int j = 0;
        int numeroPersone = numeroPersone(nomeFile);
        scriviFile("<html>\n" + "<head>\n" + "<title>" + nomePagina + "</title>", nomeFileHTML);
        salvaFile("<div align='center'>\n", nomeFileHTML);
        salvaFile("<h5>" + titolo + "</h5>", nomeFileHTML);
        salvaFile("<table border='1' width='800'>\n<tr>", nomeFileHTML);
        for (i = 0; i < blocchi.length - 1; i++) {
            salvaFile("<td> <div align='center'> <h5>" + blocchi[i] + "</h5></div></td>", nomeFileHTML);
        }
        salvaFile("</tr>", nomeFileHTML);
        for (i = 0; i <= numeroPersone; i++) {
            salvaFile("<tr>", nomeFileHTML);
            String datiPersona[] = stampaNumeroPersone(i, nomeFile);
            for (j = 0; j < blocchi.length - 1; j++) {
                salvaFile("<td> <div align='center'> <h5>" + datiPersona[j] + "</h5></div></td>", nomeFileHTML);
            }
            salvaFile("</tr>", nomeFileHTML);
        }
        salvaFile("</html>", nomeFileHTML);
    }
    
    public String[] stampaAlbum(int posizione, String nomeFile) throws IOException {
        String contenutoFile[] = stampaFile(nomeFile); //Usando il mio metodo stampaFile 
        //salvo tutto il contenuto del file dentro un array di String
        //Dove ogni riga del file è contenuta in una posizione dell'array

        String arrayStampa[] = new String[contenutoFile.length]; //Inizializzo l'array per l'output
        int separatore;
        int separatore_;
        
        separatore = contenutoFile[posizione].indexOf("/");       //Artista
        arrayStampa[0] = contenutoFile[posizione].substring(0, separatore);
        
        separatore_ = contenutoFile[posizione].indexOf("-"); //Anno
        //System.out.println(separatore_);
        if (separatore_ != -1) { //Se nell'album è presente l'anno
            arrayStampa[1] = contenutoFile[posizione].substring(separatore + 1, separatore_ - 1); //Anno
            arrayStampa[2] = contenutoFile[posizione].substring(separatore_ + 2, contenutoFile[posizione].length()); //Titolo
        }
        if (separatore_ == -1) { //Se nell'album NON è presente l'anno
            arrayStampa[1] = "0000";
            arrayStampa[2] = contenutoFile[posizione].substring(separatore + 1, contenutoFile[posizione].length());
        }
        return arrayStampa;
    }

    /**
     * Ritorna il numero di blocchi
     *
     * @param nomeFile
     * @return
     * @throws IOException
     */
    public int numeroAlbum(String nomeFile) throws IOException {
        String contenutoFile[] = stampaFile(nomeFile);
        
        return contenutoFile.length;
    }


    /**
     * Serializza l'oggetto nel file passato come parametro
     *
     * @param obj
     * @param fileName
     */
    public void serializzaOggetto(Object obj, String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (oos != null) {
            try {
                oos.writeObject(obj);
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Scrittura oggetto OK");
            System.out.println("+++++++++++++++++++");
            System.out.println("");
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Errore Lettura");
        }
    }

    /**
     * deserializza l'oggetto (come oggetto generico, poi andra' fatto il
     * casting) dal file passato come parametro (con il percorso completo)
     *
     * @param fileName
     * @return
     */
    public Object deserializzaOggetto(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ois != null) {
            Object obj = null;
            try {
                obj = ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Lettura oggetto OK");
            System.out.println("+++++++++++++++++++");
            System.out.println("");
            return obj;
        } else {
            return "Errore Lettura";
        }
    }
}
