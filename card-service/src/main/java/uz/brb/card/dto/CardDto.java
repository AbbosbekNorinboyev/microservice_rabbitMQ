package uz.brb.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CardDto implements Serializable {
//    private Long id;
    @JsonProperty
    private Long userId;
    @JsonProperty
    private String name;
    @JsonProperty
    private String number;
}
