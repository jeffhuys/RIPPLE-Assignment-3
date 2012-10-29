/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class Context {
    private IStrategy strategy;
    
    // Constructor
    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }
 
    public Single executeStrategy(Single subject, ArrayList<Single> database, Object argument) {
        return strategy.match(subject, database, argument);
    }
}
