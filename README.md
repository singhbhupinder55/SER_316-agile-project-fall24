# Task 2: PMD Analysis Comparison

## Overview

Task 2 focuses on comparing the PMD analysis results from the `metrics1` and `metrics2` branches. The purpose is to evaluate the impact of work from Assignments 2-4 on code quality.

## Comparison of Results

| **Metric**              | **Metrics1** | **Metrics2** | **Change**                    |
|--------------------------|--------------|--------------|--------------------------------|
| Number of Violations     | 6            | 8            | Increased by 2                |
| High Coupling            | 1            | 1            | No change                     |
| Cyclomatic Complexity    | 6 violations | 5 violations  | Slight improvement in methods |
| Long Methods or Classes  | 0            | 2            | New violations introduced     |
| NPath Complexity         | 0            | 1            | New violation introduced      |

## Key Observations

1. **Increase in Violations**:
    - The total number of PMD violations increased from 6 in `metrics1` to 8 in `metrics2`.
    - New violations related to long methods and classes were introduced.

2. **Cyclomatic Complexity**:
    - While cyclomatic complexity remains an issue, some methods showed slight improvements.
    - However, new violations were introduced for the `makeGuess(String)` method.

3. **NPath Complexity**:
    - The `makeGuess(String)` method introduced an NPath Complexity violation with a value of 192, far exceeding the threshold.

## PMD Report Screenshot

Below is the screenshot of the PMD report for `metrics2`:

![PMD Report Metrics2](reportsImage/task2pmdreport.png)

## Conclusion

The work from Assignments 2-4 led to the introduction of additional violations, such as long methods and classes, and increased NPath complexity. While there are minor improvements in cyclomatic complexity, further refactoring is needed to address these issues and reduce code smells.



# Task 3.1: Making Code More Adaptable

## Objective
The goal of Task 3.1 was to enhance the `GuessingGame` class by:
1. Introducing dynamic difficulty levels to set the range of numbers randomly.
2. Implementing adaptable scoring strategies to provide flexibility in scoring methods.

---

## Description of Changes

### 1. Dynamic Number Ranges
- Added a `DifficultyLevel` enum with three levels: `EASY`, `MEDIUM`, and `HARD`.
- Modified the `GuessingGame` class to set a random target number (`correctNumber`) based on the selected difficulty:
   - **EASY**: Numbers between 1 and 10.
   - **MEDIUM**: Numbers between 1 and 50.
   - **HARD**: Numbers between 1 and 100.

---

### 2. Adaptable Scoring Strategies
- Introduced a `ScoringStrategy` interface, allowing different scoring methods to be implemented.
- Added two scoring strategies:
   - **SimpleScoring**: Deducts points based on the number of incorrect guesses.
   - **TimeBasedScoring**: Rewards points based on how quickly the correct number is guessed.

---

## Impact of Changes

### Advantages
1. **Increased Flexibility**:
   - The game now supports varying difficulty levels and scoring methods, making it more versatile.

2. **Improved Scalability**:
   - Adding new difficulty levels or scoring strategies is straightforward without altering existing logic.

### Potential Downsides
- **Increased Complexity**:
   - Introducing more configuration options could make testing more complex.
- **Maintenance**:
   - Additional features (e.g., new scoring strategies) might increase the maintenance burden.

---

## Test's
  - All the tests are still passing when running the gradle run command.

## Conclusion
The refactoring in Task 3.1 significantly improves the adaptability of the `GuessingGame` class. The new features allow the game to scale easily, provide varied gameplay experiences, and adhere to clean coding principles.


### Task 3.2: Duplicate or Similar Code

#### Problem Description:
The project contained duplicate and similar code in various areas, including state management, input validation, and difficulty range handling. Additionally, multiple constructors added unnecessary complexity. Methods like `makeGuess` also had high cyclomatic complexity due to nested conditional logic.

#### Changes Suggested and Rationale:
1. **State Management:**
    - Centralized all state-related logic in a dedicated `GameState` class.
    - This reduces duplication and provides a single source of truth for managing game state (`gameOver`, `guessCount`, `score`).

2. **Input Validation:**
    - Moved all input parsing and validation logic to an `InputValidator` class.
    - This eliminates repetitive validation code across the application and adheres to the single responsibility principle.

3. **Difficulty Range Handling:**
    - Encapsulated difficulty-specific range logic in the `DifficultyLevel` enum.
    - This makes the code more maintainable and adaptable to future changes in difficulty levels.

4. **Constructor Simplification:**
    - Replaced multiple constructors with a **Factory Method** design pattern.
    - The `GuessingGameFactory` class handles object creation, improving flexibility and eliminating redundancy.

5. **Method Complexity Reduction:**
    - Decomposed large methods like `makeGuess` into smaller helper methods (`validateGuess`, `handleOutcome`, `handleGameOver`).
    - This reduces cyclomatic complexity and improves readability and testability.

#### Changes Implemented in Code:
1. **Centralized Logic:**
    - Moved state management to `GameState`.
    - Centralized validation logic in `InputValidator`.

2. **Factory Method:**
    - Implemented `GuessingGameFactory` to handle `GuessingGame` creation with custom difficulty and scoring strategies.

3. **Refactored Methods:**
    - Simplified `makeGuess` by extracting logic into helper methods.
    - Reduced code duplication and improved separation of concerns.

#### Impact of Changes:
1. **Code Maintainability:**
    - Improved modularity by separating responsibilities across dedicated classes.
    - Easier to extend and adapt for future requirements (e.g., new scoring strategies or difficulty levels).

2. **Readability:**
    - Simplified complex methods, making the code easier to understand.

3. **Testing**
    - All the tests still workings.

#### Remaining Work (if any):
As of now, all duplicate or similar code has been addressed, and the changes have been implemented. The cyclomatic complexity of some methods is still slightly high but within acceptable thresholds due to the nature of their logic.

