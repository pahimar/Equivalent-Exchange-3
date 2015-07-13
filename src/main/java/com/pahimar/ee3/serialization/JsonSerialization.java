package com.pahimar.ee3.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.JsonFluidStack;
import com.pahimar.ee3.exchange.JsonItemStack;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.knowledge.AbilityRegistry;
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
        serializationMap.add(new SerializationPair(EnergyValue.class, new EnergyValueSerializer()));
        // serializationMap.add(new SerializationPair(EnergyValueRegistry.class, new EnergyValueRegistry()));
        serializationMap.add(new SerializationPair(EnergyValueStackMapping.class, new EnergyValueStackMappingSerializer()));
        serializationMap.add(new SerializationPair(JsonFluidStack.class, new JsonFluidStackSerializer()));
        serializationMap.add(new SerializationPair(JsonItemStack.class, new JsonItemStackSerializer()));
        serializationMap.add(new SerializationPair(AbilityRegistry.class, new AbilityRegistrySerializer()));
        serializationMap.add(new SerializationPair(TransmutationKnowledge.class, new TransmutationKnowledgeSerializer()));

        jsonSerializer = initializeSerializer(serializationMap);
    }

    private static Gson initializeSerializer(List<SerializationPair> serializationMap)
    {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        for (SerializationPair pair : serializationMap)
            builder.registerTypeAdapter(pair.getObjectType(), pair.getSerializer());

        return builder.create();
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
