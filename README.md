# RenderCosmosCore
Adds cosmetics and staff related tools for Render Network (https://rendernetwork.co/)

## Goal
Aims to give eveyone a great time 😉

## Todo
None

## Debug
Pretty much everything.

## Installation
1. Download and run the [PaperMC](https://papermc.io/) server jar.
1. Download the plugin from the [releases page](https://github.com/Render-Network/ChatSystem/releases)
   and move it to the plugins folder (`.minecraft/plugins`).
1. Run the Minecraft server.

## Contributing
1. Clone the repository
   ```
   git clone https://github.com/Render-Network/ChatSystem.git
   ```
1. Import the project into your preferred IDE.
    1. If you use IntelliJ (the preferred option), you can simply import the project as a Gradle project.
    1. If you use Eclipse, you need to `./gradlew eclipse` before importing the project as an Eclipse project.
1. Edit the code
1. After testing in the IDE, build a JAR to test whether it works outside the IDE too
   ```
   ./gradlew build
   ```
   The mod JAR may be found in the `build/libs` directory
1. [Create a pull request](https://help.github.com/en/articles/creating-a-pull-request)
   so that your changes can be integrated into the plugin
    - Note: for large contributions, create an issue before doing all that
      work, to ask whether your pull request is likely to be accepted
