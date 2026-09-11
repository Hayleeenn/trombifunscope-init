package model;

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
