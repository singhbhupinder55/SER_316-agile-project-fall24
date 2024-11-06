# Code Review Defect List

**Reviewer:** Bhupinder Singh  
**GitHub Repo:** [Repository Link](https://github.com/singhbhupinder55/ser316-fall24B-bsingh55)

| ID # | Location (File and Line Number) | Problem Description | Category | Severity |
|------|---------------------------------|----------------------|----------|----------|
| 1    | GuessingGame.java:8             | `public int correctNumber` lacks encapsulation, should be private | CS       | MJ       |
| 2    | GuessingGame.java:11            | Variable `Guess_count` does not follow camelCase convention | CS       | MI       |
| 3    | GuessingGame.java:48            | Method `processValidGuesses` does not handle numbers outside valid range (1-100) | FD       | HI       |
| 4    | GuessingGame.java:73            | Method `calculateAverage` contains unused variable `unusedVar` | CG       | MI       |
| 5    | GuessingGame.java:88            | Incorrect boolean check `isOver == true`, should simplify to `if (isOver)` | CG       | MI       |
| 6    | Main.java:7                     | Hard-coded numbers in `makeGuess` calls, should use constants or variables | CG       | MI       |
| 7    | GuessingGame.java:63            | `resetGame` method assigns `null` to `previousGuesses`, which can lead to a `NullPointerException` if accessed afterward. Should use `clear()` instead. | FD       | HI       |

**Category Legend:**
- CS: Code Standards
- CG: Code General
- FD: Function Defect

**Severity Legend:**
- HI: High Impact
- MJ: Major Impact
- MI: Minor Impact
