package model;

import java.time.LocalDate;

/**
 * Une fiche du Trombifunscope : une personne de la promo dans l'univers Pokemon.
 * Chaque attribut correspond a une colonne de la table t_student.
 *
 * Toutes les valeurs sont donnees au constructeur et ne changent plus ensuite :
 * pas de setters, seulement des getters. Pour corriger une fiche, on cree un
 * nouveau Student avec les bonnes valeurs.
 *
 * Les colonnes numeriques facultatives sont en Integer (et non int) :
 * elles peuvent valoir null, comme en base.
 */
public class Student {

    /** Types possibles : reprend exactement l'enum PostgreSQL pokemon_type. */
    public enum PokemonType {
        // Types officiels
        ACIER("Acier"),
        COMBAT("Combat"),
        DRAGON("Dragon"),
        EAU("Eau"),
        ELECTRIK("Electrik"),
        FEE("Fee"),
        FEU("Feu"),
        GLACE("Glace"),
        INSECTE("Insecte"),
        NORMAL("Normal"),
        PLANTE("Plante"),
        POISON("Poison"),
        PSY("Psy"),
        ROCHE("Roche"),
        SOL("Sol"),
        SPECTRE("Spectre"),
        TENEBRES("Tenebres"),
        VOL("Vol"),

        // Types du groupe
        BASKETATOR("Basketator"),
        CALINOU("Calinou"),
        FAIGNANT("Faignant"),
        HIBOU("Hibou"),
        INTELO("Intelo"),
        MACHETERO("Machetero"),
        PATRIOTE("Patriote"),
        PHANTOM("Phantom"),
        SHERLOCK("Sherlock");

        private final String label;

        PokemonType(String label) {
            this.label = label;
        }

        /** Valeur telle qu'elle est stockee en base. */
        public String getLabel() {
            return label;
        }

        /** Retrouve le type a partir de la valeur lue en base. */
        public static PokemonType fromLabel(String label) {
            for (PokemonType type : values()) {
                if (type.label.equals(label)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Type inconnu : " + label);
        }
    }

    /** Tailles possibles : reprend exactement l'enum PostgreSQL pokemon_height. */
    public enum PokemonHeight {
        XXL, XL, L, M, S, XS;

        /** Retrouve la taille a partir de la valeur lue en base (null si la colonne est vide). */
        public static PokemonHeight fromLabel(String label) {
            return label == null ? null : PokemonHeight.valueOf(label);
        }
    }

    private final Long          id;                  // id : bigint, genere par la base (null tant que la fiche n'est pas en base)
    private final String        firstName;           // first_name : obligatoire
    private final String        pokemonName;         // pokemon_name : obligatoire
    private final Integer       level;               // level : facultatif
    private final PokemonType   type;                // type : obligatoire
    private final Integer       healthPoint;         // health_point : facultatif
    private final PokemonHeight height;              // height : facultatif
    private final boolean       flying;              // is_flying : obligatoire
    private final String        evolution;           // evolution : facultatif, unique
    private final String        naturalEnvironment;  // natural_environment : obligatoire
    private final String        gift;                // gift : facultatif
    private final Integer       giftCost;            // gift_cost : facultatif, entre 1 et 3
    private final Integer       giftDamage;          // gift_damage : facultatif
    private final String        giftText;            // gift_text : facultatif
    private final String        strength;            // strength : obligatoire
    private final Integer       strengthCost;        // strength_cost : facultatif, entre 1 et 3
    private final Integer       strengthDamage;      // strength_damage : facultatif
    private final String        strengthText;        // strength_text : facultatif
    private final String        weakness;            // weakness : obligatoire
    private final String        quote;               // quote : facultatif
    private final LocalDate     catchDate;           // catch_date : facultatif
    private final String        avatarUrl;           // avatar_url : obligatoire, unique

    /** Fiche lue en base : l'id est connu. */
    public Student(Long id,
                   String firstName,
                   String pokemonName,
                   Integer level,
                   PokemonType type,
                   Integer healthPoint,
                   PokemonHeight height,
                   boolean flying,
                   String evolution,
                   String naturalEnvironment,
                   String gift,
                   Integer giftCost,
                   Integer giftDamage,
                   String giftText,
                   String strength,
                   Integer strengthCost,
                   Integer strengthDamage,
                   String strengthText,
                   String weakness,
                   String quote,
                   LocalDate catchDate,
                   String avatarUrl) {
        this.id = id;
        this.firstName = firstName;
        this.pokemonName = pokemonName;
        this.level = level;
        this.type = type;
        this.healthPoint = healthPoint;
        this.height = height;
        this.flying = flying;
        this.evolution = evolution;
        this.naturalEnvironment = naturalEnvironment;
        this.gift = gift;
        this.giftCost = giftCost;
        this.giftDamage = giftDamage;
        this.giftText = giftText;
        this.strength = strength;
        this.strengthCost = strengthCost;
        this.strengthDamage = strengthDamage;
        this.strengthText = strengthText;
        this.weakness = weakness;
        this.quote = quote;
        this.catchDate = catchDate;
        this.avatarUrl = avatarUrl;
    }

    /** Nouvelle fiche, pas encore en base : l'id sera genere a l'insertion. */
    public Student(String firstName,
                   String pokemonName,
                   Integer level,
                   PokemonType type,
                   Integer healthPoint,
                   PokemonHeight height,
                   boolean flying,
                   String evolution,
                   String naturalEnvironment,
                   String gift,
                   Integer giftCost,
                   Integer giftDamage,
                   String giftText,
                   String strength,
                   Integer strengthCost,
                   Integer strengthDamage,
                   String strengthText,
                   String weakness,
                   String quote,
                   LocalDate catchDate,
                   String avatarUrl) {
        this(null, firstName, pokemonName, level, type, healthPoint, height, flying, evolution, naturalEnvironment, gift, giftCost, giftDamage, giftText, strength, strengthCost, strengthDamage, strengthText, weakness, quote, catchDate, avatarUrl);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public Integer getLevel() {
        return level;
    }

    public PokemonType getType() {
        return type;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public PokemonHeight getHeight() {
        return height;
    }

    public boolean isFlying() {
        return flying;
    }

    public String getEvolution() {
        return evolution;
    }

    public String getNaturalEnvironment() {
        return naturalEnvironment;
    }

    public String getGift() {
        return gift;
    }

    public Integer getGiftCost() {
        return giftCost;
    }

    public Integer getGiftDamage() {
        return giftDamage;
    }

    public String getGiftText() {
        return giftText;
    }

    public String getStrength() {
        return strength;
    }

    public Integer getStrengthCost() {
        return strengthCost;
    }

    public Integer getStrengthDamage() {
        return strengthDamage;
    }

    public String getStrengthText() {
        return strengthText;
    }

    public String getWeakness() {
        return weakness;
    }

    public String getQuote() {
        return quote;
    }

    public LocalDate getCatchDate() {
        return catchDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "#" + id + " " + pokemonName + " (" + firstName + ") - "
                + (type == null ? "?" : type.getLabel()) + ", " + healthPoint + " PV";
    }
}
