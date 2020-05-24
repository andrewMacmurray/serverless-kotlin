# serverless-kotlin
Example Kotlin APIs deployed using serverless framework

1. Using Spring Boot
2. Using Http4k

## Run locally - Spring Boot

If running from IntelliJ press the play button on `Application` main method
TODO - Run using localstack to test proxy handler integration

## Run locally - Http4k

Running from IntelliJ press the play button on main method in `Server`

## Deploying (Both)

1. Make sure you have AWS account credentials on your path (`AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`)
2. Install serverless `npm install --global serverless`
3. Deploy with `./gradlew deploy`