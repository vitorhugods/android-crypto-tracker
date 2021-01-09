# Crypto Tracker

A simple way of getting price information on crypto-currencies

#### Currently supported currencies
- Bitcoin

## Project Structure
Feature-based modules, currently contains a single feature: `ticker`

### Modules

##### `app`
The executable app

##### `base`
Provides utility classes for different feature modules

##### `base-test`
Provides utility classes for testing features

##### `ticker`
Feature: Displays a chart with price information over the last week, month or year.
 
##### `blockchain-api-client`
Client that consumes the Blockchain API to get price information.

### Tech Stack

- 100% Kotlin
- Koin for DI
- OkHttp + Retrofit for networking
- RxKotlin3 for management of asynchronous tasks and reactive behaviour
- MVVM with Architecture Component's ViewModel and LiveData
