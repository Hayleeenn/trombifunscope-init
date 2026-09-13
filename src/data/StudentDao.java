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
 * Une seule méthode vous est donnée, count().
 * Inspirez-vous de cet exemple pour
 * écrire les quatre opérations que le PO vous demande
 * (ajouter, consulter, corriger, retirer).
 *
 * Le fonctionnement d'un PreparedStatement est expliqué
 * dans le README de ce dossier.
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
    public void add(Student student) throws SQLException {
        String sql = """
        INSERT INTO t_student (
            first_name,
            pokemon_name,
            level,
            type,
            health_point,
            height,
            is_flying,
            evolution,
            natural_environment,
            gift,
            gift_cost,
            gift_damage,
            gift_text,
            strength,
            strength_cost,
            strength_damage,
            strength_text,
            weakness,
            quote,
            catch_date,
            avatar_url
        )
        VALUES (
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        )
        """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // first_name
            statement.setString(1, student.getFirstName());

            // pokemon_name
            statement.setString(2, student.getPokemonName());

            // level
            if (student.getLevel() != null) {
                statement.setInt(3, student.getLevel());
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

            // type : ENUM PostgreSQL pokemon_type
            statement.setObject(
                    4,
                    student.getType().getLabel(),
                    java.sql.Types.OTHER
            );

            // health_point
            if (student.getHealthPoint() != null) {
                statement.setInt(5, student.getHealthPoint());
            } else {
                statement.setNull(5, java.sql.Types.INTEGER);
            }

            // height : ENUM PostgreSQL
            if (student.getHeight() != null) {
                statement.setObject(
                        6,
                        student.getHeight().name(),
                        java.sql.Types.OTHER
                );
            } else {
                statement.setNull(6, java.sql.Types.OTHER);
            }

            // is_flying
            statement.setBoolean(7, student.isFlying());

            // evolution
            statement.setString(8, student.getEvolution());

            // natural_environment
            statement.setString(9, student.getNaturalEnvironment());

            // gift
            statement.setString(10, student.getGift());

            // gift_cost
            if (student.getGiftCost() != null) {
                statement.setInt(11, student.getGiftCost());
            } else {
                statement.setNull(11, java.sql.Types.INTEGER);
            }

            // gift_damage
            if (student.getGiftDamage() != null) {
                statement.setInt(12, student.getGiftDamage());
            } else {
                statement.setNull(12, java.sql.Types.INTEGER);
            }

            // gift_text
            statement.setString(13, student.getGiftText());

            // strength
            statement.setString(14, student.getStrength());

            // strength_cost
            if (student.getStrengthCost() != null) {
                statement.setInt(15, student.getStrengthCost());
            } else {
                statement.setNull(15, java.sql.Types.INTEGER);
            }

            // strength_damage
            if (student.getStrengthDamage() != null) {
                statement.setInt(16, student.getStrengthDamage());
            } else {
                statement.setNull(16, java.sql.Types.INTEGER);
            }

            // strength_text
            statement.setString(17, student.getStrengthText());

            // weakness
            statement.setString(18, student.getWeakness());

            // quote
            statement.setString(19, student.getQuote());

            // catch_date
            if (student.getCatchDate() != null) {
                statement.setDate(
                        20,
                        java.sql.Date.valueOf(student.getCatchDate())
                );
            } else {
                statement.setNull(20, java.sql.Types.DATE);
            }

            // avatar_url
            statement.setString(21, student.getAvatarUrl());

            statement.executeUpdate();
        }
    }

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
