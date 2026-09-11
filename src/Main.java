import data.StudentDao;
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
        System.out.println("=== Trombifunscope ===");

        try {
            System.out.println("Liaison établie. Test : " + dao.count()
                    + " ligne(s) dans la table student.");

            // US 6 — consulter une fiche par prénom
            System.out.println("--- Fiche filtrée par prénom (Raphael) ---");
            Student found = dao.findByFirstName("Raphael");
            if (found == null) {
                System.out.println("Aucune fiche pour ce prénom.");
            } else {
                printDetail(found);
            }
            System.out.println();

            // US 7 — Lister toutes les fiches
            List<Student> students = dao.getStudents();
            for (Student student : students) {
                System.out.println(student);
            }

        } catch (SQLException e) {
            System.out.println("La base n'a pas répondu : " + e.getMessage());
        }
    }

    private static void printDetail(Student s) {
        if (s == null) {
            return;
        }
        System.out.println(s);
        System.out.println("  type=" + s.getType().getLabel()
                + ", niveau=" + s.getLevel()
                + ", PV=" + s.getHealthPoint()
                + ", taille=" + s.getHeight()
                + ", volant=" + s.isFlying());
        System.out.println("  évolution=" + s.getEvolution()
                + ", milieu=" + s.getNaturalEnvironment());
        System.out.println("  don=" + s.getGift()
                + " (coût " + s.getGiftCost() + ", dégâts " + s.getGiftDamage() + ") — "
                + s.getGiftText());
        System.out.println("  force=" + s.getStrength()
                + " (coût " + s.getStrengthCost() + ", dégâts " + s.getStrengthDamage() + ") — "
                + s.getStrengthText());
        System.out.println("  faiblesse=" + s.getWeakness()
                + ", citation=\"" + s.getQuote() + "\""
                + ", capture=" + s.getCatchDate()
                + ", avatar=" + s.getAvatarUrl());
    }
}
