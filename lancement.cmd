@echo off
echo =============================================
echo Lanceur d'application Calendrier
echo =============================================

rem Tentative directe de lancement avec JavaFX SDK spécifique
echo Tentative de lancement avec JavaFX SDK local...
start javaw --module-path "C:\Program Files\openjfx-24_windows-x64_bin-sdk\javafx-sdk-24\lib" --add-modules javafx.controls,javafx.fxml -jar target/Calendrier-1.0.jar
