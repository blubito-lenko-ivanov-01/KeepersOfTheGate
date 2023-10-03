package com.blubito.backend.service.dto;

import com.blubito.backend.domain.Value;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO implements Serializable {

    private String id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private boolean isActive;
    private String createdAt;
    private String createdBy;
    private List<Value> values = new ArrayList<>();
}
