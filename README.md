# Task 1: PMD Setup and Metrics Analysis

## Overview

For Task 1, the PMD plugin was integrated into the project to analyze the code quality based on predefined rules in the `ruleset.xml` file. The PMD report highlights key metrics and rule violations to help identify areas for refactoring and code improvement.

## PMD Setup

1. **Branch Creation**:
    - A new branch `metrics1` was created from the `Blackbox` branch to isolate Task 1 changes.

2. **Configuration**:
    - The PMD plugin was added to the `build.gradle` file.
    - The `ruleset.xml` file, provided as part of the assignment, was placed in the same directory as `build.gradle`.
    - PMD was configured to generate HTML reports and use the custom rule set.

3. **Execution**:
    - The `gradle build` command was run to analyze the code, resulting in a report located at:
        - `build/reports/pmd/main.html`.

## PMD Metrics and Findings

| **File**          | **Line** | **Rule**                    | **Explanation**                                                                                                                                          |
|--------------------|----------|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `GuessingGame.java` | 1        | `CouplingBetweenObjects`    | High coupling due to excessive object dependencies.                                                                                                     |
| `GuessingGame.java` | 48       | `CyclomaticComplexity`       | Method `processValidGuesses(String)` exceeds complexity threshold (4).                                                                                   |
| `GuessingGame.java` | 73       | `CyclomaticComplexity`       | Method `calculateAverage(Set)` exceeds complexity threshold (3).                                                                                         |
| `GuessingGame.java` | 85       | `CyclomaticComplexity`       | Method `setGameOver(Boolean)` exceeds complexity threshold (4).                                                                                          |
| `GuessingGame.java` | 93       | `CyclomaticComplexity`       | Method `getGameOver()` exceeds complexity threshold (2).                                                                                                 |
| `Main.java`        | 2        | `CyclomaticComplexity`       | Method `main(String)` exceeds complexity threshold (2).                                                                                                  |

## Screenshot of the PMD Report
Below is the screenshot of the PMD report:

![PMD Report Screenshot](reportsImage/pmdReporttask1.png)

## Analysis of Results

1. **High Coupling**:
    - The `CouplingBetweenObjects` rule was violated in `GuessingGame.java` due to a high number of object dependencies.
    - This can make the code harder to test and maintain.

2. **Cyclomatic Complexity**:
    - Multiple methods exceeded the complexity thresholds set in the `ruleset.xml` file.
    - High complexity can make methods harder to understand and maintain.

## Conclusion

The PMD analysis identified several areas of improvement in the code, primarily focusing on high coupling and cyclomatic complexity. These metrics highlight issues that could lead to reduced maintainability and increased difficulty in testing. For example, methods like `processValidGuesses` and `setGameOver` have higher-than-ideal complexity, which suggests they should be simplified or refactored to improve readability and maintainability.

