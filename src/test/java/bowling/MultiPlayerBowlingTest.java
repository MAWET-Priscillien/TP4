/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedago
 */
public class MultiPlayerBowlingTest {
    
    private MultiPlayerBowling game;
    public String[] players= { "John", "Paul", "Georges", "Ringo" };
    
    @Before
	public void setUp() {
            game = new MultiPlayerBowling();
	}
        
        
    // Test init
    @Test
    public void init() throws Exception{
        assertEquals("Prochain tir : joueur John, tour n° 1, boule n° 1", game.startNewGame(players));
    }
    
    // Test passage au joueur suivant après un Strike
    @Test
    public void afterStrike() throws Exception{
        System.out.println(game.startNewGame(players));
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", game.lancer(10));
    }
    
    // Test score
    @Test
    public void scoreTest() throws Exception{
        System.out.println(game.startNewGame(players));
        System.out.println(game.lancer(10)); // Strike for John
        System.out.println(game.lancer(3));
        System.out.println(game.lancer(7)); // Spare for Paul
        System.out.println(game.lancer(0));
        System.out.println(game.lancer(0)); // 0 for Georges
        System.out.println(game.lancer(0));
        System.out.println(game.lancer(0)); // 0 for Ringo
        System.out.println(game.lancer(6));
        System.out.println(game.lancer(3)); // 9 for john, + strike bonus
        System.out.println(game.lancer(5));
        System.out.println(game.lancer(0)); // 5 for Paul, + spare bonus
        assertEquals(28, game.scoreFor("John"));
    }
    
    
    // Test exception si liste joueurs vide
    @Test ( expected = java.lang.Exception.class )
    public void playerEmpty() throws Exception{
        String[] players2= { };
        System.out.println(game.startNewGame(players2));
    }
    
    // Test exception si liste joueurs nulle
    @Test ( expected = java.lang.Exception.class )
    public void playerNull() throws Exception{
        String[] players3= null;
        System.out.println(game.startNewGame(players3));
    }
    
    // Test exception si joueur n'existe pas et qu'on demande son score
    @Test ( expected = java.lang.Exception.class )
    public void playerUnknown() throws Exception{
        System.out.println(game.startNewGame(players));
        System.out.println(game.scoreFor("Priscillien"));
    }
    
    
    // Test partie terminée
    @Test
    public void gameOver() throws Exception{
        System.out.println(game.startNewGame(players));
        for (int i=0;i<47;i++){
            System.out.println(game.lancer(10));
        }
        assertEquals("Partie terminée", game.lancer(10));
    }
    
    
    // Test exception partie non démarrée
    @Test ( expected = java.lang.Exception.class )
    public void notStarted() throws Exception{
        System.out.println(game.lancer(10));
    }
    
    
    
    
    
}
