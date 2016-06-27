# Movies Finder
-----------------
Movies Finder in combination with Material design can be aligned with *Clean architecture* with *Model-View-Presenter (MVP)*.

It is decoupled between `android` and `domain`.

But inside the android module, there are some good practices:

An implementation of the *repository pattern* with a datasource (it could be extended to have a cache datasource in the future).
- the existing one is: API Rest Services data source
By means of synchronised requests to the API (`Retrofit) by handling properly
the different threads with a pool of thread and UI thread control.

The `Presentation` layer starts from `Presenters`, who communicates with a `View` from a UI component (fragments / activities).

Material design
---------------
- Movies Finder uses a wide range of Material design widgets from the Design support library such as:
- `AppBarLayout`, `CoordinatorLayout`, `Toolbar`, `RecyclerView`, `CardView`, `NestedScrollView`, `CollapsingToolbarLayout`


Features
--------
- Search for a movie when a user submits a query, then a list of search results will be displayed with a thumbnail image and title if found any
- Error handling integrated for Internet connection issues or if 0 results achieved
- Once clicked an item, the detail view of an item is shown, which shows all the specific information such as: authors, PEG, year, ranking, votes, etc
- A secret link is hidden after the *Author* field of every specific item (it launches a link)

Limitations
-----------
- For some unexpected eason the material design library 23.2.1 is hidden the back arrow button,
however, by clicking back button from your mobile you will achieve the same action.

SDK support
------------
Support SDKs from **15** to **23**

Disclosure - Libraries used (special thanks)
--------------------------------------------
- **Dagger 2** for Dependency Injection
http://google.github.io/dagger/
- **ButterKnife 6.1.0** for Views Injection
http://jakewharton.github.io/butterknife/
- **Retrofit 1.6.1** for API requests
http://square.github.io/retrofit/
- **Glide 3.6.0** for Image rendering
https://github.com/bumptech/glide


Created by
----------
**Raul Hernandez Lopez**
in June 2016

TW
---
**@RaulHernandezL**

email
-----
```raul.h82@gmail.com```

Copyright
---------
```
Copyright (C) 2016 Raul Hernandez Lopez

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