# GloboFly ✈️

**GloboFly** is a modern Android app built with Kotlin that lets you explore, add, update, and delete global travel destinations. The app uses Retrofit to interact with a live REST API (Node.js, hosted on Railway). Its clean interface and modular structure make it a great example for Android networking and CRUD operations.

---

## Features

🌍 **Browse Destinations**
- View a list of all travel spots from the cloud API.

➕ **Add Destinations**
- Fill in city, country, and description to add a new destination.

✏️ **Edit & Delete**
- Update or remove destinations with simple forms.

🔗 **Live API Integration**
- All data synced in real-time with a Node.js backend via Retrofit.

🚀 **Modern Android UI**
- Material look, navigation, and responsive layouts.

---

## Screenshots

*![Globo Fly](https://github.com/user-attachments/assets/70065598-d223-4128-ab6b-d60279635e92)*

---

## Quick Start

1. **Clone the repo**
    ```sh
    git clone https://github.com/MohsinMuzamil/GloboFly-.git
    ```
2. **Open in Android Studio**

3. **Run the app**
    - Connect your device or start an emulator.
    - Click “Run”.

**No backend setup required:** uses the live API at  
[https://globofly-production.up.railway.app/](https://globofly-production.up.railway.app/)

---

## Folder Structure (Key Files)

```
com.mohsin.globofly
├── activities/            # Screens: List, Detail, Create, Welcome
├── helpers/               # Adapters & Sample Data
├── models/                # Destination data class
└── services/              # Retrofit API interface & builder
```

- **DestinationListActivity** – Lists all destinations
- **DestinationDetailActivity** – View, edit, or delete a destination
- **DestinationCreateActivity** – Add a new destination
- **DestinationAdapter** – Powers destination RecyclerView
- **DestinationService** – Retrofit interface for API endpoints
- **ServiceBuilder** – Configures Retrofit with live API base URL

---

## API & Networking

- **Powered by [Retrofit](https://square.github.io/retrofit/)** for all HTTP requests
- **Live backend**: Node.js API, always online via [Railway](https://railway.app/)
- **Endpoints:**
    - `GET /destination` – List all destinations
    - `POST /destination` – Add new destination
    - `PUT /destination/{id}` – Update destination
    - `DELETE /destination/{id}` – Remove destination

---

## Requirements

- Android Studio (latest)
- Kotlin
- Min SDK: as defined in `build.gradle`
- Retrofit & Gson
- Material Components

---

## How It Works

1. **Welcome Screen:** Tap to start.
2. **Destination List:** See all places from the live API.
3. **Add/Edit/Delete:** Use forms to manage destinations. Actions sync instantly with the backend.

---

## Contributing

Pull requests and issues are welcome! Please follow standard Android/Kotlin best practices.

---

## License

MIT License

---

**Designed and maintained by Mohsin Muzamil**
