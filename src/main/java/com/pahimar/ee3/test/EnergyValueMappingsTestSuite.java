package com.pahimar.ee3.test;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EnergyValueMappingsTestSuite
{
    Map<WrappedStack, EnergyValue> testSuiteValueMap;

    public EnergyValueMappingsTestSuite()
    {
        testSuiteValueMap = new TreeMap<WrappedStack, EnergyValue>();
    }

    public EnergyValueMappingsTestSuite(File jsonFile)
    {
        if (jsonFile != null && jsonFile.exists() && jsonFile.isFile())
        {
            testSuiteValueMap = SerializationHelper.readEnergyValueStackMapFromJsonFile(jsonFile);
        }
    }

    public void add(Object object, Object value)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            if (value instanceof Number)
            {
                Number number = (Number) value;
                WrappedStack wrappedStack = WrappedStack.wrap(object);
                wrappedStack.setStackSize(1);
                testSuiteValueMap.put(wrappedStack, new EnergyValue(number.floatValue()));
            }
            else if (value == null)
            {
                WrappedStack wrappedStack = WrappedStack.wrap(object);
                wrappedStack.setStackSize(1);
                testSuiteValueMap.put(wrappedStack, null);
            }
        }
    }

    public void remove(Object object)
    {
        if (WrappedStack.canBeWrapped(object))
        {
            WrappedStack wrappedStack = WrappedStack.wrap(object);
            wrappedStack.setStackSize(1);
            testSuiteValueMap.remove(wrappedStack);
        }
    }

    public void loadTestSuite(File jsonFile)
    {
        if (jsonFile != null && jsonFile.exists() && jsonFile.isFile())
        {
            testSuiteValueMap = SerializationHelper.readEnergyValueStackMapFromJsonFile(jsonFile);
        }
    }

    public void saveTestSuite(File jsonFile)
    {
        if (jsonFile != null)
        {
            SerializationHelper.writeEnergyValueStackMapToJsonFile(jsonFile, testSuiteValueMap);
        }
    }

    public void runTestSuite()
    {
        runTestSuite(false);
    }

    public void runTestSuite(boolean strict)
    {
        List<String> successMessages = new ArrayList<String>();
        List<String> failureMessages = new ArrayList<String>();
        for (WrappedStack wrappedStack : testSuiteValueMap.keySet())
        {
            EnergyValue registryEnergyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedStack, strict);
            EnergyValue testSuiteEnergryValue = testSuiteValueMap.get(wrappedStack);

            if (registryEnergyValue == null && testSuiteEnergryValue == null)
            {
                /**
                 *  Success - anticipated that no value was found and no value was found
                 */
                successMessages.add(String.format("SUCCESS: Object '%s' had the expected energy value [Expected (%s), Found (%s)]", wrappedStack, testSuiteEnergryValue, registryEnergyValue));
            }
            else if (registryEnergyValue == null)
            {
                /**
                 *  Failure - anticipated that a value would be found but no value was found
                 */
                failureMessages.add(String.format("FAILURE: Object '%s' did not have the expected energy value [Expected (%s), Found (%s)]", wrappedStack, testSuiteEnergryValue, registryEnergyValue));
            }
            else if (registryEnergyValue != null && testSuiteEnergryValue != null)
            {
                if (registryEnergyValue.equals(testSuiteEnergryValue))
                {
                    /**
                     *  Success - anticipated that a specific value would be found and the anticipated value was found
                     */
                    successMessages.add(String.format("SUCCESS: Object '%s' had the expected energy value [Expected (%s), Found (%s)]", wrappedStack, testSuiteEnergryValue, registryEnergyValue));
                }
                else
                {
                    /**
                     *  Failure - anticipated that a specific value would be found and while a value was found it was not the anticipated one
                     */
                    failureMessages.add(String.format("FAILURE: Object '%s' did not have the expected energy value [Expected (%s), Found (%s)]", wrappedStack, testSuiteEnergryValue, registryEnergyValue));
                }
            }
        }

        for (String successMessage : successMessages)
        {
            LogHelper.info(successMessage);
        }

        for (String failureMessage : failureMessages)
        {
            LogHelper.warn(failureMessage);
        }
    }
}
