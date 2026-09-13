import data.StudentDao;
import model.PokemonHeight;
import model.PokemonType;
import model.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Point d'entrée du Trombifunscope.
 *
 * Une règle : aucune requête SQL ici. Le sql est porté par StudentDao.
 * Le main appelle juste les méthode du DAO et affiche le résultat.
 */
public class Main {

    private static final StudentDao dao = new StudentDao();

    public static void main(String[] args) {
        System.out.println("\n=== Trombifunscope ===\n");

        try {

            // Initialisation de la base de données avec la méthode count()
            System.out.println("Liaison établie. Test : " + dao.count()
                    + " ligne(s) dans la table student.");

            // US 6 — consulter une fiche par prénom
            System.out.println("\n## US 6 - consulter une fiche par prénom ##\n");
            Student found = dao.findByFirstName("Dominga");
            if (found == null) {
                System.out.println("Aucune fiche pour ce prénom.");
            } else {
                printDetail(found);
            }
            System.out.println();

            // US 7 — Lister toutes les fiches
            System.out.println("\n## US 7 - Lister toutes les fiches ##\n");
            List<Student> students = dao.getStudents();
            for (Student student : students) {
                System.out.println(student);
            }

            // Création d'une nouvelle fiche
            Student student = new Student(
                    null,
                    "Sacha",
                    "Pikachu",
                    25,
                    PokemonType.ELECTRIK,
                    100,
                    PokemonHeight.M,
                    false,
                    "Raichu",
                    "Forêt",
                    "Tonnerre",
                    2,
                    50,
                    "Une attaque électrique.",
                    "Électricité",
                    3,
                    80,
                    "Une attaque très puissante.",
                    "Sol",
                    "Pika Pika !",
                    null,
                    "https://example.com/pikachu.png"
            );

            // INSERT en base
            dao.add(student);

            // Vérification
            System.out.println("Fiche ajoutée !");
            System.out.println("Après insertion : " + dao.count()
                    + " ligne(s) dans la table student.");

        } catch (SQLException e) {
            System.out.println("La base n'a pas répondu : " + e.getMessage());
        }
    }

    private static void printDetail(Student s) {
        if (s == null) {
            return;
        }
        String typeLabel = s.getType() == null ? "?" : s.getType().getLabel();

        System.out.println(s);
        System.out.println("  type      = " + typeLabel);
        System.out.println("  niveau    = " + s.getLevel());
        System.out.println("  PV        = " + s.getHealthPoint());
        System.out.println("  taille    = " + s.getHeight());
        System.out.println("  volant    = " + s.isFlying());
        System.out.println("  évolution = " + s.getEvolution());
        System.out.println("  milieu    = " + s.getNaturalEnvironment());
        System.out.println("  don       = " + s.getGift()
                + " (coût " + s.getGiftCost() + ", dégâts " + s.getGiftDamage() + ") — "
                + s.getGiftText());
        System.out.println("  force     = " + s.getStrength()
                + " (coût " + s.getStrengthCost() + ", dégâts " + s.getStrengthDamage() + ") — "
                + s.getStrengthText());
        System.out.println("  faiblesse = " + s.getWeakness());
        System.out.println("  citation  = \"" + s.getQuote() + "\"");
        System.out.println("  capture   = " + s.getCatchDate());
        System.out.println("  avatar    = " + s.getAvatarUrl());
    }
}
