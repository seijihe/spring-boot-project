package com.seijihe.rest_with_spring_boot.serializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;


public class UperCaseSerializer extends StdSerializer<String> {

    protected UperCaseSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        String uperCase = value.toUpperCase();
        gen.writeString(uperCase);
    }

}
