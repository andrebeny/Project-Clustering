/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHitungJarak;

//iki classku sing nggo ngoleki cosine tiap document mbok nggo wae ngko nggolek jarake
import latihan1Model.Document;
import latihan1Model.InvertedIndex2;
import latihan1Model.Posting;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author AxYxA
 */
public class TestBacaDirectory {

    public static void main(String[] args)throws FileNotFoundException, IOException, InterruptedException, Exception {
//        File dir = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\Project-Clustering 5 Juni\\Document Clustering\\Berita Koran");
//        File dir = new File("C:\\Users\\ASUS.DESKTOP-CCOM20V\\Documents\\NetBeansProjects\\Project-Clustering 6 Juni\\Document Clustering\\Berita Koran");
        File dir = new File("Berita Koran");
        InvertedIndex2 index = new InvertedIndex2();

        index.readDirectory(dir);
        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < listDoc.size(); i++) {
            Document doc = listDoc.get(i);
//            System.out.println("Content : " + doc.getId());
//            System.out.println(doc.getContent());
        }
        index.makeDictionaryWithTermNumber();
        for (int i = 0; i < listDoc.size(); i++) {
//            listDoc.get(i).stemming();
            listDoc.get(i).stemming();
            index.makeTFIDF(i);
        }
//        for (int i = 0; i < listDoc.size(); i++) {
        for (int i = 0; i < listDoc.size(); i++) {
            ArrayList<Posting> post = index.makeTFIDF(i);
            for (int j = 0; j < post.size(); j++) {
//                System.out.println(post.get(j).toString());
            }
        }

        for (int i = 1; i < listDoc.size(); i++) {
            for (int j = i + 1; j < listDoc.size() + 1; j++) {
                ArrayList<Posting> post1 = index.makeTFIDF(i);
                ArrayList<Posting> post2 = index.makeTFIDF(j);
                //double Cosine = index.getInnerProduct(post1, post2);
                double Cosine = index.getCosineSimilarity(post1, post2);
//                System.out.println("Hasil Cosine dari doc" + i + " dan doc" + j + "= " + Cosine);
                //hasilcosine isih ngawur wkwkw
                //isih eneng sik lebih dari 1 karo hasile NaN
                //lah yo mboh aku kui rumus seko bapake og
            }
        }
        //njajal method cluster
        
    }
}

