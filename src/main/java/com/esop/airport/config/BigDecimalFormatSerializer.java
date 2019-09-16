package com.esop.airport.config;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalFormatSerializer implements ObjectSerializer {

	@Override
	public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = jsonSerializer.getWriter();
        if(object == null){
            out.writeString("0.00");
        }else{
            BigDecimal bigDecimal = (BigDecimal)object;
            String value = bigDecimal.toString();
            out.writeString(value);
        }
    }
}