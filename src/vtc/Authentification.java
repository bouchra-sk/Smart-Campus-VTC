package vtc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Authentification {
  static class CompteUtilisateur {  // Corrected spelling to match all references
        private String email;
        private String motDePasse;
        private Utilisateur utilisateur;

        public CompteUtilisateur(String email, String motDePasse, Utilisateur utilisateur) {
            this.email = email;
            this.motDePasse = motDePasse;
            this.utilisateur = utilisateur;
        }
    }
 static List<CompteUtilisateur> comptes = new ArrayList<>();

    // Method to create an account
    public static Utilisateur creerCompte(Scanner scanner, ArrayList<Utilisateur> utilisateurs, Administration adminSystem) {
        System.out.println("\n=== Création de compte ===");
        
        // Saisie des informations
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        // Création du type d'utilisateur
        System.out.println("Type (1. Étudiant, 2. Enseignant, 3. ATS) : ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Utilisateur user = null;
        switch (type) {
            case 1 -> {
                System.out.print("Année admission : ");
                int annee = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Faculté : ");
                String fac = scanner.nextLine();
                System.out.print("Spécialité : ");
                String spec = scanner.nextLine();
                user = new Etudiant(nom, prenom, matricule, annee, fac, spec);
            }
            case 2 -> {
                System.out.print("Année recrutement : ");
                int annee = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Faculté : ");
                String fac = scanner.nextLine();
                user = new Enseignant(nom, prenom, matricule, annee, fac);
            }
            case 3 -> {
                System.out.print("Année recrutement : ");
                int annee = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Service : ");
                String service = scanner.nextLine();
                user = new ATS(nom, prenom, matricule, service, annee);
            }
            default -> {
                System.out.println("Type invalide");
                return null;
            }
        }

        // Enregistrement
        user.setEmail(email);
        user.setMotDePasse(motDePasse);
        utilisateurs.add(user);
        adminSystem.ajouterUtilisateur(user);
        comptes.add(new CompteUtilisateur(email, motDePasse, user));
        System.out.println("Compte créé avec succès !");
        return user;
    }

    // Method for user login
    public static Utilisateur seConnecterUtilisateur(Scanner scanner, ArrayList<Utilisateur> utilisateurs) {
        System.out.println("\n=== Connexion Utilisateur ===");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        for (CompteUtilisateur compte : comptes) {
            if (compte.email.equals(email) && compte.motDePasse.equals(mdp)) {
                Utilisateur user = compte.utilisateur;
                if (!(user instanceof Admin)) {
                    System.out.println("Connexion réussie !");
                    return user;
                }
            }
        }
        System.out.println("Identifiants incorrects ou compte non trouvé");
        return null;
    }

    // Method for admin login
    public static Utilisateur seConnecterAdmin(Scanner scanner) {
        System.out.println("\n=== Connexion Admin ===");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        for (CompteUtilisateur compte : comptes) {
            if (compte.email.equals(email) && compte.motDePasse.equals(mdp) && compte.utilisateur instanceof Admin) {
                System.out.println("Connexion admin réussie !");
                return compte.utilisateur;
            }
        }
        System.out.println("Identifiants admin incorrects");
        return null;
    }

    // Helper methods
    private static boolean emailExiste(String email) {
        return comptes.stream().anyMatch(compte -> compte.email.equals(email));
    }
}
