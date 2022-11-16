# Currency-Converter
Currency app is designed to helps customers to hand different currency conversions.

## Design Pattern
MVI design pattern as recommended in docs [guide to app architecture](https://developer.android.com/jetpack/docs/guide) , with some modification to the network layer adding Kotlin coroutines, also using Hilt for dependency injection

## Used Technologies

* [Compose](https://developer.android.com/jetpack/compose) Jetpack Compose is Android’s recommended modern toolkit for building native UI
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous programming
* [Hilt](https://dagger.dev/hilt/) for dependency injection
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to bind data to ui
* [Retrofit] for networking
* [timper] for logging
* [customLogger] for logging formatting
* [Unit Testing] test business logic in view model
* [UI Testing] test compose components
## Demo

<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/demo.gif" width="300" height="700">

## Error Handling

Support Different Types of Errors:
* No internet Connection
* Un Authorized
* Other api issues based on documentation


<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/error.gif" width="300"  height="700">
<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/auth_error.png" width="300"  height="700">
<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/api_error.png" width="300"  height="700">
<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/no_internet_error.png" width="300"  height="700">

## TO DO
Add jacoco to generate test coverage, some classes with code coverage up to 100 % 
and the ability to increase the test coverage for other classes if required

<img src="https://github.com/hazem1262/Currency-Converter/blob/master/results/coverage.png" height="700">

## TO DO
* add more styling
* Currency Converter screen fully tested for showcase, add more tests to Transaction History Screen
* add integration test


