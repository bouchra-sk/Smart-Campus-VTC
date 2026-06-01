package vtc;

public class Match {
    private static final double DISTANCE_MAX = 5.0;

    public static boolean estCompatible(Utilisateur chauffeur, Utilisateur passager) {
        if (chauffeur == null || passager == null) return false;

        if (chauffeur.getPreferences() == null || passager.getPreferences() == null) return false;
        if (chauffeur.getPositionActuelle() == null || passager.getPositionActuelle() == null) return false;
        if (chauffeur.getTypeCourse() == null || passager.getTypeCourse() == null) return false;
        if (chauffeur.getItineraire() == null || passager.getItineraire() == null) return false;

        boolean prefsCompatibles = chauffeur.getPreferences().estCompatibleAvec(passager.getPreferences())
                                   && passager.getPreferences().estCompatibleAvec(chauffeur.getPreferences());

        boolean typeCourseIdentique = chauffeur.getTypeCourse().equals(passager.getTypeCourse());

        // Comparaison des points de dépôt finaux
        Point depotChauffeur = getDernierPoint(chauffeur.getItineraire());
        Point depotPassager = getDernierPoint(passager.getItineraire());

        if (depotChauffeur == null || depotPassager == null) return false;

        double dx = chauffeur.getPositionActuelle().getLatitude() - passager.getPositionActuelle().getLatitude();
        double dy = chauffeur.getPositionActuelle().getLongitude() - passager.getPositionActuelle().getLongitude();
        double distance = Math.sqrt(dx * dx + dy * dy);

        double dxDepot = depotChauffeur.getLatitude() - depotPassager.getLatitude();
        double dyDepot = depotChauffeur.getLongitude() - depotPassager.getLongitude();
        double distanceDepot = Math.sqrt(dxDepot * dxDepot + dyDepot * dyDepot);

        return prefsCompatibles && typeCourseIdentique && distance <= DISTANCE_MAX && distanceDepot <= DISTANCE_MAX;
    }

    private static Point getDernierPoint(Itineraire itineraire) {
        if (itineraire.getPointsDepot() == null || itineraire.getPointsDepot().isEmpty()) return null;
        return itineraire.getPointsDepot().get(itineraire.getPointsDepot().size() - 1);
    }
}
