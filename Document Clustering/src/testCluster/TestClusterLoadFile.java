/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testCluster;

import java.io.File;
import java.util.ArrayList;
import latihan1Model.Document;
import latihan1Model.InvertedIndex2;
import latihan1Model.Posting;

/**
 *
 * @author User
 */
public class TestClusterLoadFile {

    public static void main(String[] args) {
        InvertedIndex2 index = new InvertedIndex2();
        Document doc = new Document();

        File folder = new File("C:\\Users\\ASUS.DESKTOP-CCOM20V\\Documents\\NetBeansProjects\\Project-Clustering Begin\\Document Clustering\\Berita Koran");
        index.readDirectory(folder);

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
