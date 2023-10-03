package com.blubito.backend.service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValueDTO {
    private String id;
    private String timestamp;
    private String value;
}
