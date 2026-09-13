# Quick start

Guide concret pour faire tourner le Trombifunscope sur votre poste.
Le contexte du dépôt (fork, rôle de chaque fichier) reste dans [`README.md`](README.md).

## 1. Monter la base

Depuis la racine du projet, jouez les scripts dans l'ordre
(avec `ON_ERROR_STOP`, sinon `psql` continue après une erreur) :

```sh
psql -U postgres -v ON_ERROR_STOP=1 -f sql/01-init.sql
psql -U postgres -d trombifunscope -v ON_ERROR_STOP=1 -f sql/02-create-table.sql
psql -U postgres -d trombifunscope -v ON_ERROR_STOP=1 -f sql/03-fill-table.sql
```

Détail et exigences (rejouables, cumulatifs) : voir [`sql/README.md`](sql/README.md).

## 2. Configurer la connexion

```sh
cp config.properties.example config.properties
```

Renseignez le mot de passe de votre rôle applicatif.
**`config.properties` n'est pas versionné** : il porte un mot de passe.

## 3. Compiler et lancer

Depuis la racine du projet, sous Windows :

```sh
javac -cp "lib/postgresql-42.7.9.jar" -d out src/Main.java src/data/*.java src/model/*.java

java -cp "out;lib/postgresql-42.7.9.jar" Main
```

Sous Linux / macOS, remplacez le `;` du classpath d'exécution par `:` :

```sh
java -cp "out:lib/postgresql-42.7.9.jar" Main
```

Sous IntelliJ : clic droit sur `lib/postgresql-42.7.9.jar` → **Add as Library…** → OK.
Sans le pilote au classpath, le projet compile très bien et échoue au lancement sur
`No suitable driver found`.

Si le programme affiche le nombre de fiches présentes dans la base, c'est bon.
