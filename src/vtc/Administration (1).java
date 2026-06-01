package vtc;




import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Classe pour gérer l'administration de l'application
public class Administration {
 private CourseManager courseManager; // Référence à l'objet qui gère les courses
 
    // Liste pour stocker tous les utilisateurs
    private ArrayList<Utilisateur> utilisateurs;
    
 ///Gérer les utilisateurs
 
    // Méthode pour ajouter un utilisateur
    // Méthode pour ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        // Évite d'ajouter un doublon basé sur le matricule
        for (Utilisateur u : utilisateurs) {
            if (u.getMatricule().equals(utilisateur.getMatricule())) {
                return; // utilisateur déjà présent
            }
        }
        utilisateurs.add(utilisateur);
    }

    
    
 // Méthode pour supprimer un utilisateur
    public void supprimerUtilisateur(String matricule) {
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).getMatricule().equals(matricule)) {
                utilisateurs.remove(i);
                break;
            }
        }
    }
 // Méthode pour afficher tous les utilisateurs
    public void afficherUtilisateurs() {
        System.out.println("--- Liste des utilisateurs ---");
        
        // Parcourir et afficher les informations de chaque utilisateur
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);
            System.out.println(u.getNom() + " " + u.getPrenom() + " - Matricule : " + u.getMatricule()+"Banni: "+(u.isEstBanni()?"oui":"non"));
        }
    }
    //constructeur explicite  
    public Administration(ArrayList<Utilisateur> utilisateurs) {
        this.courseManager = new CourseManager(); // Initialisation du gestionnaire de courses
        if (utilisateurs == null) {
            this.utilisateurs = new ArrayList<>(); // Si la liste est nulle, on initialise une liste vide
        } else {
            this.utilisateurs = utilisateurs; // Sinon, on utilise la liste fournie
        }
    }
    //Méthode pour ajouter une nouvelle course (utilise CourseManager)
    public void ajouterCourse(Course c) {
        courseManager.ajouterCourse(c);
    }
 ///Visualiser les courses en cours l'instant actuel
 
    public void afficherCoursesEnCours(LocalDateTime maintenant) {
        ArrayList<Course> enCours = courseManager.visualiserCoursesEnCours(maintenant);
        System.out.println("=== Courses en cours ===");
        for (int i = 0; i < enCours.size(); i++) {
            System.out.println(enCours.get(i));
        }
    }

 ///Visualiser le planning journalier pour une date donnée
    
    public void afficherPlanningJournalier(LocalDate date) {
        ArrayList<Course> journalier = courseManager.visualiserPlanningJournalier(date);
        System.out.println("=== Planning Journalier ===");
        for (int i = 0; i < journalier.size(); i++) {
            System.out.println(journalier.get(i));
        }
    }
 
///Visualiser l'historique des courses passées
    
    public void afficherHistoriqueCourses(LocalDateTime maintenant) {
        ArrayList<Course> historique = courseManager.visualiserHistoriqueCourses(maintenant);
        System.out.println("=== Historique des courses ===");
        for (int i = 0; i < historique.size(); i++) {
            System.out.println(historique.get(i));
        }
    }
  
 
 ///Gérer les chauffeurs et passagers à bannir   
    
// Méthode pour bannir un utilisateur selon son matricule
    public void bannirUtilisateur(String matricule) {
        
        // Chercher l'utilisateur avec le matricule donné
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);
            
            if (u.getMatricule().equals(matricule)) {
             u.bannir();
                
                return; // Sortir après la suppression
            }
        }
        
        // Si aucun utilisateur trouvé avec ce matricule
        System.out.println("Aucun utilisateur trouvé avec ce matricule.");
    } 
    
    
   }