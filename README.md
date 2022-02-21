# java-bowling-challenge

The current project is a CLI application for generating the scores of a ten-pin bowling game.

## Setup

1. Download/Clone this repository
2. Unzip and open the `.\JavaChallenge` folder

## Compiling the project

The code can be compiled with all its dependencies with the following maven command:

`clean install compile assembly:single`

## Running locally

To score a normal game, run following command inside project root folder:
```
java -jar target/JavaChallenge-0.0.1-SNAPSHOT-jar-with-dependencies.jar src\test\resources\positive\scores.txt
```
At the directory .\src\test\resources\filesToCompare it's possible to find files to check the output. 