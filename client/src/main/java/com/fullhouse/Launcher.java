package com.fullhouse;

/**
 * Entry point used by packaged (jpackage) builds.
 *
 * <p>When the application's main class extends {@link javafx.application.Application}
 * and is launched from the classpath — which is how a jpackage-produced installer
 * starts the app — the JavaFX runtime aborts with "JavaFX runtime components are
 * missing". Delegating startup from a class that does <em>not</em> extend
 * {@code Application} sidesteps that check, so the packaged installers launch
 * correctly. During development {@link App} is still launched directly via
 * {@code javafx:run}, so this class is only relevant for released builds.</p>
 */
public class Launcher {

    /**
     * Delegates to {@link App#main(String[])}.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        App.main(args);
    }
}
