package org.learning.ml.tests;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.learning.ml.MlApplication;
import org.learning.ml.algorithms.impl.SingleLinearRegressionModel;
import org.learning.ml.dtos.BitcoinPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.learning.ml.utils.TimeTrackingUtils.measureProcess;

public class SingleValueLinearRegression {

    public static void main(String[] args) throws IOException {
        Logger.getGlobal().setLevel(Level.ALL);

        final var csv = new CsvMapper();
        final var schema = CsvSchema
                .emptySchema()
                .withHeader();

        // read the CSV
        final MappingIterator<BitcoinPriceDto> content = csv.readerFor(BitcoinPriceDto.class)
                .with(schema)
                .readValues(MlApplication.class.getResourceAsStream("/bitcoin-prices.csv"));

        // get the historical prices
        final var bitcoinPrices = content.readAll();

        // test the models - either train one from scratch or use existing values from previous training sessions
        //trainAndTest(bitcoinPrices);
        useInitializedModel();
    }

    private static void useInitializedModel() {
        // manually pass the information for the parameters
        final var regressionModel = new SingleLinearRegressionModel(BigDecimal.valueOf(2.999071),
                BigDecimal.valueOf(-60566889.443382));

        guessUsingModel(regressionModel);
    }

    private static void trainAndTest(final Collection<BitcoinPriceDto> bitcoinPrices) {
        final var regressionModel = new SingleLinearRegressionModel();

        final var answers = bitcoinPrices.stream()
                .map(BitcoinPriceDto::getPrice)
                .collect(Collectors.toList());

        measureProcess("Training", () -> {
            regressionModel.learn(bitcoinPrices, answers);
        });

        guessUsingModel(regressionModel);
    }

    private static void guessUsingModel(final SingleLinearRegressionModel regressionModel) {
        System.out.println(":::::::::::::::::::::::::::::::");

        measureProcess("Prediction of the future", () -> {
            final var date = "2022-01-01";
            final var predictionFuture = regressionModel.predict(new BitcoinPriceDto(date));
            System.out.println("The prediction of the date [" + date + "] is: " + predictionFuture);
        });

        System.out.println(":::::::::::::::::::::::::::::::");

        measureProcess("Prediction of the past", () -> {
            final var date = "2020-11-04";
            final var predictionExisting = regressionModel.predict(new BitcoinPriceDto(date));
            System.out.println("The guess for the date [" + date + "] is: " + predictionExisting);
        });
    }

}
