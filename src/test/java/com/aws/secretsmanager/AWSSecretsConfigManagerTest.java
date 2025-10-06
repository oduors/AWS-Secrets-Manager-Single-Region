package com.aws.secretsmanager;

import com.aws.secretsmanager.config.AWSSecretsConfigManager;
import com.amazonaws.services.secretsmanager.model.SecretListEntry;
import org.junit.Test;
import org.junit.Assert;
import java.util.List;

public class AWSSecretsConfigManagerTest {
    
    @Test
    public void testListSecrets() {
        AWSSecretsConfigManager manager = AWSSecretsConfigManager.getInstance();
        List<SecretListEntry> secrets = manager.listSecrets();
        Assert.assertNotNull("Secrets list should not be null", secrets);
    }

    @Test
    public void testGetCurrentRegion() {
        AWSSecretsConfigManager manager = AWSSecretsConfigManager.getInstance();
        String region = manager.getCurrentRegion();
        Assert.assertNotNull("Region should not be null", region);
        Assert.assertFalse("Region should not be empty", region.isEmpty());
    }
}
