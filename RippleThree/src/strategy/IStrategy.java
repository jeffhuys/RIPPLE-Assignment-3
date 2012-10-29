/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;

/**
 * Interface voor strategy
 * @author Jeff
 */
public interface IStrategy {
    public Single match(Single subject, ArrayList<Single> database, Object argument);
}
