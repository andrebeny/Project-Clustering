/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan1Model;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Cluster {

    //center bisa abstrak atau object
    private ArrayList<Document> member = new ArrayList<Document>();
    private Document center = new Document();
    private int idCluster;
    private InvertedIndex2 ii = new InvertedIndex2();

    public Cluster() {
    }

    public Cluster(int idCluster) {
        this.idCluster = idCluster;
    }

    /**
     * @return the member
     */
    public ArrayList<Document> getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(ArrayList<Document> member) {
        ii.setListOfDocument(member);
        this.member = member;
    }

    /**
     * @return the center
     */
    public Document getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(Document center) {
        this.center = center;
    }
    
//    public void reCenter() {
//        double hasil = 0;
//        for (int i = 0; i < member.size(); i++) {
//            double jarak = ii.getCosineSimilarity(center.getListOfClusteringPosting(), member.get(i).getListOfClusteringPosting());
//            hasil = hasil + jarak;
//        }
//        
//    }

    /**
     * @return the idCluster
     */
    public int getIdCluster() {
        return idCluster;
    }

    /**
     * @param idCluster the idCluster to set
     */
    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }

}
