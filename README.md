# Vaadin Gradle Skeleton Starter

This project demos the possibility of having Vaadin project in npm+webpack mode using Gradle.
Please see the [Starting a Vaadin project using Gradle](https://vaadin.com/docs/latest/guide/start/gradle) for the documentation.

Prerequisites:
* Java 8 or higher
* node.js and npm. You can either let the Vaadin Gradle plugin to install `nodejs` and `npm/pnpm` for you automatically, or you can install it to your OS:
  * Windows: [node.js Download site](https://nodejs.org/en/download/) - use the .msi 64-bit installer
  * Linux: `sudo apt install npm`
* Git
* (Optionally): Intellij Ultimate

## Vaadin Versions

* The [v14](https://github.com/vaadin/base-starter-gradle) branch (the default one) contains the example app for Vaadin 14
* See other branches for other Vaadin versions.

## Running With Gretty In Development Mode

Run the following command in this repo:

```bash
./gradlew clean appRun
```

Now you can open the [http://localhost:8080](http://localhost:8080) with your browser.

## Building In Production Mode

Simply run the following command in this repo:

```bash
./gradlew clean build -Pvaadin.productionMode
```

That will build this app in production mode as a WAR archive; please find the
WAR file in `build/libs/base-starter-gradle.war`. You can run the WAR file
by using [Jetty Runner](https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner):

```bash
cd build/libs/
wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.26.v20200117/jetty-runner-9.4.26.v20200117.jar
java -jar jetty-runner-9.4.26.v20200117.jar base-starter-gradle.war
```

Now you can open the [http://localhost:8080](http://localhost:8080) with your browser.

### Building In Production On CI

Usually the CI images will not have node.js+npm available. Luckily Vaadin will download `nodejs` and `npm/pnpm` automatically, there is nothing you need to do.
To build your app for production in CI, just run:

```bash
./gradlew clean build -Pvaadin.productionMode
```

## Running/Debugging In Intellij Ultimate With Tomcat in Development Mode

* Download and unpack the newest [Tomcat 9](https://tomcat.apache.org/download-90.cgi).
* Open this project in Intellij Ultimate.
* Click "Edit Launch Configurations", click "Add New Configuration" (the upper-left button which looks like a plus sign `+`), then select Tomcat Server, Local.
  In the Server tab, the Application Server will be missing, click the "Configure" button and point Intellij to the Tomcat directory.
  * Still in the launch configuration, in the "Deployment" tab, click the upper-left + button, select "Artifact" and select `base-starter-gradle.war (exploded)`.
  * Still in the launch configuration, name the configuration "Tomcat" and click the "Ok" button.

Now make sure Vaadin is configured to be run in development mode - run:

```bash
./gradlew clean vaadinPrepareFrontend
```

* Select the "Tomcat" launch configuration and hit Debug (the green bug button).

If Tomcat fails to start with `Error during artifact deployment. See server log for details.`, please:
* Go and vote for [IDEA-178450](https://youtrack.jetbrains.com/issue/IDEA-178450).
* Then, kill Tomcat by pressing the red square button.
* Then, open the launch configuration, "Deployment", remove the (exploded) war, click `+` and select `base-starter-gradle.war`.

## Running/Debugging In Intellij Community With Gretty in Development Mode

Make sure Vaadin is configured to be run in development mode - run:

```bash
./gradlew clean vaadinPrepareFrontend
```

In Intellij, open the right Gradle tab, then go into *Tasks* / *gretty*, right-click the *appRun* task and select Debug.
Gretty will now start in debug mode, and will auto-deploy any changed resource or class.

There are a couple of downsides:
* Even if started in Debug mode, debugging your app won't work.
* Pressing the red square "Stop" button will not kill the server and will leave it running.
  Instead, you have to focus the console and press any key - that will kill Gretty cleanly.
* If Gretty says "App already running", there is something running on port 8080.
  See above on how to kill Gretty cleanly.
