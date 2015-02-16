package com.pahimar.ee3.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionHelper
{
    public static byte[] compressStringToByteArray(String uncompressedString)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream;
        try
        {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(uncompressedString.getBytes("UTF-8"));
            gzipOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    public static String decompressStringFromByteArray(byte[] compressedString)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedString));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
