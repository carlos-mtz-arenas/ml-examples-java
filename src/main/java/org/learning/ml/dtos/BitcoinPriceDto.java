package org.learning.ml.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@JsonPropertyOrder({"Date", "Open", "High"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinPriceDto {
    private static final Logger LOG = Logger.getLogger(BitcoinPriceDto.class.getName());

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @JsonProperty("Date")
    private String date;
    @JsonIgnore
    private long time;
    @JsonProperty("High")
    private double price;

    private long convertToDate(final String dateStr) {

        final var number = new StringBuilder();

        for (final String part : dateStr.split("-")) {
            number.append(part);
        }

        // just keep the number short to avoid having NaN
        return Long.parseLong(number.toString());
        /*
        final var df = new SimpleDateFormat(DATE_PATTERN);

        try {
            return df.parse(dateStr).getTime();
        } catch (ParseException e) {
            LOG.warning(String.format("Error while trying to parse the date [%s]", dateStr));
        }

        return 0;*/
    }


    public BitcoinPriceDto(String date) {
        this.setDate(date);
    }

    public BitcoinPriceDto() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        this.setTime(convertToDate(this.date));
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
