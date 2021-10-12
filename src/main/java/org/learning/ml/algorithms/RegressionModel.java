package org.learning.ml.algorithms;

import java.util.Collection;

public interface RegressionModel<INDEPENDENT, DEPENDANT> {

    /**
     * Trains the model using the data passed (x/y).
     *
     * @param observations The values that determine y (independent variables).
     * @param answers      All the "answers" for the given x.
     */
    void learn(Collection<INDEPENDENT> observations, Collection<DEPENDANT> answers);

    /**
     * Predicts the result for the given observation.
     *
     * @param observation What was observed in the wild.
     * @return The calculated value for that.
     */
    DEPENDANT predict(INDEPENDENT observation);
}
