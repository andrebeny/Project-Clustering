/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan1Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author jarene umang borad wkwkw
 */
public class Document implements Comparable<Document> {

    private int id;
    private String content;
    private String realContent;
    private double CosineSimilarity;
    private String namaDokumen;
    private double tf_idf;
    private ArrayList<Double> docVector = new ArrayList<>();
    private ArrayList<Double> unitVector = new ArrayList<>();
    private ArrayList<Posting> listOfClusteringPosting = new ArrayList<Posting>();

    public ArrayList<Double> getUnitVector() {
        return null;
        //nggoleki vector space ne, sek nilai term iki piro opo mneh kui
        //iki sek penting tapi aku yo ramudeng jugo iki ngopo
    }

    public void add_component_to_vector(double Tf_Idf) {
        this.tf_idf = Tf_Idf;
    }

    //iki posting pak
    //nganggo method getlengthofposting, hampir mirip
    void normalized_Vector() {
        double squaredSum = 0;
        if (!docVector.isEmpty()) {

            for (int d = 0; d < docVector.size(); d++) {
                squaredSum += (docVector.get(d) * docVector.get(d));
            }

            squaredSum = Math.sqrt(squaredSum);

            for (int d = 0; d < docVector.size(); d++) {
                unitVector.add(d, docVector.get(d) / squaredSum);
            }
        }

    }
    

    ArrayList<Double> getVector() {
        return docVector;
    }

    double countOccurences(String get) {
        int count = 0;
        for (int x = 0; x < listOfClusteringPosting.size(); x++) {
            if (get.equals(listOfClusteringPosting.get(x))) {
                count++;
            }
        }
        return count;
    }

    public String getName() {
        return namaDokumen;
    }

    public void SetName(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public Document() {

    }

    public Document(String content) {
        this.content = content;
        this.realContent = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
        this.realContent = content;
    }

    Document(int idDocument) {
        this.id = idDocument;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String[] getListofTerm() {
        String value = this.getContent();
        value = value.replaceAll("[.,?!]", "");
        return value.split(" ");
    }

    public ArrayList<Posting> getListofPosting() {
        // panggil fungsi getListOfTerm
        String tempString[] = getListofTerm();
        // buat objek ArrayList<Posting> result untuk menampung hasil
        ArrayList<Posting> result = new ArrayList<Posting>();
        // buat looping sebanyak listOfTerm
        for (int i = 0; i < tempString.length; i++) {
            // di dalam looping
            // jika term pertama maka
            if (i == 0) {
                // buat object tempPosting
                Posting temPosting = new Posting(tempString[0], this);
                // set atribut document, gunakan this
                // tambahkan ke ArrayList result
                result.add(temPosting);
            } else {
                // lainnya
                // sorting ArayList result
                Collections.sort(result);
                // cek apakah term sudah ada
                // gunakan fungsi search dengan luaran indeks obyek yang memenuhi
                // buat object tempPosting           
                Posting temPosting = new Posting(tempString[i], this);
                int indexCari = Collections.binarySearch(result, temPosting);
                // jika hasil cari kurang dari 0  (obyek tidak ada)
                if (indexCari < 0) {
                    // set atribut document, gunakan this
                    // tambahkan ke ArrayList result
                    result.add(temPosting);
                } else {
                    // lainnya   (obyek ada)
                    // ambil postingnya, 
                    // tambahkan atribut numberOfTerm dengan 1
                    // dgn fungsi get
                    // int tempNumber = get(indekshasilCari).getNumberOfTerm()+1;
                    int tempNumber = result.get(indexCari).getNumberOfTerm() + 1;
                    // atau get(indekshasilcari.setNumberOfTerm(tempNumber)
                    result.get(indexCari).setNumberOfTerm(tempNumber);
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(Document t) {
        //return Integer.compare(this.id, t.id);
        return id - t.getId();
    }

    public void readFile(int idDoc, File file) {

        this.id = idDoc;
        String strLine = null;
        try {
            FileReader inputDokumen = new FileReader(file);//membaca inputan sebuah dokumen
            BufferedReader br = new BufferedReader(inputDokumen);

            while ((strLine = br.readLine()) != null) {
                this.setContent(strLine);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * @return the CosineSimilarity
     */
    public double getCosineSimilarity() {
        return CosineSimilarity;
    }

    /**
     * @param CosineSimilarity the CosineSimilarity to set
     */
    public void setCosineSimilarity(double CosineSimilarity) {
        this.CosineSimilarity = CosineSimilarity;
    }

    public void removeStopWords() {
        // asumsi content sudah ada
        String text = content;
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);
        // ambil stopwords
        CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
        // buat token
        TokenStream tokenStream = analyzer.tokenStream("myField", new StringReader(text.trim()));
        // buang stop word
        tokenStream = new StopFilter(tokenStream, stopWords);
        // buat string baru tanpa stopword
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        content = sb.toString();
    }

    public void stemming() {
        String text = content;
        System.out.println("Text = " + text);
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);
        // buat token
        TokenStream tokenStream = analyzer.tokenStream(
                "myField",
                new StringReader(text.trim()));
        // stemming
        tokenStream = new PorterStemFilter(tokenStream);
        // buat string baru tanpa stopword
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        content = sb.toString();
    }

    /**
     * @return the realContent
     */
    public String getRealContent() {
        return realContent;
    }

    /**
     * @param realContent the realContent to set
     */
    public void setRealContent(String realContent) {
        this.realContent = realContent;
    }

    public void IndonesiaStemming() {
        String text = realContent;
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new IndonesianAnalyzer();
        analyzer.setVersion(matchVersion);
        // ambil stopwords
        CharArraySet stopWords = IndonesianAnalyzer.getDefaultStopSet();
        // buat token
        TokenStream tokenStream = analyzer.tokenStream("myField", new StringReader(text.trim()));
        // buang stop word
        tokenStream = new StopFilter(tokenStream, stopWords);
        // buat string baru tanpa stopword
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        content = sb.toString();
    }

    /**
     * @return the listOfClusteringPosting
     */
    public ArrayList<Posting> getListOfClusteringPosting() {
        return listOfClusteringPosting;
    }

    /**
     * @param listOfClusteringPosting the listOfClusteringPosting to set
     */
    public void setListOfPosting(ArrayList<Posting> listOfClusteringPosting) {
        this.listOfClusteringPosting = listOfClusteringPosting;
    }

}
