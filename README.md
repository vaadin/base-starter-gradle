# Vaadin Gradle Skeleton Starter

This project demoes the possibility of having Vaadin 14 project in npm+webpack
mode using Gradle.

Prerequisites:
* Java 8 or higher
* node.js and npm installed locally. You can simply install those:
  * Windows: [node.js Download site](https://nodejs.org/en/download/) - use the .msi 64-bit installer
  * Linux: `sudo apt install npm`
  * TODO what to do in the CI environment
* Git
* (Optionally): Intellij Ultimate

> *Note*: this is an early preview which requires some extra steps to get the Vaadin
> Gradle Plugin. Soon the plugin will be deployed in the Gradle plugin repo which will
> simplify this tutorial radically.

## Installing Vaadin Gradle Plugin

Currently the Vaadin Gradle Plugin needs to be installed from sources. Please follow the
steps below to get it installed:

```bash
git clone https://github.com/vaadin/vaadin-gradle-plugin
cd vaadin-gradle-plugin
git checkout feature/18
```

Edit the `/build.gradle` file: at line 43 change the line from

```
version = project.hasProperty('BUILD_VERSION') //... yadda yadda
```

to

```
version = '0.0.1'
```

Now run:

```bash
./gradlew clean publishToMavenLocal -x test
```

The command will fail, BUT there will be a jar file installed in your local Maven repository:

```
$HOME/.m2/repository/com/vaadin/vaadin-gradle-plugin/0.0.1/vaadin-gradle-plugin-0.0.1.jar
```

The Vaadin Gradle Plugin is now ready to be used.

## Running With Gretty In Development Mode

Run the following command in this repo:

```bash
./gradlew clean vaadinPrepareFrontend appRun
```

Now you can open the [http://localhost:8080](http://localhost:8080) with your browser.

## Building In Production Mode

Simply run the following command in this repo:

```bash
./gradlew
```

That will build this app in production mode as a WAR archive; please find the
WAR file in `build/libs/gradle-skeleton-starter-flow.war`. You can run the WAR file
by using [Jetty Runner](https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner):

```bash
cd build/libs/
wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.26.v20200117/jetty-runner-9.4.26.v20200117.jar
java -jar jetty-runner-9.4.26.v20200117.jar gradle-skeleton-starter-flow.war
```

Now you can open the [http://localhost:8080](http://localhost:8080) with your browser.

## Running/Debugging In Intellij Ultimate With Tomcat in Development Mode

* Download and unpack the newest [Tomcat 9](https://tomcat.apache.org/download-90.cgi).
* Open this project in Intellij Ultimate.
* Click "Edit Launch Configurations",
click "Add New Configuration" (the upper-left button which looks like a plus sign + ),
then select Tomcat Server, Local. In the Server tab, the Application Server will be missing,
click the "Configure" button and point Intellij to the Tomcat directory.
  * Still in the launch configuration, in the "Deployment" tab, click the upper-left + button,
    select "Artifact" and select `gradle-skeleton-starter-flow.war (exploded)`.
  * Still in the launch configuration, name the configuration "Tomcat" and click the "Ok" button.

Now make sure Vaadin is configured to be run in development mode - run:

```bash
./gradlew clean vaadinPrepareFrontend
```

* Select the "Tomcat" launch configuration and hit Debug (the green bug button).

If Tomcat fails to start with `Error during artifact deployment. See server log for details.`, please:
* Go and vote for [IDEA-178450](https://youtrack.jetbrains.com/issue/IDEA-178450).
* Then, kill Tomcat by pressing the red square button.
* Then, open the launch configuration, "Deployment", remove the (exploded) war, click + and select `gradle-skeleton-starter-flow.war`.

## Running/Debugging In Intellij Community With Gretty in Development Mode

Make sure Vaadin is configured to be run in development mode - run:

```bash
./gradlew clean vaadinPrepareFrontend
```

In Intellij, open the right Gradle tab, then go into *Tasks* / *gretty*, right-click the
*appRun* task and select Debug. Gretty will now start in debug mode, and will auto-deploy
any changed resource or class.

There are couple of downsides:
* Even if started in Debug mode, debugging your app won't work.
* Pressing the red square "Stop" button will not kill the server and will leave it running.
  Instead, you have to focus the console and press any key - that will kill Gretty cleanly.
* If Gretty says "App already running", there is something running on port 8080. See above
  on how to kill Gretty cleanly.
