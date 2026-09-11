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

    private static final String SELECT_COLUMNS =
            "id, first_name, pokemon_name, level, type, health_point, height, is_flying, "
                    + "evolution, natural_environment, gift, gift_cost, gift_damage, gift_text, "
                    + "strength, strength_cost, strength_damage, strength_text, weakness, quote, "
                    + "catch_date, avatar_url";

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

    /**
     * US 5 — Permet d'ajouter une fiche.
     */


    /**
     * US 6 — Récupère une fiche en filtrant par le prénom (first_name).
     */
    public Student findByFirstName(String firstName) throws SQLException {
        String sql = "SELECT " + SELECT_COLUMNS + " FROM t_student WHERE first_name = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                return null;
            }
        }
    }


    /**
     * US 7 — Récupère toutes les fiches.
     */

    public List<Student> getStudents() throws SQLException {
        String sql = "SELECT * FROM t_student ORDER BY id";
        List<Student> students = new ArrayList<>();

        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                students.add(mapRow(resultSet));
            }
        }
        return students;
    }

    /**
     * US 8 — Permet de modifier une fiche en filtrant par l'id (id).
     */


    /**
     * US 9 — Permet de Supprimer une fiche.
     */


    /** Transforme une ligne du ResultSet en Student. */
    private Student mapRow(ResultSet resultSet) throws SQLException {
        return new Student(
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
                resultSet.getString("avatar_url"));
    }
}
