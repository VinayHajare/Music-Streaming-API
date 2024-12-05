---

# 🎵 Music Streaming API 🎧

Welcome to the **Music Streaming API**, a robust backend system designed for a seamless music streaming experience. This API empowers users to **search**, **stream**, **create playlists**, and discover **new music**, complete with personalized recommendations and playback history.

---

## 🚀 Features

### 1. **User Management** 👤
- **Register**: Create an account with a username, email, and password.
- **Login**: Secure authentication using JWT.
- **Profile Management**: Update profile details such as bio and profile picture.
  - **Endpoints**:
    - POST `/api/auth/signup`: Register a user.
    - POST `/api/auth/login`: Authenticate a user.
    - GET `/api/profile/{username}`: Retrieve user profile details.
    - PUT `/api/profile/{username}`: Update user profile details.

---

### 2. **Song Management** 🎼
- **Stream Songs**: High-quality audio streaming with chunked transfer encoding.
- **Search Songs**: Find songs by title, artist, album, or genre.
- **Retrieve Songs**: Fetch song details by ID.
  - **Endpoints**:
    - GET `/api/songs`: Retrieve all songs.
    - GET `/api/songs/search?queryParam=value`: Search for songs.
    - GET `/api/songs/{id}`: Fetch details of a specific song.

---

### 3. **Playlist Management** 🎵
- **Create Playlists**: Organize your favorite songs into playlists.
- **Add Songs to Playlist**: Add songs to an existing playlist.
- **Shuffle Playlists**: Shuffle songs within a playlist.
  - **Endpoints**:
    - POST `/api/playlists/create`: Create a new playlist.
    - POST `/api/playlists/{playlistId}/addSong`: Add a song to a playlist.
    - GET `/api/playlists/user/{username}`: Fetch playlists by user.
    - GET `/api/playlists/{playlistId}/shuffle`: Shuffle songs in a playlist.

---

### 4. **Music Discovery & Social Features** 🎧
- **Recommendations**: Personalized song recommendations based on:
  - Playback history.
  - Genre preferences.
- **Follow Artists**: Stay updated with your favorite artists.
  - **Endpoints**:
    - GET `/api/discover/recommend/genre`: Get recommendations by genre.
    - GET `/api/discover/recommend/user/{username}`: User-specific recommendations.
    - POST `/api/discover/followArtist`: Follow an artist.
    - GET `/api/discover/recommendations`: Fetch recommendations based on playback history.

---

### 5. **Stream & Playback History** ⏯️
- **Stream Songs**: Play songs in real time with play, pause, skip, and repeat functionalities.
- **Playback History**: Track and retrieve songs you’ve listened to.
  - **Endpoints**:
    - GET `/api/stream/play/{songId}`: Stream a song.
    - GET `/api/playback-history`: Retrieve playback history.

---

## 📦 Test Suite
- We’ve included a detailed [Postman Test Suite](https://documenter.getpostman.com/view/37944914/2sAYBaBA96) to help you test all the API features seamlessly.

---

## 🛠️ Tech Stack
- **Java** with **Spring Boot** for backend development.
- **MySQL/PostgreSQL** for database management.
- **JWT** for secure authentication.
- **Postman** for API testing.

---

## 🎯 Getting Started

### Prerequisites
- **Java 17+**
- **Maven/Gradle**
- **MySQL/PostgreSQL**
- **Postman (optional)**

### Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd music-streaming-api
   ```

2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/music_streaming_db
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. Build and run the project:
   ```bash
   mvn spring-boot:run
   ```

4. Import the [Postman Test Suite](test\Music Streaming API Test Suite🎧.postman_collection.json) for testing.

---

## 🧪 Testing
- Use the [Postman Test Suite](test/Music Streaming API Test Suite🎧.postman_collection.json) for end-to-end API testing.
- Ensure all endpoints return the expected responses and data.

---

## 🚀 Deployment
1. **Dockerize** the application using a `Dockerfile`.
2. Deploy on platforms like:
   - **AWS ECS**
   - **Heroku**
   - **Azure App Services**
3. Configure environment variables for secure deployment.

---

## 📝 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
