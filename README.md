# Le Trombifunscope : dépôt de départ

Ce dépôt n'est pas une application, c'est un **point de départ à forker**. Il
contient de quoi faire tourner un programme Java qui parle à PostgreSQL, et rien de plus.
Pour le moment il ne marche pas : à vous de le configurer pour qu'il fonctionne avec la base
de données que vous aurez créée.

Pour monter la base, configurer la connexion et lancer le programme : voir
[`QUICKSTART.md`](QUICKSTART.md).

## Ce qui vous est fourni

| Fichier | Ce qu'il fait                                                                                      |
|---|----------------------------------------------------------------------------------------------------|
| `src/data/Database.java` | ouvre la connexion à la base. **La seule classe qui le fait**, et vous n'avez pas à la réécrire    |
| `src/data/StudentDao.java` | une opération, `count()`. Elle vous servira à valider que votre projet démarre, mais aussi de modèle pour écrire les autres requêtes |
| `src/data/README.md` | comment fonctionne un `PreparedStatement`. **À lire avant d'écrire votre première opération**       |
| `src/model/Student.java` | une fiche, avec un prénom et un nom. À compléter avec vos colonnes                                 |
| `src/Main.java` | affiche le nombre de fiches en base. À compléter                                                   |
| `lib/` | le pilote JDBC PostgreSQL. À vous de le mettre dans le classpath, à la compilation comme à l'exécution |

## Démarrer

**1. Forkez ce dépôt**, une fois pour le groupe. Chacun clone ensuite le fork du groupe.

**2. Montez la base.** Écrivez vos scripts dans `sql/`, conformement au brief.

**3. Renseignez votre connexion.** Copiez le fichier d'exemple et mettez-y le mot de passe de
votre rôle applicatif.

**`config.properties` n'est pas versionné**, il porte un mot de passe. Chacun a le sien sur son
poste. Sa copie d'exemple, elle, est dans le dépôt.

**4. Compilez et lancez.**

Le pilote JDBC est dans `lib/`, mais il n'est branché nulle part : à vous de le mettre au
classpath. Sous IntelliJ, clic droit sur `lib/postgresql-42.7.9.jar` dans l'arborescence de
gauche → **Add as Library…** → OK.

Sans ça, le projet compile très bien et échoue au lancement sur `No suitable driver found`.

Si le programme affiche le nombre de fiches présentes dans la base, c'est bon : vous pouvez
passer à l'écriture des autres requêtes.
