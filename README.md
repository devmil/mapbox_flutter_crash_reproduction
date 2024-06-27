# Mapbox Flutter crash reproduction

This repository contains a sample app that reproduces a crash in the Mapbox SDK.
The crash happens if Android decides to delete the Activity and the engine is managed in a way so that its lifecycle is not attached to the lifecycle of the hosting Activity.

## Configuring the app
The app needs an public Mapbox Token set up in the file `mapbox_access_token.xml`. 
After that it runs fine.

## Reproducing the issue
As the issue only occurs on Activity destruction the easiest way to reproduce this is by enabling the "don't keep activities" developer option on Android.  
This way the Activity will be deleted every time the app goes to background.  

Steps to reproduce:
1. launch the app (world map is visible)
2. send the app to background
3. switch back to the app -> crash