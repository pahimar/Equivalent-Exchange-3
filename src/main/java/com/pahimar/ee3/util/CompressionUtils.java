package com.pahimar.ee3.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionUtils {

    /**
     * TODO Finish JavaDoc
     *
     * @param string
     * @return
     */
    public static byte[] compress(String string) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(string.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param byteArray
     * @return
     */
    public static String decompress(byte[] byteArray) {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(byteArray)), StandardCharsets.UTF_8))) {
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
