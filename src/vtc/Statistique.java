package vtc;



import java.util.ArrayList;

// Classe pour gérer les statistiques
public class Statistique {
    // Liste pour stocker tous les utilisateurs
    private ArrayList<Utilisateur> utilisateurs;
    private int nbEtudiants = 0;
    private int nbEnseignants = 0;
    private int nbATS = 0;
    private int total = 0;

    // Constructeur qui prend une liste d'utilisateurs
    public Statistique(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    // Méthode pour compter les types d'utilisateurs
    public void compterUtilisateurs() {
     
        // Parcourir la liste
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);

            // Vérifier le type de chaque utilisateur
            if (u instanceof Etudiant) {
                nbEtudiants++;
            } else if (u instanceof Enseignant) {
                nbEnseignants++;
            } else if (u instanceof ATS) {
                nbATS++;
            }
            total++; // Compter tous les utilisateurs
        }

        // Afficher les résultats
        System.out.println("--- STATISTIQUES ---");
        System.out.println("Nombre d'étudiants: " + nbEtudiants);
        System.out.println("Nombre d'enseignants: " + nbEnseignants);
        System.out.println("Nombre d'ATS: " + nbATS);
        System.out.println("Nombre total d'utilisateurs: " + total);
    }
    public void cat_laplus_course() {
    	if((nbEtudiants>nbEnseignants)&&(nbEtudiants>nbATS)) {
    		System.out.println(" catégories proposent plus de courses est les étudiants avec:"+nbEtudiants+"étudiants");
    	}else if(nbATS >nbEnseignants) {
    		System.out.println(" catégories proposent plus de courses est les ATS avec:"+nbATS+"ATS");
    	}else {
    		System.out.println(" catégories proposent plus de courses est les enseignants avec:"+nbEnseignants+"enseignants");
	
    	}
    }

    // Méthode pour afficher les utilisateurs avec une mauvaise réputation
    public void utilisateursFaibleReputation() {
        System.out.println("Utilisateurs avec une faible réputation (<2/5) : ");

        // Parcourir tous les utilisateurs
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);

            // Si la réputation est inférieure à 2
            if (u.getReputation() < 2.0) {
                // Afficher ses informations
                System.out.println(u.getNom() + " " + u.getPrenom() + " - Réputation : " + u.getReputation());
            }
        }
    }
    public void top10() {
    	// Créer une liste pour stocker les chauffeurs
        ArrayList<Utilisateur> chauffeurs = new ArrayList<>();
    	
    	// Trier les chauffeurs par réputation de la plus élevée à la plus basse
    	System.out.println("Top 10 des chauffeurs : ");
    	
    	// Filtrer les utilisateurs ayant le statut "CHAUFFEUR"
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i); // Accéder à l'utilisateur à l'indice i
            if (u.getStatut() == StatutUtilisateur.CHAUFFEUR) {
                chauffeurs.add(u); // Ajouter le chauffeur à la liste
            }
        }
        
     // Trier les chauffeurs par réputation
    	for (int i = 0; i < chauffeurs.size(); i++) {
    	    for (int j = i + 1; j < chauffeurs.size(); j++) {
    	        // Si la réputation du chauffeur à l'index j est plus grande que celle du chauffeur à l'index i
    	        if (chauffeurs.get(i).getReputation() < chauffeurs.get(j).getReputation()) {
    	            // Échanger les positions des chauffeurs i et j
    	            Utilisateur temp = chauffeurs.get(i);
    	            chauffeurs.set(i, chauffeurs.get(j));
    	            chauffeurs.set(j, temp);
    	        }
    	    }
    	}

    	// Afficher les 10 premiers chauffeurs 
    	int count = 0;
    	for (int i = 0; i < chauffeurs.size(); i++) {
    	    if (count == 10) break; // Si on a déjà affiché 10 chauffeurs, sortir de la boucle
    	    Utilisateur chauffeur = chauffeurs.get(i);
    	    // Afficher le nom, prénom et la réputation de chaque chauffeur
    	    System.out.println((i + 1) + ". " + chauffeur.getNom() + " " + chauffeur.getPrenom() + " - Réputation : " + chauffeur.getReputation());
    	    count++;
    	}

    }
    public void pire_utilisateurs() {
        ArrayList<Utilisateur> chauffeurs = new ArrayList<>();
        ArrayList<Utilisateur> passagers = new ArrayList<>();

        // Remplir les deux listes avec les bons utilisateurs
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);
            if (u.getStatut() == StatutUtilisateur.CHAUFFEUR) {
                chauffeurs.add(u);
            } else if (u.getStatut() == StatutUtilisateur.PASSAGER) {
                passagers.add(u);
            }
        }

        // Afficher les chauffeurs/passagers à bannir
        System.out.println("Liste des chauffeurs/passagers à bannir :");

        int count = 0;
        
        // Vérifier dans les chauffeurs
        for (int i = 0; i < chauffeurs.size(); i++) {
            Utilisateur chauffeur = chauffeurs.get(i);
            if (chauffeur.getReputation() < 2.0) {
                System.out.println("- " + chauffeur.getNom() + " " + chauffeur.getPrenom() + " - Réputation : " + chauffeur.getReputation());
                count++;
            }
        }
        
        // Vérifier dans les passagers
        for (int i = 0; i < passagers.size(); i++) {
            Utilisateur passager = passagers.get(i);
            if (passager.getReputation() < 2.0) {
                System.out.println("- " + passager.getNom() + " " + passager.getPrenom() + " - Réputation : " + passager.getReputation());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Aucun chauffeur/passager à bannir.");
        }
    }

}