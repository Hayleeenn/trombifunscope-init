package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.PokemonHeight;
import model.PokemonType;
import model.Student;

/**
 * Une seule méthode vous est donnée, count(). Inspirez-vous de cet exemple pour
 * écrire
 * les quatre opérations que le PO vous demande (ajouter, consulter, corriger,
 * retirer).
 *
 * Le fonctionnement d'un PreparedStatement est expliqué dans le README de ce
 * dossier.
 */
public class StudentDao {

    /***
     * C'est la requête la plus simple du projet, elle sert de
     * premier test : si elle répond, c'est que la base tourne et est accessible
     * depuis ce projet.
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

  public List<Student> getStudents() throws SQLException {
    String sql = "SELECT * FROM t_student ORDER BY id";
    List<Student> students = new ArrayList<>();

    try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            students.add(new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("pokemon_name"),
                    resultSet.getObject("level", Integer.class),
                    PokemonType.fromLabel(resultSet.getString("type")),
                    resultSet.getObject("health_point", Integer.class),
                    PokemonHeight.fromLabel(resultSet.getString("height")),
                    resultSet.getBoolean("is_flying"),
                    resultSet.getString("evolution"),
                    resultSet.getString("natural_environment"),
                    resultSet.getString("gift"),
                    resultSet.getObject("gift_cost", Integer.class),
                    resultSet.getObject("gift_damage", Integer.class),
                    resultSet.getString("gift_text"),
                    resultSet.getString("strength"),
                    resultSet.getObject("strength_cost", Integer.class),
                    resultSet.getObject("strength_damage", Integer.class),
                    resultSet.getString("strength_text"),
                    resultSet.getString("weakness"),
                    resultSet.getString("quote"),
                    resultSet.getObject("catch_date", LocalDate.class),
                    resultSet.getString("avatar_url")));
        }
    }
    return students;
}
}
