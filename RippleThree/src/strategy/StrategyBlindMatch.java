/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class StrategyBlindMatch implements IStrategy {

    @Override
    public Single match(Single subject, ArrayList<Single> database, Object argument) {
        Random r = new Random();
        if (database.isEmpty()) return null;
        else return database.get(r.nextInt(database.size()));
    }
    
}
