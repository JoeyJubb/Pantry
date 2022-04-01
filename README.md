# Pantry Demo App

A super simple app that displays a list of items you've entered. One day it might become an app to
keep track of what food you've got in your cupboards. For now it exists as a technical demo for
interviews :D

## Module Overview

### app

- Provides implementation for Navigator interfaces
- Provides entry point to application
- Provides injection

### core

- Base module that all other depend on
- Exposes interfaces that are available to all modules

### data

- Implements repository interfaces

### feature

- Exposes one entry point (Activity, Fragment, Dialog or Service)
- Exposes one Navigator interface. This represents the "exit points" for the module.

## Implementing a module

Feature modules to be implemented with MVVM architecture.

#### Fragments/Activities/Services are the "View".

- Depends on a ViewModel
- Accepts user input and interprets that into high-level commands to the ViewModel
- Observes the ViewState exposed by the ViewModel and displays it on the screen
- Observes the Events emitted by the ViewModel and actions them
- They should not contain any business logic whatsoever.

#### ViewModels are the... "View Model".

- Depends on UseCases
- Accepts high level input e.g. "onAddButtonPress()"
- Exposes an observable ViewState to the View
- Unit-testable UI logic lives here. e.g. a test that requires the ViewState to be "Error" if a
  Usecase isn't working

##### View States

- Entirely describes the view required e.g. an Error state will contain an error message, a label
  for a "retry" button and the action to perform when the retry button is pressed
- Mutually exclusive e.g. can't show error at the same time as loading

#### UseCases are the "Model"

- Depends on Repositories
- Represents high level business logic like "Get a list of items"
- Responsible for mapping domain objects to view objects for the ViewModels to consume
- Unit testable business logic lives here. e.g. a test that will assure the UseCase emits a failure
  if the user isn't logged in

