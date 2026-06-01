package vtc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Données en mémoire
    private static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static Administration adminSystem = new Administration(utilisateurs);
    
    public static void main(String[] args) {
        // Initialisation avec un admin par défaut
        Admin admin = new Admin("Admin", "System", "ADMIN001", "admin@usthb.dz", "admin123");
        utilisateurs.add(admin);
        adminSystem.ajouterUtilisateur(admin);
        Authentification.comptes.add(new Authentification.CompteUtilisateur(
            "admin@usthb.dz",
            "admin123",
            admin
        ));
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Application de Covoiturage USTHB ===");
            Utilisateur utilisateur = null;
            
            while (utilisateur == null) {
                System.out.println("\n1. Créer un compte");
                System.out.println("2. Se connecter (Utilisateur)");
                System.out.println("3. Se connecter (Admin)");
                System.out.println("4. Quitter");
                System.out.print("Choix : ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1 -> 
                        utilisateur = Authentification.creerCompte(scanner, utilisateurs, adminSystem);
                    case 2 -> 
                        utilisateur = Authentification.seConnecterUtilisateur(scanner, utilisateurs);
                    case 3 -> {
                        utilisateur = Authentification.seConnecterAdmin(scanner);
                        if (utilisateur != null) {
                            menuAdmin(scanner, (Admin)utilisateur);
                            utilisateur = null; // Retour au menu principal
                        }
                    }
                    case 4 -> {
                        System.out.println("Au revoir !"); 
                        System.exit(0);
                    }
                    default -> System.out.println("Option invalide");
                }
            }

            if (utilisateur != null && !(utilisateur instanceof Admin)) {
                System.out.println("\nBienvenue " + utilisateur.getPrenom() + " " + utilisateur.getNom());
                menuUtilisateur(scanner, utilisateur);
            }
        }
    }

    private static void menuAdmin(Scanner scanner, Admin admin) {
        Statistique stats = new Statistique(utilisateurs);
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Gérer utilisateurs");
            System.out.println("2. Gérer courses");
            System.out.println("3. Voir statistiques");
            System.out.println("0. Déconnexion");
            System.out.print("Choix : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> gererUtilisateurs(scanner);
                case 2 -> gererCourses(scanner);
                case 3 -> voirStatistiques(scanner, stats);
                case 0 -> running = false;
                default -> System.out.println("Option invalide");
            }
        }
    }

    private static void gererUtilisateurs(Scanner scanner) {
        System.out.println("\n1. Lister utilisateurs");
        System.out.println("2. Supprimer utilisateur");
        System.out.println("3. Bannir utilisateur");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (subChoice == 1) {
            adminSystem.afficherUtilisateurs();
        } else if (subChoice == 2) {
            System.out.print("Matricule à supprimer : ");
            adminSystem.supprimerUtilisateur(scanner.nextLine());
        } else if (subChoice == 3) {
            System.out.print("Matricule à bannir : ");
            adminSystem.bannirUtilisateur(scanner.nextLine());
        }
    }

    private static void gererCourses(Scanner scanner) {
        System.out.println("\n1. Courses en cours");
        System.out.println("2. Planning journalier");
        System.out.println("3. Historique courses");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (subChoice == 1) {
            adminSystem.afficherCoursesEnCours(LocalDateTime.now());
        } else if (subChoice == 2) {
            System.out.print("Date (AAAA-MM-JJ) : ");
            adminSystem.afficherPlanningJournalier(LocalDate.parse(scanner.nextLine()));
        } else if (subChoice == 3) {
            adminSystem.afficherHistoriqueCourses(LocalDateTime.now());
        }
    }

    private static void voirStatistiques(Scanner scanner, Statistique stats) {
        System.out.println("\n1. Statistiques générales");
        System.out.println("2. Catégorie la plus active");
        System.out.println("3. Top 10 chauffeurs");
        System.out.println("4. Utilisateurs à bannir");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (subChoice == 1) {
            stats.compterUtilisateurs();
        } else if (subChoice == 2) {
            stats.cat_laplus_course();
        } else if (subChoice == 3) {
            stats.top10();
        } else if (subChoice == 4) {
            stats.pire_utilisateurs();
        }
    }

    private static void menuUtilisateur(Scanner scanner, Utilisateur user) {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MENU UTILISATEUR ===");
            System.out.println("1. Commencer une course");
            System.out.println("2. Voir réputation");
            System.out.println("0. Déconnexion");
            System.out.print("Choix : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> demarrerCourse(scanner, user);
                case 2 -> System.out.println("Votre réputation : " + user.getReputation());
                case 0 -> running = false;
                default -> System.out.println("Option invalide");
            }
        }
    }
//modifier
    private static void demarrerCourse(Scanner scanner, Utilisateur user) {
        System.out.println("\n1. Conducteur\n2. Passager");
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        if (choix == 1) {
            // Conducteur
            Preferences prefsConducteur = Preferences.saisirPreferencesConducteur(scanner);
            user.setPreferences(prefsConducteur);
            
            
            
            Itineraire itineraire = Course.saisirItineraire(scanner);
            Course course = new Course(user, new ArrayList<>(), itineraire, 
                                    LocalDateTime.now(), null, TypeCourse.ALLER_SIMPLE, 
                                    StatutCourse.EN_COURS);
            courses.add(course);
            adminSystem.ajouterCourse(course);
            System.out.println("Course créée !");
        } else {
            // Passager
            Preferences prefsPassager = Preferences.saisirPreferencesPassager(scanner);
            user.setPreferences(prefsPassager);
            
            System.out.print("Latitude : ");
            float lat = scanner.nextFloat();
            System.out.print("Longitude : ");
            float lon = scanner.nextFloat();
            scanner.nextLine();
            
            Utilisateur chauffeur = trouverChauffeurCompatible(user, utilisateurs);
            if (chauffeur != null) {
                System.out.println("Chauffeur trouvé : " + chauffeur.getNom());
                // Démarrer la course avec ce chauffeur
            } else {
                System.out.println("Aucun chauffeur disponible");
            }
        }
    }

   

    public static Utilisateur trouverChauffeurCompatible(Utilisateur passager, List<Utilisateur> utilisateurs) {
        if (passager == null || utilisateurs == null || utilisateurs.isEmpty()) {
            return null;
        }

        for (Utilisateur chauffeur : utilisateurs) {
            // Vérifie les critères de base + compatibilité via Match.estCompatible()
            if (chauffeur != null &&
                chauffeur.getStatutUtilisateur() == StatutUtilisateur.CHAUFFEUR &&
                !chauffeur.isEstBanni() &&
                chauffeur.getReputation() >= 2.5 &&
                Match.estCompatible(chauffeur, passager)) {
                
                // Ajoute le passager à la course du chauffeur (si applicable)
                // Note : Supposons que le chauffeur a une course en attente.
                Course courseChauffeur = chauffeur.getCourseActuelle();
                if (courseChauffeur != null) {
                    courseChauffeur.ajouterPassager(passager);
                    return chauffeur;
                }
            }
        }
        return null; // Aucun chauffeur compatible trouvé
    }
}

class Admin extends Utilisateur {
    public Admin(String nom, String prenom, String matricule, String email, String mdp) {
        super(nom, prenom, matricule);
        setEmail(email);
        setMotDePasse(mdp);
    }
}