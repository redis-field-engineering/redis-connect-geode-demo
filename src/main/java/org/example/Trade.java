package org.example;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class Trade implements Serializable {
    @JsonProperty("s")
    String symbol;
    @JsonProperty("p")
    float price;
    @JsonProperty("t")
    long timestamp;
    @JsonProperty("v")
    int volume;
    @JsonProperty("c")
    String[] conditions;

    @JsonGetter("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonGetter("price")
    public float getPrice() {
        return price;
    }

    @JsonGetter("timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    @JsonGetter("volume")
    public int getVolume() {
        return volume;
    }

    @JsonGetter("conditions")
    public String[] getConditions(){
        return conditions;
    }
}
