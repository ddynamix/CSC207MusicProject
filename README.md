# CSC207 Music Event Platform
### Instructor: Yasaman Rohanifar
### Summer 2024
### Team
- Meghan Hendrickson
- Tim Koukarine
- Tasnim Reza
- Tyler Steptoe

## Project Description
Our project is an advertising platform for music events.
Venues and Artists post their events and general audience users interact with the events and posts.
Audience users will be able to follow venues and artists as well as their friends.
Audience users will be able to post about events and share with their followers.
All users will be able to add a song to their profile using the Spotify API.

Our project database will be mongodb though we are currently using a temporary data file

We are currently using Branch Phase1 of the remote repo

Link to Repo: https://github.com/ddynamix/CSC207MusicProject/tree/main

## Use Cases
- Sign up
- Login
- .
- .

## Installation

To install this project, clone the repository and install dependencies:
- Maven 
  - Version 4.0.0
- org.mongodb
  - mongodb-driver-sync
  - version 4.11.2

## Project Structure

Our project structure follows Clean Architecture
Our runable main.java file is in src/main/java/app

## Entities
- User
  - AudienceUser
  - ArtistUser
  - VenueUser
- Event
- Post
- Song