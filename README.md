## Java: Discovering Its Power - EL ENG X436.2

This is a repo of all the code that I wrote for the Java Course that I underwent at UBC Extension for one of my pre-requisites for Heinz College at CMU.

## Project Structure

This is a single project, that has the following modules:

1. ModuleOneToSix - Has all the java files that I created for Modules 1 though 6
2. ModuleSeven - 3 main java files ChatStack.java, InflatableBalloon.java and Threader.java. Other Threader files are experimental. The Thread management coding assignment was particularly challenging given the all the thread count requirement. Good Learning! 
3. ModuleEight - FileIO with sampleFile
4. ModuleNine - Xmling with DTD and two dvd.xml sample files.
5. FinalProject - I create a Snake game! Heavily inspired from this [CodeReview](https://codereview.stackexchange.com/questions/151800/snake-in-javafx/151845#151845?newreg=25450db81cb24bb489f04fdb0e53f36a)

    For the Final project as I used JavaFX, I had to add references to JavaFX explicitly by passing run time arguments:

    ```"vmArgs": --module-path absolute\path\to\javafx\sdk\lib --add-modules=javafx.controls```

    Since after Java 11, JavaFX does not come packaged with JDK any more, you will have to download it from [here](https://gluonhq.com/products/javafx/)

    Also, I will check-in my .vscode folder too - Just so you can peek into the launch.json if you want a better understanding of what I am talking about.

I used VS Code to write all my programs just because I freaking love it. If you want to too, follow this: https://code.visualstudio.com/docs/java/java-debugging to setup Java on VS Code.

## Gratitude

Thanks to Mr. Carl Limsico for his guidance. I especially enjoyed the learning references that he pointed us to.
