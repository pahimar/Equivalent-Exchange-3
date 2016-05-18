package com.pahimar.ee3.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionHelper {

    /**
     *
     * @param uncompressedString
     * @return
     */
    public static byte[] compress(String uncompressedString) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(uncompressedString.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     *
     * @param compressedString
     * @return
     */
    public static String decompress(byte[] compressedString) {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(compressedString)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
