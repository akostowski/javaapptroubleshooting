package pl.akostowski.helloapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("unzip")
@SessionScope
public class ZipController {

    Logger logger = LoggerFactory.getLogger(ZipController.class);

    private String detectedText;

    @PostMapping
    public String unzip(final @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Entering unzip method for file: {}", file.getName());
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        zis.getNextEntry();
        StringBuilder text = new StringBuilder();
        try (Scanner scanner = new Scanner(zis, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNext()) {
                text.append(scanner.next());
            }
        }
        zis.close();
        detectedText = text.toString();
        return String.format("File content size is %d", detectedText.length());
    }
}
