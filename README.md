# EndLock (Fabric 1.21.1)

Simple mod to lock/unlock the End dimension.

## Commands

- `/lockend` - Locks the End dimension (requires OP level 2)
- `/unlockend` - Unlocks the End dimension (requires OP level 2)

## How it works

When the End is locked, players cannot enter it through End portals. 
The lock state is saved per-world in `endlock.json`.

Exiting the End (via the return portal) is never blocked.

## Build

1. Make sure you have JDK 21 installed
2. Run `./gradlew build` (Linux/Mac) or `gradlew.bat build` (Windows)
3. The built jar will be in `build/libs/`

## Install

Copy the built `.jar` file to your Minecraft instance's `mods` folder along with Fabric API.
