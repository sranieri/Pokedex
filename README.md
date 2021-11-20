# Pokedex
Sample pokedex app to display all pokemon in recycler view with a detail containing the pokemon types and abilities.

- This app is written in Kotlin.
- Uses Clean Architecture (Repository pattern) with Uniflow library.
- Use JetPack libraries such as ViewModel, Room, and Navigation
- Uses Hilt for dependency injection.
- Uses Retrofit2 and OkHttp3 as network libraries with coroutines.
- Uses Coroutines
- Uses Moshi
- Uses Glide
- Works both online and offline.

The app will display the pokemon list as paged. While the pokemon image is loading a placeholder image is shown, inside the detail fragment there is no progress shown
since all the datas are taken from the db used for the offline version. 
