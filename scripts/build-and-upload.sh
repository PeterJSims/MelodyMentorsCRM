#!/bin/bash

# REMEMBER TO SET spring.profiles.active to "prod"

# Run with 'bash build-and-upload.sh'

# Go one level up to main directory
cd ../

# Clean the /target folder and build a new JAR
mvn clean verify

#Login to AWS ECR
aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/a5c5s3u9

# Build an image of the new JAR from the first step
docker build -t melodymentors/melodymentorscrm .

# Tag the image
docker tag melodymentors/melodymentorscrm:latest public.ecr.aws/a5c5s3u9/melodymentors/melodymentorscrm:latest

# Publish the image to AWS
docker push public.ecr.aws/a5c5s3u9/melodymentors/melodymentorscrm:latest

