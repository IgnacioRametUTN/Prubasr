package com.example.buensaborback.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private final String cloudName;

    private final String apiKey;

    private final String apiSecret;

    public CloudinaryConfig() {
        Dotenv dotenv = Dotenv.load();
        this.cloudName = dotenv.get("CLOUDINARY_CLOUD_NAME");
        this.apiKey = dotenv.get("CLOUDINARY_API_KEY");
        this.apiSecret = dotenv.get("CLOUDINARY_SECRET");
    }

    @Bean
    public Cloudinary cloudinary() {
        // Configuración y creación del objeto Cloudinary con los valores inyectados
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
}
