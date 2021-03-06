[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Carbon-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1491)&nbsp;&nbsp;
[![Maven Central](https://img.shields.io/badge/Maven%20Central-0.10.1-brightgreen.svg)](https://oss.sonatype.org/content/groups/public/tk/zielony/carbon/0.10.1/)&nbsp;&nbsp;
[![Dropbox](https://img.shields.io/badge/Dropbox-Sample%20app-brightgreen.svg)](https://www.dropbox.com/s/qp4gu6m5so1o0df/samples.apk?raw=1)

[![Google+](https://img.shields.io/badge/Google+-Zielony-red.svg)](https://plus.google.com/u/2/109054799904873578131)&nbsp;&nbsp;
[![Google+](https://img.shields.io/badge/Google+-Carbon-red.svg)](https://plus.google.com/u/1/communities/111973718340428039040)&nbsp;&nbsp;
[![Twitter](https://img.shields.io/badge/Twitter-GreenMakesApps-blue.svg)](https://twitter.com/GreenMakesApps)

Carbon
================
Material Design implementation for Android 2.2 and newer. This is not the exact copy of the Lollipop's API and features. It's a custom implementation of the most useful things as shown in the design specification. The library also features some additional non-standard extensions, like rounded corners for layouts or a Divider view for easy divider creation.

### Samples
[![Sample App](http://img.youtube.com/vi/YcTQ8a8sTpU/0.jpg)](https://www.youtube.com/watch?v=YcTQ8a8sTpU)

![Anchors](https://github.com/ZieIony/Carbon/blob/master/images/anchors.png)
![Spinner](https://github.com/ZieIony/Carbon/blob/master/images/spinner.png)
![Buttons / Usage sample](https://github.com/ZieIony/Carbon/blob/master/images/buttonsusage.png)
![Auto complete demo](https://github.com/ZieIony/Carbon/blob/master/images/autocomplete.png)
![Power menu demo](https://github.com/ZieIony/Carbon/blob/master/images/powermenu.png)
![Circular progress indicators](https://github.com/ZieIony/Carbon/blob/master/images/progress.png)
![Text appearances](https://github.com/ZieIony/Carbon/blob/master/images/textappearances.png)
![Calculator](https://github.com/ZieIony/Carbon/blob/master/images/calculator.png)
![ScrollView](https://github.com/ZieIony/Carbon/blob/master/images/scrollview.png)
![Sample app](https://github.com/ZieIony/Carbon/blob/master/images/sampleapp.png)
![RecyclerView and CardView](https://github.com/ZieIony/Carbon/blob/master/images/recyclercards.png)
![CheckBoxes](https://github.com/ZieIony/Carbon/blob/master/images/checkboxes.png)
![PagerTabStrip](https://github.com/ZieIony/Carbon/blob/master/images/pagertabstrip.png)
![Drawer](https://github.com/ZieIony/Carbon/blob/master/images/drawer.png)

### Features
 - realtime, animated shadows
 - the touch ripple
 - an elevation system (changing z order changes view rendering order)
 - widget tinting
 - rounded corners
 - SVG rendering
 - a floating action button view
 - text appearances, sizes, colors and many more useful definitions
 - custom, xml-selectable fonts for all text Views
 - predefined animation styles and visibility change animations
 - a saturation/brightness/alpha fade for ImageView
 - Divider drag&drop view
 - dark and light themes
 - a radial transition animation
 - circular progress indicators
 - CardView with real shadows and rounded corners
 - state animators
 - debug mode showing draw and hit areas when in edit mode
 - tab strip with animated underline
 - fading edges
 - animated checkboxes and radiobuttons
 - DrawerLayout with real shadows
 - EditText with floating label, character counter and pattern validation
 - Lollipop's recents list layout
 - Snackbar with swipe-to-dismiss, tap-outside-to-dismiss, message queue and view pushing
 - view anchors
 - Spinner with PopupMenu

### [Installation](https://github.com/ZieIony/Carbon/wiki/Installation)

### Android Studio

Add the following line to dependencies:

    compile 'tk.zielony:carbon:0.10.1'

And these two lines to `build.gradle` android/defaultConfig:

    renderscriptTargetApi 20
    renderscriptSupportModeEnabled true

In case of any problems with these check the sample app.

### Eclipse

Eclipse is not directly supported, but it's possible to use Carbon with Eclipse.

 - Download [.zip package](https://github.com/ZieIony/Carbon/releases) and extract it.
 - Create an Eclipse library project using that code.
 - Change the namespace, because 'carbon' is too short for Eclipse – you can change it to 'tk.zielony.carbon' for example.
 - Add all Carbon dependencies listed in build.gradle.
 - Set this project as a dependency for your app.

Alternatively you can try to use one of the Gradle/Maven plugins for Eclipse or try Android Studio – it’s pretty good and has Eclipse key shortcuts as well!

### Recent builds

There is a way to use builds which are not officially released yet. Simply use jitpack.io - a great tool for packaging and importing github repositories. For example to import commit '9e465d4f1a' apply instructions from the following link:

https://jitpack.io/#ZieIony/Carbon/9e465d4f1a

### [FAQ](https://github.com/ZieIony/Carbon/wiki/FAQ)

### [Changelog](https://github.com/ZieIony/Carbon/wiki/Changelog)

### License
```
Copyright 2015 Marcin Korniluk 'Zielony'

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
