package vtc;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


public class HistoriqueCourse {

    private final String courseId;
    private Utilisateur chauffeur;
    private ArrayList<Utilisateur> passagers;
    private ArrayList<Evaluation> evaluations;
    private LocalDateTime date;
    private TypeCourse typeCourse;
    private StatutCourse statutFinal;

    // Constructeur manuel
    public HistoriqueCourse(Utilisateur chauffeur, ArrayList<Utilisateur> passagers, StatutCourse statutFinal) {
        this.courseId = UUID.randomUUID().toString();
        this.chauffeur = chauffeur;
        this.passagers = passagers != null ? passagers : new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.statutFinal = statutFinal;
        this.date = LocalDateTime.now();
    }

    // Constructeur basé sur un objet Course
    public HistoriqueCourse(Course course) {
        this.courseId = UUID.randomUUID().toString();
        this.chauffeur = course.getChauffeur();
        this.passagers = new ArrayList<>(course.getPassagers());
        this.evaluations = new ArrayList<>();
        this.typeCourse = course.getTypeCourse();
        this.statutFinal = course.getStatutCourse();
        this.date = course.getDateHeureFin(); // Ou .getDateHeureDepart() selon le besoin
    }
   /////// HistoriqueCourse hist = new HistoriqueCourse(courseTerminee);
   ////// utilisateur.getHistoriqueCourses().add(hist);

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public Utilisateur getChauffeur() {
        return chauffeur;
    }

    public ArrayList<Utilisateur> getPassagers() {
        return passagers;
    }

    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public StatutCourse getStatutFinal() {
        return statutFinal;
    }

    // Méthode pour ajouter une évaluation
    public void ajouterEvaluation(Evaluation evaluation) {
        if (evaluation != null) {
            this.evaluations.add(evaluation);
        }
    }
}
