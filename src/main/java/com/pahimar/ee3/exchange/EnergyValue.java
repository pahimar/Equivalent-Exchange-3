package com.pahimar.ee3.exchange;

import com.google.gson.*;
import com.pahimar.ee3.util.LogHelper;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnergyValue implements Comparable<EnergyValue>, JsonDeserializer<EnergyValue>, JsonSerializer<EnergyValue>
{
    // Gson serializer for serializing to/deserializing from json
    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(EnergyValue.class, new EnergyValue()).create();
    private static final int PRECISION = 4;

    public final float[] components;

    public EnergyValue()
    {
        this(new float[EnergyType.TYPES.length]);
    }

    public EnergyValue(int value)
    {
        this((float) value);
    }

    public EnergyValue(float value)
    {
        this(value, EnergyType.DEFAULT);
    }

    public EnergyValue(float value, EnergyComponent component)
    {
        this(value, component.type);
    }

    public EnergyValue(int value, EnergyType energyType)
    {
        this((float) value, energyType);
    }

    public EnergyValue(float value, EnergyType energyType)
    {
        this(value, Arrays.asList(new EnergyComponent(energyType)));
    }

    public EnergyValue(float[] components)
    {
        if (components.length == EnergyType.TYPES.length)
        {
            this.components = new float[components.length];
            for (int i = 0; i < this.components.length; i++)
            {
                BigDecimal bigComponent = BigDecimal.valueOf(components[i]).setScale(PRECISION, BigDecimal.ROUND_HALF_DOWN);
                this.components[i] = bigComponent.floatValue();
            }
        }
        else
        {
            this.components = null;
        }
    }

    public EnergyValue(int value, List<EnergyComponent> componentList)
    {
        this((float) value, componentList);
    }

    public EnergyValue(float value, List<EnergyComponent> componentList)
    {
        this.components = new float[EnergyType.TYPES.length];

        List<EnergyComponent> collatedComponents = collateComponents(componentList);

        int totalComponents = 0;

        for (EnergyComponent component : collatedComponents)
        {
            if (component.weight > 0)
            {
                totalComponents += component.weight;
            }
        }

        if (totalComponents > 0)
        {
            for (EnergyComponent component : collatedComponents)
            {
                if (component.weight > 0)
                {
                    this.components[component.type.ordinal()] = value * (component.weight * 1F / totalComponents);
                }
            }
        }
        else
        {
            this.components[EnergyType.DEFAULT.ordinal()] = value;
        }

        for (int i = 0; i < this.components.length; i++)
        {
            BigDecimal bigComponent = BigDecimal.valueOf(this.components[i]).setScale(PRECISION, BigDecimal.ROUND_HALF_DOWN);
            this.components[i] = bigComponent.floatValue();
        }
    }

    /**
     * Deserializes an EnergyValue object from the given serialized json String
     *
     * @param jsonEnergyValue
     *         Json encoded String representing a EnergyValue object
     *
     * @return The EnergyValue that was encoded as json, or null if a valid EnergyValue could not be decoded from given
     * String
     */
    @SuppressWarnings("unused")
    public static EnergyValue createFromJson(String jsonEnergyValue)
    {
        try
        {
            return gsonSerializer.fromJson(jsonEnergyValue, EnergyValue.class);
        }
        catch (JsonSyntaxException exception)
        {
            LogHelper.error(exception.getMessage());
        }
        catch (JsonParseException exception)
        {
            LogHelper.error(exception.getMessage());
        }

        return null;
    }

    private static List<EnergyComponent> collateComponents(List<EnergyComponent> uncollatedComponents)
    {
        Integer[] componentCount = new Integer[EnergyType.TYPES.length];

        for (EnergyComponent energyComponent : uncollatedComponents)
        {
            if (componentCount[energyComponent.type.ordinal()] == null)
            {
                componentCount[energyComponent.type.ordinal()] = 0;
            }

            if (energyComponent.weight >= 0)
            {
                componentCount[energyComponent.type.ordinal()] = componentCount[energyComponent.type.ordinal()] + energyComponent.weight;
            }
        }

        List<EnergyComponent> collatedComponents = new ArrayList<EnergyComponent>();

        for (int i = 0; i < EnergyType.TYPES.length; i++)
        {
            if (componentCount[i] != null)
            {
                collatedComponents.add(new EnergyComponent(EnergyType.TYPES[i], componentCount[i]));
            }
        }

        Collections.sort(collatedComponents);

        return collatedComponents;
    }

    private static int compareComponents(float[] first, float[] second)
    {
        if (first.length == EnergyType.TYPES.length && second.length == EnergyType.TYPES.length)
        {

            for (EnergyType energyType : EnergyType.TYPES)
            {
                if (Float.compare(first[energyType.ordinal()], second[energyType.ordinal()]) != 0)
                {
                    return Float.compare(first[energyType.ordinal()], second[energyType.ordinal()]);
                }
            }

            return 0;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public float getValue()
    {
        float sumSubValues = 0;

        for (float subValue : this.components)
        {
            if (subValue > 0)
            {
                sumSubValues += subValue;
            }
        }

        return sumSubValues;
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof EnergyValue && (compareTo((EnergyValue) object) == 0);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (EnergyType energyType : EnergyType.TYPES)
        {
            if (components[energyType.ordinal()] > 0)
            {
                stringBuilder.append(String.format(" %s:%s ", energyType, components[energyType.ordinal()]));
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;

        hashCode = 37 * hashCode + Float.floatToIntBits(getValue());
        for (float subValue : components)
        {
            hashCode = 37 * hashCode + Float.floatToIntBits(subValue);
        }

        return hashCode;
    }

    @Override
    public int compareTo(EnergyValue exchangeEnergyValue)
    {
        if (exchangeEnergyValue != null)
        {
            return compareComponents(this.components, exchangeEnergyValue.components);
        }
        else
        {
            return -1;
        }
    }

    /**
     * Returns this EnergyValue as a json serialized String
     *
     * @return Json serialized String of this EnergyValue
     */
    public String toJson()
    {
        return gsonSerializer.toJson(this);
    }

    @Override
    public JsonElement serialize(EnergyValue exchangeEnergyValue, Type type, JsonSerializationContext context)
    {
        JsonObject jsonEnergyValue = new JsonObject();

        for (EnergyType energyType : EnergyType.TYPES)
        {
            jsonEnergyValue.addProperty(energyType.toString(), exchangeEnergyValue.components[energyType.ordinal()]);
        }

        return jsonEnergyValue;
    }

    @Override
    public EnergyValue deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {

        float[] energyValueComponents = new float[EnergyType.TYPES.length];
        JsonObject jsonEnergyValue = (JsonObject) jsonElement;

        for (EnergyType energyType : EnergyType.TYPES)
        {
            if ((jsonEnergyValue.get(energyType.toString()) != null) && (jsonEnergyValue.get(energyType.toString()).isJsonPrimitive()))
            {
                try
                {
                    energyValueComponents[energyType.ordinal()] = jsonEnergyValue.get(energyType.toString()).getAsFloat();
                }
                catch (UnsupportedOperationException exception)
                {
                    LogHelper.error(exception.getMessage());
                }
            }
        }

        EnergyValue exchangeEnergyValue = new EnergyValue(energyValueComponents);

        if (exchangeEnergyValue.getValue() > 0f)
        {
            return exchangeEnergyValue;
        }

        return null;
    }
}
