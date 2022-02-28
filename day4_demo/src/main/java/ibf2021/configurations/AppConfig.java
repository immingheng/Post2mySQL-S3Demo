package ibf2021.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${spaces.endpoint}")
    private String endpoint;

    @Value("${spaces.region}")
    private String region;

    @Bean
    AmazonS3 createS3Client() {
        final String accessKey = System.getenv("S3_ACCESS_KEY");
        final String secretKey = System.getenv("S3_SECRET_KEY");

        // Credential
        final AWSStaticCredentialsProvider cred = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey));

        // Endpoint Configuration
        EndpointConfiguration config = new EndpointConfiguration(endpoint, region);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(cred)
                .withEndpointConfiguration(config)
                .build();
    }

}
