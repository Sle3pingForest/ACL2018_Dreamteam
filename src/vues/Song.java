package vues;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Song {

    /**** Ici on gere la musique de font ****/
    private static Music background;
    private static int volumeMusic = 1;
    private static int pitchMusic = 1;

    /**** Ici on gere les option pour les bruitage du jeu ****/
    private static int volumeSong = 1;
    private static int pitchSong = 1;

    public static void chargerForet() throws SlickException {
        background = new Music("main/resources/song/musique/Woods.ogg");
    }

    public static void jouerBackground() {
        background.loop(pitchMusic,volumeMusic);
    }


}
