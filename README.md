# VehiclesManager
Sample of code using MVVM with Kotlin, Android Architecture Components, Dagger 2, Retrofit and RxAndroid

For Developers who are interested to learn new ways of developing an elegant and clean code with kotlin.

The project will be based on JSONPlaceholder API in order to display a list of vehicles.
The application will try to get vehicles from the local database. If there is no vehicle in it, it will retrieve them from JSONPlaceholder API, then will save them in database.

The following are included inside:
# Lifecycle library
adding a dependency to the lifecycle extension library (which includes both ViewModel and LiveData libraries) in the project.

# Retrofit
adding Retrofit’s adapters and converters and using RxJava to subscribe to the API calls to parse the JSON file.

# Dagger 2
using dependency injection to let Retrofit services be injected in the ViewModel. 

# Android Architecture Components:
#LiveData: building data objects that notify views when the underlying database changes.
ViewModel: storing UI-related data that isn't destroyed on app rotations.
Layout, DataBinding and BindingAdapters
Room: is a SQLite object mapping library
- if there are vehicles in the database, they will be shown
- if not, they will be retreived from API and stored the result in database
