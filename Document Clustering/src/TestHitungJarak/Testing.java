/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHitungJarak;

import java.io.File;
import java.util.ArrayList;
import latihan1Model.InvertedIndex2;
import latihan1Model.Posting;

/**
 *
 * @author AxYxA
 */
public class Testing {
    public static void main(String[] args) {
        File f = new File("Berita Koran");
        InvertedIndex2 ii = new InvertedIndex2();
        
        ii.readDirectory(f);
        ii.makeDictionaryWithTermNumber();
        for (int i = 0; i < ii.getListOfDocument().size(); i++) {
            ii.getListOfDocument().get(i).IndonesiaStemming();
            ii.makeTFIDF(i);
        }
        
        ArrayList<Posting> xx = ii.makeTFIDF(39);
        ArrayList<Posting> xy = ii.makeTFIDF(40);
        double cs = ii.getCosineSimilarity(xx, xy);
        System.out.println(cs);
    }
}
