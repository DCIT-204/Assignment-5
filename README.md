# Assignment_5

## Clone this repository

1. Open your terminal and navigate to the folder you want this repository to be cloned.
2. Paste the following command and hit enter.

```
git clone https://github.com/DCIT-204/Assignment-5.git
```

## Compile and run this program

### Prerequisites

Have JDK 17 or higher installed on your PC.

You can verify your JDK installation by running the following commands in your terminal

```
java --version
javac --version
```

If the versions are displayed, proceed to the compilation steps.

If you get a 'java is not recognized ...' error, install the JDK from [`the official Oracle site`](https://www.oracle.com/java/technologies/downloads/).

### Compilation steps

1. Clone this repository using the steps above, if you haven't.
2. Navigate to the cloned repository's directory

```
cd Assignment-5
```

3. Compile the java source files

```
javac src/*.java
```

### Run the application

1. Execute the compiled Application

```
java src.App
```

#### Potential Issues

If 'java' and 'javac' are recognized but compilation or execution fails, you may have to manually add Java's bin folder to your system's PATH variable. You can do this by following [`these steps`](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html).
