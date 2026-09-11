package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Une seule méthode vous est donnée, count(). Inspirez-vous de cet exemple pour écrire
 * les quatre opérations que le PO vous demande (ajouter, consulter, corriger,
 * retirer).
 *
 * Le fonctionnement d'un PreparedStatement est expliqué dans le README de ce dossier.
 */
public class StudentDao {

    /***
     * C'est la requête la plus simple du projet, elle sert de
     * premier test : si elle répond, c'est que la base tourne et est accessible depuis ce projet.
     */
    public int count() throws SQLException {
        String sql = "SELECT count(*) as nbr_student FROM t_student";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt("nbr_student");
        }
    }
}
