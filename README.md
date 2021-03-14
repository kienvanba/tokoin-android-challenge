# Tokoin Test For Mobile Developer

A Simple Android news application that:

- [x] Use https://newsapi.org/ as data source. 
- [x] Written in `Kotlin`. 
- [x] Using MVVM design pattern. 
- Android Architecture Components (`ViewModel`, `LiveData`, `Data Binding`).  
- Navigation Component
- `Retrofit` with `RxJava` for networking. 
- `Koin` for dependency injection. 
- [ ] Has testing
- `Unit tests`. 
- `Component/ Functional/ Integration tests`. 
- `UAT/ end-to-end tests`. 

### Features

Application allows user to surf through and read top headlines news or news based on user's interest. :+1:


![News List][screenshot1] ![News Detail][screenshot2] ![News Original][screenshot3] ![News List][screenshot4] ![Profile][screenshot5]

### Installation

You can 
- Install the application on Play Store (Internal Testing) by sending an email to `kien.vanba@gmail.com` ***Using the same email address*** you're using to register on Play Store.
 I will send you an invitation to test the app. 
- Install the application by installing the [apk file]. 
- Run this repo in Android Studio. 

### Source code structure

```
- data
  - local       # contains local data storage such as SharedPreferences or DB, in this app only SharedPrefrences is used. 
  - network     # define network services. 
  - repository  # contains all data repository using in application. 
- di            # config all dependency injection within the app using Koin. 
- model         # all model data classes. 
- ui            # screens and views. 
  - base        # contains all base (open & abstract) classes. 
  - activity    # contains app activities. 
  - fragment    # contains app screens. 
  - view        # contains views such as dialogs, custom views... 
- util          # contains app utilities such as constants, helpers, extensions... 
```

<!-- MARKDOWN -->
[apk file]: tokoin-challenge-11b2.apk
[screenshot1]: images/screen_shot_1.png
[screenshot2]: images/screen_shot_2.png
[screenshot3]: images/screen_shot_3.png
[screenshot4]: images/screen_shot_4.png
[screenshot5]: images/screen_shot_5.png
