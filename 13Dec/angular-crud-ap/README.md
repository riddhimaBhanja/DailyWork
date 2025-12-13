```md
# Angular CRUD Application

A clean and simple Angular application demonstrating complete **CRUD (Create, Read, Update, Delete)** functionality.  
This project is designed to help understand Angular fundamentals such as components, services, routing, HTTP communication, and state handling in a structured and practical manner.

---

##  Features

- User-friendly interface built with Angular
- Modular component-based architecture
- Centralized service layer for API communication
- Reactive data handling
- Scalable project structure suitable for learning and extension
- Supports live reload during development

---

## Tech Stack

- **Frontend Framework:** Angular (v21.0.3)
- **Language:** TypeScript
- **Styling:** CSS
- **Tooling:** Angular CLI
- **Testing:** Vitest (Unit Testing)

---

##  Project Structure (Overview)

```

src/
├── app/
│   ├── components/        # UI components
│   ├── services/          # Business logic & API calls
│   ├── models/            # Data models/interfaces
│   ├── app.component.*    # Root component
│   └── app.routes.ts      # Application routing
├── assets/                # Static assets
├── environments/          # Environment configurations
└── main.ts                # Application entry point

````

---

##  Getting Started

### Prerequisites

Ensure the following are installed on your system:

- **Node.js** (LTS recommended)
- **npm** (comes with Node.js)
- **Angular CLI** (v21+)

To verify Angular CLI installation:
```bash
ng version
````

---

###  Running the Application

Start the development server:

```bash
ng serve
```

Open your browser and navigate to:

```
http://localhost:4200/
```

The application will automatically reload when you make changes to the source files.

---

##  Code Scaffolding

Generate new Angular artifacts using the CLI:

```bash
ng generate component component-name
ng generate service service-name
ng generate pipe pipe-name
```

To see all available options:

```bash
ng generate --help
```

---

##  Build for Production

Create an optimized production build:

```bash
ng build
```

The compiled output will be available in the `dist/` directory.

---

## 🧪 Testing

### Unit Tests

Run unit tests using Vitest:

```bash
ng test
```

### End-to-End Tests

Angular CLI does not include an e2e framework by default. You may integrate tools like **Cypress** or **Playwright** as needed.

```bash
ng e2e
```

---

##  Learning Objectives

This project helps in understanding:

* Angular component lifecycle
* Dependency injection using services
* HTTPClient for API communication
* Routing and navigation
* Clean project organization
* Best practices for scalable Angular applications

---

