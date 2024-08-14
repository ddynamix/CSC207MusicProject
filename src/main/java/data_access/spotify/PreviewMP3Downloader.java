package data_access.spotify;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PreviewMP3Downloader {
    public static void downloadMP3(String url, String outputFilePath) throws IOException {
        if (url == null || url.isEmpty()) {
            throw new MalformedURLException("URL is null or empty");
        }

        File outputFile = new File(outputFilePath);
        File parentDir = outputFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (InputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }
}
