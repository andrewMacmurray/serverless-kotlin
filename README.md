# serverless-springboot-kotlin
Example Spring Boot API deployed using serverless framework

## Run locally

If running from IntelliJ press the play button on `Application` main method
TODO - Run using localstack to test proxy handler integration

## Deploying

1. Make sure you have AWS account credentials on your path (`AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`)
2. Install serverless `npm install --global serverless`
3. Deploy with `./gradlew deploy`