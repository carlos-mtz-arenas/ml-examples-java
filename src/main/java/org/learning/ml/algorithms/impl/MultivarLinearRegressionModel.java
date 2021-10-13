package org.learning.ml.algorithms.impl;

import org.learning.ml.algorithms.RegressionModel;

import java.util.Collection;

public class MultivarLinearRegressionModel implements RegressionModel<Collection<Double>, Double> {
    Collection<Double> correlations; // also known as Ws
    Double bias;

    @Override
    public void learn(Collection<Collection<Double>> observations, Collection<Double> answers) {
        //observation[0] = [7.4,0.7,0,1.9,0.076,11,34,0.9978,3.51,0.56,9.4] ; answer[0] = 5
    }

    @Override
    public Double predict(Collection<Double> observation) {
        return null;
    }
}
