package university.covoiturage.USTHB;

public class Preferences {
    // Préférences de base
    private boolean musique;
    private boolean bagages;
    private Genre genrePreference;
    private String typeVehicule; // "voiture" ou "moto"
    private boolean fumeur;
    private int nombrePassagerMax;
    
    // Paramètres d'accessibilité
    private boolean sourdOuMalentendant;
    private boolean avecChienGuide;
    private boolean mobiliteReduite;
    private boolean muteOuDifficulteExpression;
    private boolean enFauteuilRoulant;

    // Constructeur
    public Preferences() {
    	this.genrePreference = Genre.Indifférent;
        this.typeVehicule = "voiture";
        this.nombrePassagerMax = 1;
    }

    // Getters et Setters
    public Genre getGenrePreference() { return genrePreference; }
    public boolean isMusique() { return musique; }
    public boolean isBagages() { return bagages; }
    public boolean isFumeur() { return fumeur; }
    public int getNombrePassagerMax() { return nombrePassagerMax; }
    public String getTypeVehicule() { return typeVehicule; }

    public void setGenrePreference(Genre genrePreference) {
        this.genrePreference = genrePreference;
    }

    
    public void setMusique(boolean musique) { this.musique = musique; }
    public void setBagages(boolean bagages) { this.bagages = bagages; }
    public void setFumeur(boolean fumeur) { this.fumeur = fumeur; }
    
    public void setNombrePassagerMax(int nombrePassagerMax) {
        if (nombrePassagerMax > 0 && nombrePassagerMax <= 4) {
            this.nombrePassagerMax = nombrePassagerMax;
        }
    }
    
    public void setTypeVehicule(String typeVehicule) {
        if (typeVehicule.equals("voiture") || typeVehicule.equals("moto")) {
            this.typeVehicule = typeVehicule;
        }
    }

    // Accessibilité
    public boolean isSourdOuMalentendant() { return sourdOuMalentendant; }
    public boolean isAvecChienGuide() { return avecChienGuide; }
    public boolean isMuteOuDifficulteExpression() { return muteOuDifficulteExpression; }
    public boolean isEnFauteuilRoulant() { return enFauteuilRoulant; }

    public void setSourdOuMalentendant(boolean value) { this.sourdOuMalentendant = value; }
    public void setAvecChienGuide(boolean value) { this.avecChienGuide = value; }
    public void setMobiliteReduite(boolean value) { this.mobiliteReduite = value; }
    public void setMuteOuDifficulteExpression(boolean value) { this.muteOuDifficulteExpression = value; }
    public void setEnFauteuilRoulant(boolean value) { this.enFauteuilRoulant = value; }

    // Compatibilité
    public boolean estCompatibleAvec(Preferences autre) {
        if (!this.genrePreference.equals("Indifférent") && 
            !autre.genrePreference.equals("Indifférent") &&
            !this.genrePreference.equals(autre.genrePreference)) {
            return false;
        }
        
        if (!this.musique && autre.musique) return false;
        if (!this.fumeur && autre.fumeur) return false;
        if (!this.typeVehicule.equals(autre.typeVehicule)) return false;
        if (this.enFauteuilRoulant && !"voiture".equals(this.typeVehicule)) return false;
        if (this.avecChienGuide && autre.fumeur) return false;
        
        return true;
    }

    // Représentation textuelle
    @Override
    public String toString() {
        String access = (sourdOuMalentendant ? "Sourd/Malentendant " : "") +
                       (avecChienGuide ? "Chien guide " : "") +
                       (mobiliteReduite ? "Mobilité réduite " : "") +
                       (muteOuDifficulteExpression ? "Difficulté d'expression " : "") +
                       (enFauteuilRoulant ? "Fauteuil roulant" : "");
        
        return "Préférences [" +
               "Genre: " + genrePreference +
               ", Musique: " + (musique ? "Oui" : "Non") +
               ", Bagages: " + (bagages ? "Oui" : "Non") +
               ", Fumeur: " + (fumeur ? "Oui" : "Non") +
               ", Véhicule: " + typeVehicule +
               ", Passagers: " + nombrePassagerMax +
               ", Accessibilité: " + access.trim() + "]";
    }
}