/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.Random;
import strategy.matchers.Criterion;

/**
 *
 * @author Jeff
 */
public class StrategySelectionMatch implements IStrategy {

    @Override
    public Single match(Single subject, ArrayList<Single> database, Object argument) {
        Random r = new Random();
        ArrayList<Single> rightCriteria = new ArrayList<Single>();
        Criterion[] criteria = (Criterion[]) argument;
        for (Single single : database) {
            boolean include = true;
            for (Criterion criterion : criteria) {
                if (!criterion.applies(single)) {
                    include = false;
                    break;
                }
            }
            if (include) {
                rightCriteria.add(single);
            }
        }
        if (rightCriteria.isEmpty()) {
            return null;
        } else {
            return rightCriteria.get(r.nextInt(rightCriteria.size()));
        }
    }
}
