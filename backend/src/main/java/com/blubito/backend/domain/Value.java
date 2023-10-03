package com.blubito.backend.domain;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Value {
    private String id;
    private String timestamp;
    private String value;
    private String deviceId;
}
