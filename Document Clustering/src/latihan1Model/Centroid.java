
package latihan1Model;

import java.util.ArrayList;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import latihan1Model.Document;


/**
 *
 * @author Vandi Setyawan
 */

public class Centroid {
    private ArrayList<Double> points= new ArrayList<>();
    private ArrayList<Document> docs= new ArrayList<>();
    private ArrayList<Double> newpoints= new ArrayList<>();
   
    public Centroid(ArrayList<Double> set_points)
    {
        points.addAll(0,set_points);
        
        newpoints.addAll(0,set_points);
    }

    ArrayList<Double> getpoints() {
        return points;
    }
    
    void AssignDocumentToCentroid(Document d)
    {
        docs.add(d);
    }
    
    void clearlistOfDoc()
    {
        docs =new ArrayList<>();
    }
    
    void setPoints(ArrayList<Double> set_points)
    {
        points=new ArrayList<>();
        points.addAll(0,set_points);
    }
    
    ArrayList<Document> getdocs()
    {
        return docs;
    }

    void setnewPoints(ArrayList<Double> temp) {
        
        newpoints=new ArrayList<>();
        newpoints.addAll(0,temp);
    
    }
    
    boolean compareCentroidPoints() throws Exception
    {   
        int counter=0;
    if(points.size()==newpoints.size())
    {
       for(int z=0;z<points.size();z++)
        {
           if(points.get(z).compareTo(newpoints.get(z))==0)
            counter+=0; 
           else 
               counter++;
        }
    }
    else
    {
        throw new CannotProceed("Number of components do not match", null,null);
    }
       return counter==0; 
    }
    
    void replaceOldPointsWithNew()
    {
        points=newpoints;
        newpoints=new ArrayList<>();
    }
    
}
