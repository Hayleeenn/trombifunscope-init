package model;


public enum PokemonHeight {
    XXL,
    XL,
    L,
    M,
    S,
    XS;

    /** Retrouve la taille a partir de la valeur lue en base (null si la colonne est vide). */
    public static PokemonHeight fromLabel(String label) {
        return label == null ? null : PokemonHeight.valueOf(label);
    }
}
