 package vtc;

import java.util.Date;

public class Evaluation {
    private float note;
    private String commentaire;
    private final Utilisateur auteur;
    private final Utilisateur cible;
    private final Course course;
    private final Date dateCreation;
    private Date dateModification;

    public Evaluation(float note, String commentaire, Utilisateur auteur, 
                     Utilisateur cible, Course course) {
        setNote(note);  // Utilisation du setter pour la validation
        this.commentaire = commentaire;
        this.auteur = auteur;
        this.cible = cible;
        this.course = course;
        this.dateCreation = new Date();
        this.dateModification = new Date();
    }
    public Evaluation(float note, String commentaire, Utilisateur auteur, Utilisateur cible) {
        this(note, commentaire, auteur, cible, null);
    }//ajouter constracteur


    // Setters avec contrôle d'accès
    public void setNote(float note) {
        if (note < 1 || note > 5) {
           System.out.println("La note doit être entre 1 et 5");
        }
        this.note = note;
        this.dateModification = new Date();
    }

    public void setCommentaire(String commentaire, Utilisateur demandeur) {
        if (!demandeur.equals(this.auteur)) {
        	 System.out.println("Seul l'auteur peut modifier le commentaire");
        }
        this.commentaire = commentaire;
        this.dateModification = new Date();
    }
   

    // Getters
    public float getNote() { return note; }
    public String getCommentaire() { return commentaire; }
    public Utilisateur getAuteur() { return auteur; }
    public Utilisateur getCible() { return cible; }
    public Course getCourse() { return course; }
    public Date getDateCreation() { return dateCreation; }
    public Date getDateModification() { return dateModification; }
}