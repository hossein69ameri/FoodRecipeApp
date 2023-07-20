# FoodRecipeApp
FoodRecipeApp is a sample Android project using [Spoonacular](https://api.spoonacular.com/) based on MVVM architecture. It showcases the latest Android tech stacks with well-designed architecture and best practices.
FoodRecipeApp is an app for adopting healthier habits and losing weight. In this clone app, you have access to add and manage your daily breakfast, lunch, dinner, and drink calorie data. Using this clone you can also cook or read multiple recipes with their ingredients, calories, time and all. You will find 20+ diet plans like Keto, Low Carb, Intermittent Fasting, Clean Eating, Bulk Up, Vegetarian, and more.

# Screen Shot
<img src="https://github.com/hossein69ameri/Blogs/assets/103646893/dac920cb-2fcc-4e2e-9a0f-16becd3ad19a" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | <img src="https://github.com/hossein69ameri/RecipeApp/assets/103646893/5397cf81-2420-4763-a1f1-9fb502a84d3d" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | <img src="https://github.com/hossein69ameri/RecipeApp/assets/103646893/11758a9e-4ccc-4b24-9423-186d39691063" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | 

# Features
100% Kotlin

Cache Data

MVVM architecture

Android Architecture Components

Kotlin Coroutines + Flow + LiveData

Single activity pattern

Dependency injection

# Tech Stacks
[Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.

[Hilt](https://dagger.dev/hilt/)  - Dependency injection.

[ViewBinding](https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that allows you to more easily write code that interacts with views 

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture)  - A collection of libraries that help you design robust, testable and maintainable apps.

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)  - UI-related data holder, lifecycle aware.

[Navigation component](https://developer.android.com/guide/navigation)  -  Fragment routing handler.

[Coroutines](https://developer.android.com/kotlin/coroutines) - Concurrency design pattern for asynchronous programming.

[Flow](https://developer.android.com/kotlin/flow) - Stream of value that returns from suspend function.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is an observable data holder class.

[DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil) - DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one.

[Room](https://developer.android.com/training/data-storage/room) - Save data in a local database using Room.

[Data Store](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers

[Lottie](https://lottiefiles.com/blog/working-with-lottie/getting-started-with-lottie-animations-in-android-app) - Animations in an Android App

[Coil](https://github.com/coil-kt/coil) - Image loading

# Architectures
![alt - Github](https://raw.githubusercontent.com/amitshekhariitbhu/MVVM-Architecture-Android/master/assets/mvvm-arch.png)

**View** : Activity/Fragment with UI-specific logics only.

**ViewModel** : It keeps the logic away from View layer, provides data streams for UI and handle user interactions

**Model** :  Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources. The local data sources will serve as single source of truth

# Contact
Have an project? DM me at

hossein.arabameri69@gmail.com
