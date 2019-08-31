package com.lukas.adventuregame.gamelogic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for the {@code Game} class.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public class GameTest {
    /**
     * Tests the shortest way through the game.
     */
    @Test
    public void shortWayTest() {
        Game game = new Game();
        game.handleCommand("prohledej sousoší");
        game.handleCommand("seber víno");
        game.handleCommand("jdi podloubí");
        game.handleCommand("dej víno");
        game.handleCommand("jdi nádvoří");
        game.handleCommand("jdi chodba");
        game.handleCommand("prohledej truhla");
        game.handleCommand("seber chloroform");
        game.handleCommand("seber whisky");
        game.handleCommand("jdi komnata_knížete");
        game.handleCommand("dej chloroform");
        game.handleCommand("seber svícen");
        game.handleCommand("jdi chodba");
        game.handleCommand("jdi nádvoří");
        game.handleCommand("jdi brána");
        game.handleCommand("dej whisky");
        game.handleCommand("jdi svoboda");
        assertTrue(game.isFinished());
    }
    
    /**
     * Tests a thorough way through the game.
     */
    @Test
    public void longWayTest() {
        Game game = new Game();
        GamePlan plan = game.getGamePlan();
        assertEquals("Tam se odsud jít nedá!", game.handleCommand("jdi nádvoří"));
        assertEquals("kašna", plan.getCurrentRoom().getName());
        game.handleCommand("jdi podloubí");
        assertEquals("podloubí",plan.getCurrentRoom().getName());
        assertEquals("stráž", plan.getCurrentRoom().getCharacter().getName());
        game.handleCommand("jdi kašna");
        assertEquals("Tato věc není přenositelná.", game.handleCommand("seber sousoší"));
        assertTrue(game.handleCommand("prohledej sousoší").contains("víno"));
        assertEquals("Sebral jsi víno.", game.handleCommand("seber víno"));
        game.handleCommand("jdi podloubí");
        assertEquals("Tento prostor je zamčený. Teď tam nemůžeš.", game.handleCommand("jdi nádvoří"));
        assertEquals("podloubí", plan.getCurrentRoom().getName());
        assertEquals("Díky, teď tedy můžeš projít.", game.handleCommand("dej víno"));
        game.handleCommand("jdi nádvoří");
        assertEquals("nádvoří", plan.getCurrentRoom().getName());
        game.handleCommand("jdi chodba");
        assertEquals("chodba", plan.getCurrentRoom().getName());
        assertEquals("Taková věc tu není.", game.handleCommand("seber chloroform"));
        game.handleCommand("prohledej truhla");
        assertEquals("Sebral jsi chloroform.", game.handleCommand("seber chloroform"));
        assertEquals("Sebral jsi whisky.", game.handleCommand("seber whisky"));
        game.handleCommand("jdi komnata_knížete");
        assertEquals("Teď jej nemůžeš vzít. Kníže by se probudil.", game.handleCommand("seber svícen"));
        game.handleCommand("dej chloroform");
        assertEquals("Sebral jsi svícen.", game.handleCommand("seber svícen"));
        game.handleCommand("jdi chodba");
        game.handleCommand("jdi nádvoří");
        game.handleCommand("jdi brána");
        assertEquals("Tento prostor je zamčený. Teď tam nemůžeš.", game.handleCommand("jdi svoboda"));
        assertFalse(game.isFinished());
        game.handleCommand("dej whisky");
        game.handleCommand("jdi svoboda");
        assertTrue(game.isFinished());
    }
}
