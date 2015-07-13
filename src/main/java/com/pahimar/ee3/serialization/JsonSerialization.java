package com.pahimar.ee3.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.JsonFluidStack;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;

import java.util.ArrayList;
import java.util.List;

public class JsonSerialization
{
    public static final Gson jsonSerializer;

    static
    {
        List<SerializationPair> serializationMap = new ArrayList<SerializationPair>();
        serializationMap.add(new SerializationPair(WrappedStack.class, new WrappedStackSerializer()));
        serializationMap.add(new SerializationPair(EnergyValue.class, new EnergyValue()));
        // serializationMap.add(new SerializationPair(EnergyValueRegistry.class, new EnergyValueRegistry()));
        serializationMap.add(new SerializationPair(EnergyValueStackMapping.class, new EnergyValueStackMapping()));
        serializationMap.add(new SerializationPair(JsonFluidStack.class, new JsonFluidStack()));
        serializationMap.add(new SerializationPair(JsonItemStack.class, new JsonItemStack()));
        // serializationMap.add(new SerializationPair(AbilityRegistry.class, new AbilityRegistry()));
        serializationMap.add(new SerializationPair(TransmutationKnowledge.class, new TransmutationKnowledge()));

        jsonSerializer = initializeSerializer(serializationMap);
    }

    private static Gson initializeSerializer(List<SerializationPair> serializationMap)
    {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        for (SerializationPair pair : serializationMap)
            builder.registerTypeAdapter(pair.getObjectType(), pair.getSerializer());

        return builder.create();
    }

    public static Gson getJsonSerializer()
    {
        return jsonSerializer;
    }

    private static class SerializationPair
    {
        private final Class objectType;
        private final Object serializer;

        private SerializationPair(Class objectType, Object serializer)
        {
            this.objectType = objectType;
            this.serializer = serializer;
        }

        public Class getObjectType()
        {
            return objectType;
        }

        public Object getSerializer()
        {
            return serializer;
        }
    }
}
