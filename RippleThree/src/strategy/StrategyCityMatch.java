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
public class StrategyCityMatch implements IStrategy {

    @Override
    public Single match(Single subject, ArrayList<Single> database, Object argument) {
        Random r = new Random();

        ArrayList<Single> rightCity = new ArrayList<Single>();
        for (Single single : database) {
            if (single.getCity().equals(subject.getCity())) {
                rightCity.add(single);
            }
        }
        if (rightCity.isEmpty()) {
            return null;
        } else {
            return rightCity.get(r.nextInt(rightCity.size()));
        }
    }
}
