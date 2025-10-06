package com.aws.secretsmanager.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.util.EC2MetadataUtils;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class AWSSecretsConfigManager {
    private static final Logger logger = LogManager.getLogger(AWSSecretsConfigManager.class);
    private static AWSSecretsConfigManager instance;
    private final AWSSecretsManager secretsManager;
    private final String currentRegion;

    private AWSSecretsConfigManager() {
        currentRegion = EC2MetadataUtils.getEC2InstanceRegion();
        
        secretsManager = AWSSecretsManagerClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(currentRegion)
                .build();
        logger.info("Initialized AWSSecretsManager in region: {}", currentRegion);
    }

    public static AWSSecretsConfigManager getInstance() {
        if (instance == null) {
            instance = new AWSSecretsConfigManager();
        }
        return instance;
    }

    public List<SecretListEntry> listSecrets() {
        List<SecretListEntry> secrets = new ArrayList<>();
        ListSecretsRequest request = new ListSecretsRequest();
        long startTime = System.currentTimeMillis();

        try {
            String nextToken = null;
            do {
                request.setNextToken(nextToken);
                ListSecretsResult result = secretsManager.listSecrets(request);
                if (result.getSecretList() != null) {
                    secrets.addAll(result.getSecretList());
                    logger.info("Fetched batch of {} secrets", result.getSecretList().size());
                }
                nextToken = result.getNextToken();
            } while (nextToken != null);

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Total secrets found in {}: {}. Time taken: {} ms", 
                currentRegion, secrets.size(), duration);
        } catch (Exception e) {
            logger.error("Error listing secrets in {}: {}", currentRegion, e.getMessage(), e);
        }
        
        return secrets;
    }

    public String getCurrentRegion() {
        return currentRegion;
    }
}
