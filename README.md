# CSC207 Music Event Platform: The Best Project Ever!
### Instructor: Yasaman Rohanifar
### Summer 2024
### Team: (the) Best Team Ever!
- Meghan Hendrickson
- Tim Koukarine
- Tasnim Reza
- Tyler Steptoe

### Phase Two Assignment: August 15 2024

### Table of Contents

- Project Description
- Entities
- Use Cases
  - Views
- Accesibility
- Database
- Project Structure
- Installation

## Project Description
Our project is an **advertising** platform for music events.<br>
**Venues and Artists** can post their events and **general audience** users interact with the events and posts.<br>
Audience users can **follow venues and artists** as well as their friends.<br>
Audience users can **post** about events and share with their followers.<br>
All users can **add a featured song** to their profile using the Spotify API.<br>

Link to Repo: https://github.com/ddynamix/CSC207MusicProject/tree/main

## Entities
- User
  - AudienceUser
  - ArtistUser
  - VenueUser
- Event
- Post
- Song

## Use Cases
- Sign up as AudienceUser, VenueUser, or ArtistUser
- Login User of any type
- Create Post
  - Edit Post
- Create Event (_if of ArtistUser or VenueUser types_)
  - Edit Event
- Add favourite song
- Search for songs
- Search for other users/peers
  - Follow/Unfollow another user
- Search for events
  - Add event to logged-in user's events list
- Sign out of logged-in user's account
- Switch page

### Views

SplashView -- LoginView -- SignupView -- ProfileView<br>
HomescreenView -- EventScreenView <br>
PostMakerView -- EditPostView<br>
EventCrafterView -- EditEventView<br>
MyFollowersView -- MyFollowingView<br>
SearchEventsView -- SearchUsersView<br>

ViewManager<br>
Header

## Accessibility
Project includes **ToolTipText** for all buttons providing explanation of their function<br>
Once logged in, each page has an option to switch from light to **dark mode**

## Database
Our project uses Mongodb as our database
We also use local CSV files during creation as mock data that is stil functional
Regardless of Database type, the functionality of the program remains

## API: Spotify
Our project includes the Spotify API for users to add their favourite song and be able to play it 
within the app. The environment variables require keys that will be emailed to our team TA to
ensure the keys are not invalidated.

## Project Structure

Our project has been refactored to Screaming Architecture<br>
This system groups files by use case allowing for easier understanding <br>
It separates the Views and View_Models into their own folders as they apply more to the UI than the Use Case <br>
Our runable Main.java file is in src/main/java/app

_All words in italics and underlined are simplifications of the structure to reduce the visual/mental load_<br>
_All folders are bolded_ <br><br>
**Src/Main/Java/**
- **app**
  - **controller_factories**
    - _<u>factories for all controllers </u>_
  - **interface_adapter_tools**
    - Theme
    - UserSession
    - ViewManagerModel
    - ViewModel
  - ControllerCreater
  - HeaderFactory
  - Main
  - SwingViewCreator
  - ViewCreatorInterface
- **data_access**
  - **csv**
    -   _<u>all object DAOs and the general DAO factory</u>_
  - mongodb
  - **DatabaseConnection**
    - UserDataAccessObject
  - _<u> all interfaces for data access and related custom exceptions</u>_
- **entity**
  - _<u>**general**:  structure formula applied to Event, Post & Song </u>_
    - General
    - GeneralFactory
    - IGeneral _interface_
  - **user**
    - ArtistUser _extends User_
    - AudienceUser _extends User_
    - Uploadable _interface_
    - User
    - VenueUser _extends User_
- **use_case**
  - _<u>general example provided + sign out listed and specification for Screen_Switcher</u>_
  - _<u>use case Screen_Switcher has additional files for Data for each of its views</u>_
  - **example_use_case**
    - **interface_adapter**
      - Controller
      - Presenter
    - InputBoundary _interface_
    - InputData
    - Interactor
    - OutputBoundary _interface_
    - OutputData
  - **sign_out**
    - **interface_adapter**
      - SignOutController
    - SignOutInputBoundary _interface_
    - SignOutInteractor
- **view**
  - **jswing_views**
    - **utils**
      - _<u>CellRenderers + JPanels for lists + DialogHelper</u>_
    - _<u>view files as listed above under views</u>_
- **view_model**
  - <u> State and ViewModel files for all views</u>

## Installation

To install this project, clone the repository and install dependencies:
<br><br>API keys are not listed on the README but will be emailed to our team's TA
- **Maven** 
  - Version 4.0.0
- **org.mongodb**
  - mongodb-driver-sync
  - version 4.11.2
- **org.mockito**
  - mockito-core
  - version 5.12.0
- **org.mockito**
  - mockito-inline
  - version 5.2.0

Be sure to link **pom.xml** as maven project, click reload project, and run "install" in the maven toolbar<br>
Pom.xml found in project folder


## Attributions
- Icon: <a href="https://www.flaticon.com/free-icons/music" title="music icons">Music icons created by Freepik</a>
- Festival Gif: <a href="https://www.youtube.com/watch?v=1wlR4ECGJR8" title="Courtesy of PSM InfoTech">Courtesy of PSM InfoTech</a>
