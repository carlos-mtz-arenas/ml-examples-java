package org.learning.ml.algorithms.impl;

import org.learning.ml.algorithms.RegressionModel;
import org.learning.ml.dtos.BitcoinPriceDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.logging.Logger;

public class SingleLinearRegressionModel implements RegressionModel<BitcoinPriceDto, Double> {
    private static final Logger LOG = Logger.getLogger(SingleLinearRegressionModel.class.getName());

    private BigDecimal correlationFactor;
    private BigDecimal bias;

    public SingleLinearRegressionModel(final BigDecimal correlationFactor, final BigDecimal bias) {
        this.correlationFactor = correlationFactor;
        this.bias = bias;
    }

    public SingleLinearRegressionModel() {
        this.correlationFactor = new BigDecimal(0);
        this.bias = new BigDecimal(0);
    }

    @Override
    public void learn(final Collection<BitcoinPriceDto> observations, final Collection<Double> answers) {
        LOG.info("Starting training for single variable linear regression model");

        double sumX = 0;
        double sumY = 0;
        var sumSquaresX = new BigDecimal(0);
        var sumProd = new BigDecimal(0);

        for (final BitcoinPriceDto observation : observations) {
            sumX += observation.getTime();
            sumY += observation.getPrice();
        }

        var meanX = new BigDecimal(sumX / observations.size());
        var meanY = new BigDecimal(sumY / answers.size());

        var iteratorY = answers.iterator();
        var iteratorX = observations.iterator();

        while (iteratorX.hasNext() && iteratorY.hasNext()) {
            final var answer = iteratorY.next();
            final var observation = iteratorX.next();

            var diff = BigDecimal.valueOf(observation.getTime() - meanX.doubleValue());
            sumSquaresX = sumSquaresX.add(diff.pow(2));
            sumProd = sumProd.add(diff.multiply(BigDecimal.valueOf(answer - meanY.doubleValue())));
        }

        this.correlationFactor = sumProd.divide(sumSquaresX, RoundingMode.CEILING);
        this.bias = meanY.subtract(this.correlationFactor.multiply(meanX));

        LOG.info(String.format("Model with correlation factor of [%f] and bias of [%f]", this.correlationFactor, bias));
    }

    @Override
    public Double predict(final BitcoinPriceDto observation) {
        return this.correlationFactor.multiply(BigDecimal.valueOf(observation.getTime()))
                .add(this.bias)
                .doubleValue();
    }
}
