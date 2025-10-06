package com.aws.secretsmanager;

import com.aws.secretsmanager.config.AWSSecretsConfigManager;
import com.amazonaws.services.secretsmanager.model.SecretListEntry;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AWSSecretsConfigManager manager = AWSSecretsConfigManager.getInstance();
        String currentRegion = manager.getCurrentRegion();
        
        System.out.println("\nListing secrets in current region: " + currentRegion);
        System.out.println("=" + "=".repeat(currentRegion.length() + 28));
        
        List<SecretListEntry> secrets = manager.listSecrets();
        
        if (secrets.isEmpty()) {
            System.out.println("No secrets found in region: " + currentRegion);
            return;
        }

        secrets.forEach(secret -> {
            System.out.println("\nSecret Name: " + secret.getName());
            if (secret.getDescription() != null) {
                System.out.println("Description: " + secret.getDescription());
            }
            System.out.println("Tags: " + secret.getTags());
        });
        
        System.out.println("\nTotal secrets found in " + currentRegion + ": " + secrets.size());
    }
}
