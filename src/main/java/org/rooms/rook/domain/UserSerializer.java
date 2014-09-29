package org.rooms.rook.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        if (value.getId().isPresent()) {
            jgen.writeNumberField("id", value.getId().get());
        }
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("email", value.getEmail());
        jgen.writeStringField("passwordHash", value.getPasswordHash());
        jgen.writeEndObject();
    }
}
