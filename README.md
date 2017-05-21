# GuideReviews
This is the implementation of the GYG take home test.
# Architecture
A simple MVVM application built using the latest Architecture components from Google.
# Libaries
This application was built using:

* [Retrofit2](https://github.com/square/retrofit "Retrofit")
* [InfiniteScroll](https://github.com/pwittchen/InfiniteScroll "InfiniteScroll")
* Android databinding
* Android architecture components
* Google Gson

# To be worked on
* Visual error handling when data is not available
* Caching of the data on the disk so it is available to the user when returning but offline
* moving loading calls into a Repository
* Indication that no data was loaded
* More complete handling of the add review web service method

# Add Review payload and service info
For the brevity sake of this project the service was mocked. 
In a real life implementation the review would be able to be added only by an authenticated user. In case of this specific example authentication was omitted. Additionaly the date should also be omitted from the payload as it would be filled server side. Thus the app has only the Review title, the message (review text) and the rating as completely necessary fields. The flag "isAnonymousComment" was added in case the user wants to have control over how the Author field is presented without them needing to go into their profile settings. The location is also being filled on the server based on the profile data of the user.
