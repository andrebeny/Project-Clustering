/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testCluster;

import java.util.ArrayList;
import latihan1Model.Document;
import latihan1Model.InvertedIndex2;
import latihan1Model.Posting;

/**
 *
 * @author User
 */
public class TestCluster1 {

    public static void main(String[] args) {
        // seting dokumen
        // seting dokumen
        Document doc1 = new Document(1, "Fahri Hamzah Usul Ibu Kota Pindah ke Kepulauan Seribu");
        Document doc2 = new Document(2, "Gaya Nyentrik Menteri Susi Saat Pimpin Penenggelaman 13 Kapal Vietnam di Kalbar");
        Document doc3 = new Document(3, "TNI AL: KRI Tjiptadi-381 Diprovokasi Kapal Pengawas Ikan Vietnam ");

        // buat object invertedIndex
        InvertedIndex2 index = new InvertedIndex2();
        // lakukan stemming untuk semua dokumen
        doc1.IndonesiaStemming();
        doc2.IndonesiaStemming();
        doc3.IndonesiaStemming();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();
        // bikin preclustering
        index.preClustering();
        for (int i = 0; i < index.getListOfDocument().size(); i++) {
            ArrayList<Posting> listPosting
                    = index.getListOfDocument().get(i).getListOfClusteringPosting();
            System.out.println("IdDoc = " + index.getListOfDocument().get(i).getId());
            for (int j = 0; j < listPosting.size(); j++) {
                System.out.println(listPosting.get(j));
            }

        }
    }
}
