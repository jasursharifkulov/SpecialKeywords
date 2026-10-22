# Laboratory 6 — Special Classes

15 console exercises based on the supplied laboratory and lecture: enum classes, sealed classes, sealed interfaces and exhaustive when expressions. Kotlin 2.2.10 supports the task 13 guard conditions. No extra application libraries are needed.

## Open and run

1. Open the existing SpecialKeywords project in Android Studio.
2. Sync the project with Gradle after adding the module.
3. In Project view, open specialclasses/src/main/kotlin.
4. Open task01/Main.kt (or any task02 through task15).
5. Run the main function using the green arrow and enter input in the Run window.

These programs run in the console, not on the phone emulator. Each package is independent. Task 05 intentionally preserves a separate extended version of Task 04. Task 12 has several files to demonstrate inheritance across files and packages.

From the project root you can also run:

```sh
./gradlew :specialclasses:runTask01 --console=plain
./gradlew :specialclasses:build
```

Replace 01 with 02 through 15 to select an exercise.

## Files

- ANSWERS.md: written explanations for all 15 tasks, including the deliberate compiler-error experiments.
- VERIFICATION.md: actual inputs and outputs from 41 successful runtime scenarios covering all tasks.
- src/main/kotlin: final working programs, including requested extensions.

The intentionally invalid experimental versions are explained in ANSWERS.md and are not left in the working source. The module build completed successfully; there are no unit tests in this module. The recorded checks execute the console programs directly.
