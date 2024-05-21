package redis.geode;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
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
}
