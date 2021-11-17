package com.aderenchuk.brest.service.rest_app.websocket;


import com.aderenchuk.brest.dao.jpa.TourDaoJPA;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView({TourDaoJPA.class})
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;
}
