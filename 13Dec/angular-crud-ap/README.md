# Angular CRUD Application

A simple and well-structured Angular application that demonstrates complete **CRUD (Create, Read, Update, Delete)** functionality.
The project focuses on clarity, maintainability, and practical usage of Angular concepts, making it suitable for learning as well as further extension.

---

## Features

* Full CRUD operations
* Clean and modular component-based design
* Centralized service layer for data handling
* Reactive data flow
* Scalable folder structure
* Live reload during development

---

## Tech Stack

* Frontend Framework: Angular (v21.0.3)
* Language: TypeScript
* Styling: CSS
* Tooling: Angular CLI
* Testing: Vitest

---

## Project Structure

src/
├── app/

│   ├── components/   

│   ├── services/          
│   ├── models/            
│   ├── app.component.*    
│   └── app.routes.ts      
├── assets/                
├── environments/          
└── main.ts                

---

---

## Getting Started

### Prerequisites

Ensure the following are installed on your system:

* Node.js (LTS version recommended)
* npm
* Angular CLI (v21 or higher)

Verify Angular CLI installation by running:

ng version

---

## Running the Application

Start the development server:

ng serve

Open your browser and navigate to:

[http://localhost:4200/](http://localhost:4200/)

The application will automatically reload whenever you make changes to the source files.

---

## Code Scaffolding

Generate Angular artifacts using the CLI:

ng generate component component-name
ng generate service service-name
ng generate pipe pipe-name

To see all available schematics:

ng generate --help

---

## Build for Production

Create an optimized production build:

ng build

The compiled output will be available in the dist/ directory.

---

## Testing

### Unit Tests

Run unit tests using Vitest:

ng test

### End-to-End Tests

Angular CLI does not include an end-to-end testing framework by default.
You may integrate tools such as Cypress or Playwright if required.

ng e2e

---

## Learning Objectives

This project helps in understanding:

* Angular component lifecycle
* Dependency injection using services
* HTTPClient for API communication
* Routing and navigation
* Clean project organization
* Best practices for building scalable Angular applications

---




