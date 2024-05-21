package redis.geode;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

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
    UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String[] getConditions() {
        return conditions;
    }

    public void setConditions(String[] conditions) {
        this.conditions = conditions;
    }
}
