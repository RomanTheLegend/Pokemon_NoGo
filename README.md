# Pokemon NoGo

Desktop client for playing Pokemon GO on any PC
It can work both with emulators and mobile phones. Connection is done over ADB socket forwarding

Features:

* Seamless gameplay experience
* Full control over player's movements
* Ability to automate navigation between locations
* Own Pokemon Detector which helps locating Pokemon nearby
* Checkpoints for farming the best locations
* Patrol mode between any number of checkpoints
* Pokedex style :)

## Attention: Niantics forces the update to Pokemon GO client v.0.31 which ignores old hooks for GPS spoofing with results in  "GPS not found" issue. We're working on resolving this on a top priority

![Pokemon NoGo screenshot]( https://github.com/RomanTheLegend/Pokemon_NoGo/blob/master/Screenshot2.PNG )




## Please support this project by following my twitter or reddit thread

#[Official Twitter] (https://twitter.com/RomanTheLegend) 
Please give follow up or contact me here. Any feedback is much appreciated.

**Official Reddit discussions is moving to another sub, new link will be provided shortly**






## Prerequisites
* NOX App Player (http://en.bignox.com) - there are two versions available. Please use the one WITHOUT Pokemon GO preinstalled
* Xposed framework (use this exact version: http://dl-xda.xposed.info/modules/de.robv.android.xposed.installer_v33_36570c.apk )
* Mock Mock Locations (is installed through Xposed)
* Android terminal emulator (https://apkpure.com/terminal-emulator-for-android/jackpal.androidterm)

* Dedicated PTC account for Pokemon detector. ** DO NOT USE YOUR MAIN ACCOUNT FOR THAT**
	(also please activate it by starting a game and picking your first Pokemon)

## How to setup
(Youtube video with installation steps and client demo will come in the next few days)

1. Download latest Pokemon NoGo files (v4.3 alpha)
2. Install NOX player, launch it and go to settings:	
	* Under "General" make sure that "Root" is ON
	* Under "Advanced" set "Performance Settings" to "High".
	* Restart	
3. Go to android settings (not NOX settings) and enable "Developer Mode" by going to "About tablet" and tapping "Build number" 7 times
4. Go to android settings (not NOX settings) and enable "USB debugging" and "Allow mock location" in Developer options
5. Install Xposed framework (click on icon on the right side panel of NOX player. It looks like box with APK inside and arrow)
6. Open "Xposed Installer" click on "Framework" and click the "Install/Update". When system asks for Root access select "Remember choice forever" and click Allow. After it finishes it will ask to reboot. DO NOT LET IT REBOOT, choose "Soft reboot" instead.
7. After rebook verify that everything under "Framework" is installed. If not - install again
8. Go to "Download" inside of "Xposed Installer" hit the magnifying search button and write "Mock Mock Locations" , we click it, swipe to the right and install it.
9. Reboot
10. Go to "Xposed Installer" -> "Modules" and make sure the box to the right of "Mock Mock Locations" is ticked
11. Install Pokemon GO
12. Click the "Mock Mock Location", uncheck "All apps" option, click "Add app" and look for Pokemon Go
13. Another reboot

14. Install and launch terminal emulator
```
su
mv /system/lib/hw/gps.nox.so /system/lib/hw/gps.nox.so.bcp
```

If file is not there then find it with

```
find / -name "*gps*"
```

and then rename it and reboot


15. Install Pokemon NoGo .apk file and launch it (click on Start - you should see "Waiting for client connection")
16. Click "Start"
17. Open Windows command line 
18. Right click on NOX icon -> Properties -> Open File Location. Then locate nox_adb.exe in that folder and drag it to command line
19. Paste this to the command line and press Enter* :
```
 -s 127.0.0.1:62001 forward tcp:1234 tcp:9090
```

*- after doing  steps 17-19 and before pressing Enter the command line should look similar to this:
```
"C:\Program Files\NOX\Nox\bin\nox_adb.exe" -s 127.0.0.1:62001 forward tcp:1234 tcp:9090
``` 
where path to nox_adb.exe might be different on different systems

20. Launch AshBurn client, make sure that Pokemon NoGo log window has "Client connection detected"
21. Launch Pokemon GO and enjoy the game :)
22. To enable Pokemon detector, please provide your secondary PTC login in AshBurn client under Settings

-------------

For people who have problems running this on NOX, here's complete NOX VM image with everything preinstalled and configured:

https://goo.gl/x5RiK1

Instructions are inside

-------------


## Warnings

Using this software is against the ToS of the game. You can get banned, use this tool at your own risk.
Software comes "As is". I'm not responsible for any issues it may cause.

## Big Thank You!

* To https://github.com/Grover-c13/PokeGOAPI-Java/ project for making great API
* OwnedCore forum for helping with NOX player
* Reddit for motivating me and helping to improve the project

