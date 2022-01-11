package pb.edu.pl.krysiukm.learnit.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3ClientConfig {

    public AwsS3ClientConfig(@Value("${cloud.aws.credentials.access-key}") String awsId,
                             @Value("${cloud.aws.credentials.secret-key}") String awsKey,
                             @Value("${cloud.aws.region.static}") String region) {
        this.awsId = awsId;
        this.awsKey = awsKey;
        this.region = region;
    }

    private final String awsId;

    private final String awsKey;

    private final String region;

    @Bean
    public AmazonS3 amazonS3Client() {

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return amazonS3Client;
    }
}