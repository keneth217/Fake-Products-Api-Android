📌 README Description for Your Fake API Android App
📖 Project Overview




 This project is a Jetpack Compose-based Android application that fetches product data from the Fake Store API using Retrofit. It follows the MVVM (Model-View-ViewModel) architecture, ensuring a clean and maintainable code structure. The app leverages Kotlin coroutines for handling API requests asynchronously, providing a smooth user experience. State management is handled using mutableStateOf, allowing real-time UI updates based on API responses. Additionally, error handling and loading indicators have been implemented to enhance reliability.

With Jetpack Compose as the UI framework, the app provides a modern and responsive interface. Retrofit is used to interact with the REST API efficiently, while Gson handles JSON conversion seamlessly. The project is designed with scalability in mind, making it easy to extend with additional features such as product details, filtering, or caching. This setup serves as a great starting point for integrating APIs in Jetpack Compose applications while maintaining clean architecture principles.

🛠️ Features
✔️ Fetch Products from Fake API using Retrofit
✔️ State Management with mutableStateOf
✔️ Jetpack Compose UI for modern UI development
✔️ MVVM Architecture for clean and maintainable code
✔️ Error Handling & Loading Indicators

📂 Tech Stack
Kotlin – Primary language
Jetpack Compose – Modern UI framework
Retrofit – HTTP client for API calls
Coroutines & ViewModel – Asynchronous data handling
State Management – mutableStateOf for UI state
📦 Dependencies (Gradle - Kotlin DSL)
Add these dependencies to your build.gradle.kts (Module: app):


dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.runtime:runtime:1.5.0")
    
    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}
