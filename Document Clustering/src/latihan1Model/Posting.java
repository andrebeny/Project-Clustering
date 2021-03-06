/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan1Model;

/**
 *
 * @author admin
 */
public class Posting implements Comparable<Posting> {

    private String term;
    private Document document;
    private int numberOfTerm = 1;
    private double weight = 0.0;

    public Posting() {
        numberOfTerm = 1;
        setWeight(0);
    }

    public Posting(Document document) {
        this.document = document;
        setWeight(0);
    }

    public Posting(String term, Document document) {
        this.document = document;
        this.term = term;
        setWeight(0);
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public int compareTo(Posting posting) {
        return term.compareToIgnoreCase(posting.getTerm());
    }

    /**
     * @return the numberOfTerm
     */
    public int getNumberOfTerm() {
        return numberOfTerm;
    }

    /**
     * @param numberOfTerm the numberOfTerm to set
     */
    public void setNumberOfTerm(int numberOfTerm) {
        this.numberOfTerm = numberOfTerm;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Posting{" + "term=" + term + ", numberOfTerm=" + numberOfTerm + ", weight=" + weight + '}';
    }
}
