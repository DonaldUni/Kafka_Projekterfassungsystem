package Haegertime.Donald.Kafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application-kafka-properties")
@Data @NoArgsConstructor
public class KafkaProperties {

    public static String BOOTSTRAP_ADDRESS = "localhost:29092";
}