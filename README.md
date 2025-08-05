# 🛒 Getir Lite App

A lightweight and modular e-commerce application built with Jetpack Compose and a Clean Architecture approach.  
Developed as a feature-rich, scalable, and testable shopping cart experience using modern Android development practices.

---

## 🖼️ Screenshots


|  |  |  |
|----------------|----------------|----------------|
| <img src="https://github.com/user-attachments/assets/a9d6c851-f31c-4b1e-ad3a-c5888a3157b9" width="300" /> | <img src="https://github.com/user-attachments/assets/60d7b85c-6654-4e2c-9c85-ab73e9df120c" width="300" /> | <img src="https://github.com/user-attachments/assets/7869892f-332a-4523-aa46-e3fa894fafb6" width="300" /> |

|  |  |  |
|---------------------|-----------|------------------------|
| <img src="https://github.com/user-attachments/assets/e13cb71f-cba0-4b31-950b-bdde7761241e" width="300" /> | <img src="https://github.com/user-attachments/assets/4b1dfbdd-cb37-45ec-90fd-d2cf7b30864d" width="300" /> | <img src="https://github.com/user-attachments/assets/90308ef7-81d5-441d-9e87-59db17099f53" width="300" /> |

---

## 📦 Modules

The project follows a **multi-module** structure with **domain-driven design**. Here's an overview of the included modules:
```
📁 app  
📁 core  
├── 📁 common  
├── 📁 contract  
├── 📁 data  
├── 📁 domain  
├── 📁 presentation  
📁 feature  
├── 📁 common  
│   ├── 📁 domain  
│   └── 📁 data  
├── 📁 products  
│   ├── 📁 contract  
│   ├── 📁 data  
│   ├── 📁 domain  
│   └── 📁 presentation  
├── 📁 detail  
│   ├── 📁 contract  
│   └── 📁 presentation  
└── 📁 cart  
    ├── 📁 contract  
    └── 📁 presentation  
📁 network  
📁 test  
📁 ui  
```

---

## 🚀 Features

- 🧱 **Convention Plugin Usage**  
  Project-wide standardization of dependencies and Gradle logic via custom convention plugins for:
  - `data`, `domain`, `presentation`, `compose`, and `test` modules  
  - Simplifies setup and promotes DRY principles across modules

- 🧩 **Modular Clean Architecture**  
  Organized with a scalable multi-module structure based on domain-driven design principles.

- 💉 **Hilt Dependency Injection**  
  Provides lifecycle-aware DI across all layers.

- ⚙️ **Type-Safe Navigation System**  
  Built with `@Serializable` route definitions and a strongly typed `NavHost`.

- 🌐 **Modern Jetpack Compose UI**  
  - Efficient list rendering via `LazyColumn` and `LazyRow`  
  - Sticky buttons, smooth screen transitions, and nested scrolling support

- 🛍️ **Reactive Shopping Cart System**  
  - Add/remove products seamlessly across screens  
  - State is synced between Product List, Detail, and Cart screens  
  - Powered by `CartCacheManager` using `StateFlow`

- 💬 **Composable Dialogs**  
  - Includes confirmation and error dialogs  
  - Fully reusable and stylized for the design system

- 📊 **Dynamic UI State Handling**  
  Handles loading, empty, and error states in a clean, composable way

- 🎨 **Custom Design System**  
  Consistent theming via `LocalColorScheme` across all components

- 🧪 **Robust Unit Testing Setup**  
  - Covers ViewModels, cache logic, and UI interactions  
  - Uses:
    - `MockK`  
    - `kotlinx-coroutines-test`  
    - `Turbine`  
    - `JUnit 5` with `de.mannodermaus.junit5` plugin

- ⚡ **Animated Screen Transitions**  
  - Forward navigation: Slide **right → left**  
  - Back navigation: Slide **downward**

---

## 🧪 Testing

The project has strong test coverage across modules:

- ✅ **ViewModel Tests**: ViewModels tested with mocked dependencies
- ✅ **CacheManager Tests**: Ensures cache manipulation behaves correctly
- 🧪 Uses:
  - `mockk`
  - `kotlinx-coroutines-test`
  - `app.cash.turbine`
  - `android-junit5`

---

## 🔨 Build System

- Gradle Kotlin DSL
- Version Catalog via `libs.versions.toml`
- Custom **Convention Plugins** for `data`, `domain`, `presentation`, `compose
