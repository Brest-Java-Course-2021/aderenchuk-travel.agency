package com.aderenchuk.brest.service.websocket;


import com.aderenchuk.brest.model.websocket.EventType;
import com.aderenchuk.brest.model.websocket.ObjectType;
import com.aderenchuk.brest.model.websocket.Views;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView({Views.Id.class})
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;
}
