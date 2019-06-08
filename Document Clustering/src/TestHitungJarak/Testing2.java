/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHitungJarak;

import java.io.File;
import java.util.ArrayList;
import latihan1Model.Cluster;
import latihan1Model.InvertedIndex2;
import latihan1Model.Document;
import latihan1Model.Posting;

/**
 *
 * @author User
 */
public class Testing2 {

    public static void main(String[] args) {
        InvertedIndex2 index = new InvertedIndex2();
        Document doc = new Document();

        File dir = new File("Berita Koran");
        index.readDirectory(dir);

        index.preClustering();
        index.ClusteringAnyar();
        //perlu nganggo loop
        //error index out of bounds
        
        //loop nggo ngopo??
        
        for (int i = 0; i < index.getListOfDocument().size(); i++) {
            //iki meh ngopo?
            
        }

        ArrayList<Cluster> klaster = index.getCluster();
        for (int i = 0; i < klaster.size(); i++) {
            ArrayList<Document> dokumenKlaster = klaster.get(i).getMember();
            System.out.println("Klaster ke-"+(i+1)+" : ");
            for (int j = 0; j < dokumenKlaster.size(); j++) {
                System.out.println(dokumenKlaster.get(j).getId());
            }
        }
    }
}
