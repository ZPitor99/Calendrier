# Calendrier

Une application de gestion de calendrier développée en Java avec JavaFX. Cette application permet aux utilisateurs de gérer leurs événements, rendez-vous et tâches au quotidien avec une interface graphique intuitive.

## Prérequis

- Java 11 ou supérieur
- JavaFX 17 ou supérieur

## Installation

### Option 1 : Télécharger et installer JavaFX (recommandé)

1. Téléchargez JavaFX SDK depuis [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
2. Extrayez l'archive téléchargée dans un dossier de votre choix
3. Clonez ce dépôt Git ou téléchargez-le en ZIP
4. Compilez le projet avec Maven :
   
   ```
   mvn clean package
   ```
5. Créez un script batch (Windows) pour lancer l'application :
   
   ```batch
   @echo off
   set JAVAFX_PATH=C:\chemin\vers\votre\javafx-sdk\lib
   java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,org.controlsfx.controls -jar target/Calendrier-1.0.jar
   pause
   ```
   
   Remplacez `C:\chemin\vers\votre\javafx-sdk\lib` par le chemin où vous avez extrait JavaFX

### Option 2 : Utiliser Maven directement

Si vous avez déjà Maven installé, vous pouvez lancer l'application directement :

```batch
java --module-path "C:\chemin\vers\votre\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml -jar target/Calendrier-1.0.jar
```

## Guide d'utilisation

### Démarrage

1. Lancez l'application en suivant les instructions d'installation
2. L'application s'ouvre sur le calendrier du mois en cours
3. Naviguez entre les différentes vues (jour, semaine, mois) à l'aide des onglets en haut

## Structure du projet

```
Calendrier/
├── src/
│   ├── main/
│   │   ├── java/        
│   │   │   ├── modele/      # Classes de modèle
│   │   │   ├── vue/         # Classes d'interface utilisateur
│   │   │   └── module-info.java
│   │   └── resources/       # Ressources (CSS, images)
├── .idea                    # Configuration IntelliJ
├── css                      # Styles supplémentaires
├── target/                  # Fichiers compilés
├── pom.xml                  # Configuration Maven
├── README.md                # Ce fichier
└── lancement.cmd            # Script de lancement Windows
```

## Dépannage

### Problèmes courants

1. **L'application ne démarre pas** - Vérifiez que Java et JavaFX sont correctement installés
2. **Erreur "Module not found"** - Vérifiez que le chemin vers JavaFX SDK est correct dans votre script de lancement
3. **Erreurs d'affichage** - Mettez à jour vos pilotes graphiques

### Support

Pour toute question ou problème, veuillez [ouvrir un ticket](https://github.com/ZPitor99/Calendrier/issues) sur GitHub.

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.