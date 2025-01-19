<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <img src="https://github.com/user-attachments/assets/9cde9668-5f96-4299-a1e8-8644846f6f68" width="200"/>
  <br/>
  <h3 align="center">HabitBLOOM</h3>
  <p align="center">
    "One Step at a Time, Shape Your Habits!"
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    &middot;
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>  
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



# Habit Goal

Habit Goal is an Android application designed to help users build and maintain habits, track their progress, and achieve their personal goals. Built using Kotlin and following the Clean Architecture principles, this app provides a robust and scalable solution for habit tracking.

---

## Features

### 1. **Habit Tracking**
   - Add new habits with custom names and descriptions.
   - Set daily, weekly, or custom schedules for habits.

### 2. **Progress Monitoring**
   - Visualize progress for each habit through charts and progress indicators.
   - Track streaks and completion rates.

### 3. **Clean Architecture**
   - The application is built with the Clean Architecture design pattern, ensuring:
     - Separation of concerns.
     - Testability.
     - Scalability.

### 4. **Jetpack Libraries**
   - **Room Database**: For storing and managing user data locally.
   - **LiveData**: For observing and updating UI changes.
   - **ViewModel**: To manage UI-related data in a lifecycle-conscious way.
   - **Navigation Component**: For seamless navigation within the app.

### 5. **Lottie Animations**
   - Engaging animations enhance the user experience and provide visual feedback for actions like completing a habit or achieving milestones.

### 6. **Customizable UI**
   - Light and dark mode support.
   - User-friendly interface with intuitive controls.

---

## Technology Stack

- **Programming Language**: Kotlin
- **Architecture**: Clean Architecture
- **Database**: Room Database
- **Jetpack Libraries**: LiveData, ViewModel, Navigation Component
- **Animations**: Lottie

---

## Installation

### Prerequisites
1. Android Studio (latest version).
2. JDK 17 or later.
3. Gradle (built into Android Studio).

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/habit-goal.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Build and run the app on an emulator or physical device.

---

## Usage

### Adding a Habit
1. Open the app and navigate to the "Add Habit" screen.
2. Enter the habit name, description, and schedule.
3. Save the habit to start tracking.

### Tracking Progress
- View the list of habits on the dashboard.
- Mark habits as complete each day.
- Check streaks and overall completion rates on the "Progress" screen.

---

## Folder Structure

```plaintext
HabitGoal/
├── data/         # Data layer (Room entities, DAOs, repositories)
├── domain/       # Domain layer (Use cases, models)
├── presentation/ # Presentation layer (ViewModels, UI components)
├── utils/        # Utility classes and helpers
├── resources/    # XML files (layouts, strings, colors)
```

---

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Open a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Open a Pull Request


<!-- CONTACT -->
## Contact

Your Name - [@your_twitter](https://x.com/koc_etoertugrul) - etoertugrul.koc@gmail.com

Project Link: [https://github.com/etokoc/habitapp](https://github.com/etokoc/habitapp)

