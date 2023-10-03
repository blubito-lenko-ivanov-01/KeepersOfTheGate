package com.blubito.backend.domain;

import com.google.firebase.database.annotations.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Device {

    @NotNull
    private String id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private boolean isActive;
    private String createdAt;
    private String createdBy;
    private List<Value> values;

    public Device() {

    }
}
