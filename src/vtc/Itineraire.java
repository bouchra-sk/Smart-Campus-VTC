package vtc;

import java.util.ArrayList;

public class Itineraire {
    private ArrayList<Point> pointsRamassage;
    private ArrayList<Point> pointsDepot; 
  

    public Itineraire(ArrayList<Point> pointsRamassage, ArrayList<Point> pointsDepot) {
        this.pointsRamassage = pointsRamassage;
        this.pointsDepot = pointsDepot;
   
    }

    public void ajouterPointRamassage(Point point) {
        if (pointsRamassage == null)
        	System.out.println("Les points de ramassage  ne peuvent pas être vides.");;
        pointsRamassage.add(point);
    }

    public void ajouterPointDepot(Point point) {
        if (pointsDepot == null)
        	System.out.println("Les points de dépôt ne peuvent pas être vides.");
        pointsDepot.add(point);
    }

    public ArrayList<Point> getPointsRamassage() { return pointsRamassage; }
    public void setPointsRamassage(ArrayList<Point> pointsRamassage) { this.pointsRamassage = pointsRamassage; }

    public ArrayList<Point> getPointsDepot() { return pointsDepot; }
    public void setPointsDepot(ArrayList<Point> pointsDepot) { this.pointsDepot = pointsDepot; }

   

    public double calculerDistance(Point p1, Point p2) {
        double dx = p1.getLatitude() - p2.getLatitude();
        double dy = p1.getLongitude() - p2.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
