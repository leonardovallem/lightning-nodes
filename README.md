## Build tools & versions used
- Gradle 8.11.1
- JDK 21

## Steps to run the app

After cloning the project, open it using Android Studio. The project should automatically sync through Gradle.
If not, you can manually sync it using the Gradle Sync button on the IDE.

To run the tests, you can run the command:
```shell
./gradlew test 
```

## What areas of the app did you focus on?

- Performance
  - Utilizing stable collections (kotlinx.collections.immutable)
  - Marking interfaces as @Stable
 
- Obfuscation
  - Specifying exclusion rules for ProGuard

## What was the reason for your focus? What problems were you trying to solve?

- Performance
  - Compose performance must be tweaked in order to guarantee the mitigation of unnecessary recompositions which make the app slower
  - Compose compiler don't "risk too much" trying to make objects stable, so we have to manually tell it when we guarantee the stability
  - I've faced many performance issues with compose in past projects

- Obfuscation
  - Attempts to avoid unexpected behaviors due to obfuscation of important classes

## How long did you spend on this project?
Around 4 hours

## Did you make any trade-offs for this project? What would you have done differently with more time?
I don't thing I had to make any trade-offs. However, if more time was available, I'd try to improve the UI.

## What do you think is the weakest part of your project?
Probably the UI design.
