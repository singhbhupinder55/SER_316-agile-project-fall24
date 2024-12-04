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
