package com.pahimar.ee3.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionHelper
{
    public static byte[] compress(String uncompressedString) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(uncompressedString.getBytes(StandardCharsets.UTF_8));
        gzipOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static String decompress(byte[] compressedString) throws IOException {

        GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedString));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
