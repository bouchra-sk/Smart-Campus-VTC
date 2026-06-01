package vtc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Cette classe permet de gérer toutes les courses ajoutées dans le système.
public class CourseManager {
    private ArrayList<Course> toutesLesCourses; // Liste de toutes les courses enregistrées

    public CourseManager() {
        this.toutesLesCourses = new ArrayList<Course>(); // Initialisation de la liste
    }

    // Méthode pour ajouter une nouvelle course à la liste
    public void ajouterCourse(Course c) {
        toutesLesCourses.add(c);
    }

    //Méthode pour obtenir les courses en cours (celles dont l'heure actuelle est entre départ et arrivée)
    public ArrayList<Course> visualiserCoursesEnCours(LocalDateTime maintenant) {
        ArrayList<Course> enCours = new ArrayList<Course>();

        for (int i = 0; i < toutesLesCourses.size(); i++) {
            Course c = toutesLesCourses.get(i);
            c.infosurcourse(); // Mise à jour du statut selon le moment actuel

            if (c.getStatutCourse() == StatutCourse.EN_COURS) {
                enCours.add(c);
            }
        }

        return enCours;
    }

    // Méthode pour obtenir les courses prévues pour un jour précis
    public ArrayList<Course> visualiserPlanningJournalier(LocalDate date) {
        ArrayList<Course> journalier = new ArrayList<Course>();

        for (int i = 0; i < toutesLesCourses.size(); i++) {
            Course c = toutesLesCourses.get(i);

            if (c.getDateHeureDepart().toLocalDate().equals(date)) {
                journalier.add(c); // Ajouter si la date de départ correspond à la date demandée
            }
        }

        return journalier;
    }

    // Méthode pour obtenir l'historique des courses terminées
    public ArrayList<Course> visualiserHistoriqueCourses(LocalDateTime maintenant) {
        ArrayList<Course> historique = new ArrayList<Course>();

        for (int i = 0; i < toutesLesCourses.size(); i++) {
            Course c = toutesLesCourses.get(i);
            c.infosurcourse(); // Mise à jour du statut selon le moment actuel
            if (c.getStatutCourse() == StatutCourse.TERMINEE) {
             historique.add(c);
            }
           
        }

        return historique;
    }
}