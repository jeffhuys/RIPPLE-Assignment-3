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
public class StrategyAgeMatch implements IStrategy {
    @Override
    public Single match(Single subject, ArrayList<Single> database, Object argument) {
        Random r = new Random();
        ArrayList<Single> rightAge = new ArrayList<Single>();
        int maxYounger = ((int[]) argument)[0];
        int maxOlder = ((int[]) argument)[1];
        for (Single single : database) {
            if (single.getAge() >= subject.getAge() - maxYounger
                    && single.getAge() <= subject.getAge() + maxOlder) {
                rightAge.add(single);
            }
        }

        if (rightAge.isEmpty()) {
            return null;
        } else {
            return rightAge.get(r.nextInt(rightAge.size()));
        }
    }
}
