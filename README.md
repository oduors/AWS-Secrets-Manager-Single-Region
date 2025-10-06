# AWS Secrets Manager Single Region Implementation

A Java-based solution for efficiently managing AWS Secrets Manager secrets within a single region, optimized for performance and simplicity.

## Overview
This implementation provides a streamlined solution for listing AWS Secrets Manager secrets within a single region, with built-in pagination support and comprehensive error handling.

## Features
- ✅ Efficient pagination (10 secrets per batch)
- ✅ Performance optimized (~520ms for 40 secrets)
- ✅ Comprehensive error handling
- ✅ Detailed logging and metrics
- ✅ Single region focus for simplified operations

## Prerequisites
- Java 11 or later
- Maven 3.x
- AWS Account with Secrets Manager access
- EC2 instance with appropriate IAM roles
- AWS SDK for Java (v1.12.791)

## Quick Start

### Building the Project
```bash
mvn clean package

    

    
Running the Application
    
java -jar target/aws-secrets-manager-single-region-1.0-SNAPSHOT.jar

    

    
Implementation Details
Singleton pattern implementation
Efficient pagination handling
Performance metrics logging
Comprehensive error management
Author
Samson Oduor
