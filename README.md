# NextFTC v2 + Sloth Setup Guide

This guide explains how to use **NextFTC v2** and **Sloth** in the HVSummer26 FTC project.

The repository is already configured with the required dependencies, Gradle configuration, and Sloth deployment tasks. **You do not need to install or configure these manually.**

---

## What is Sloth?

Sloth is a fast deployment system for FTC. Instead of installing a complete APK every time you make a small change to `TeamCode`, Sloth allows you to rapidly deploy your code to the Control Hub.

### Use Sloth when:

* Changing Java/Kotlin code in `TeamCode`
* Testing robot logic
* Iterating quickly during development
* Making frequent small code changes

### Use a normal Android Studio install when:

* Changing Gradle dependencies
* Changing `build.gradle` files
* Changing the FTC SDK
* Changing NextFTC/Sloth versions
* Making changes that require a complete APK rebuild

---

# Initial Setup

After cloning the repository, open the project in **Android Studio** and allow Gradle to sync.

You should **not need to add any dependencies manually**. The repository already contains the required NextFTC and Sloth configuration.

Before using Sloth, make sure the project can successfully perform a **normal Android Studio installation** to the Control Hub.

---

# Deploying with Sloth

The repository contains a Gradle task called:

```text
:TeamCode:deploySloth
```

This is the primary way to quickly deploy `TeamCode`.

## Creating the Deploy Sloth Run Configuration

If the configuration is not already present in your Android Studio setup:

1. Open **Run → Edit Configurations...**
2. Click **`+`**
3. Select **Gradle**
4. Name it:

```text
Deploy Sloth
```

5. Set the Gradle project to the project root (`HVSummer26`).
6. Set **Tasks** to:

```text
:TeamCode:deploySloth
```

7. Click **Apply → OK**.

You can now select **Deploy Sloth** from the run configuration dropdown and press **Run ▶**.

---

# Normal Android Studio Installation

You should also have a normal **Android App** configuration for installing the complete TeamCode APK.

The configuration should look approximately like:

```text
Type: Android App
Module: HVSummer26.TeamCode
Launch: Nothing
Deploy: Default APK
```

FTC does not use a normal Android launcher Activity, so **Launch should be set to `Nothing`**.

The normal Android App configuration should also have the following task under **Before launch**:

```text
:TeamCode:removeSlothRemote
```

This is important because it removes the Sloth deployment before performing a normal APK installation.

Your Before Launch section should contain something similar to:

```text
Gradle-aware Make
Run Gradle Task: :TeamCode:removeSlothRemote
```

---

# Which Deployment Method Should I Use?

| Change                              | Deployment                     |
| ----------------------------------- | ------------------------------ |
| Java changes                        | **Deploy Sloth**               |
| Kotlin changes                      | **Deploy Sloth**               |
| Robot logic changes                 | **Deploy Sloth**               |
| Subsystem changes                   | **Deploy Sloth**               |
| OpMode changes                      | **Deploy Sloth**               |
| Gradle dependencies                 | **Normal Android App install** |
| `build.gradle` changes              | **Normal Android App install** |
| NextFTC version changes             | **Normal Android App install** |
| Sloth version changes               | **Normal Android App install** |
| FTC SDK changes                     | **Normal Android App install** |
| Major project configuration changes | **Normal Android App install** |

### Rule of thumb

If you only changed code inside `TeamCode`, **try Deploy Sloth first**.

If you changed the project's build configuration or dependencies, use a **normal Android Studio installation**.

---

# Using Sloth During Development

A typical development cycle should look like this:

```text
Write code
    ↓
Build / compile
    ↓
Deploy Sloth
    ↓
Test on robot
    ↓
Make changes
    ↓
Deploy Sloth
    ↓
Test again
```

This avoids repeatedly installing the entire Robot Controller APK.

---

# Using NextFTC

This project uses **NextFTC v2**.

NextFTC provides the command-based framework and hardware abstractions used by our robot code.

For example, a command can be created and scheduled with:

```java
DriveCommandsKt.mecanumDrive(
        drivetrain.frontLeft,
        drivetrain.frontRight,
        drivetrain.backLeft,
        drivetrain.backRight,
        gamepad1,
        new MecanumKinematics()
).schedule();
```

Commands that are intended to continuously run can be scheduled once rather than being recreated every `periodic()` loop.

For example:

```java
@Override
public void start() {
    robot.startDrive(gamepad1);
}
```

rather than:

```java
@Override
public void periodic() {
    robot.startDrive(gamepad1);
}
```

---

# Sloth and Normal Installs

Sloth creates a remote deployment of your `TeamCode`. Because of this, **do not switch randomly between Sloth deployment and normal APK installation without understanding what is happening**.

The normal Android App configuration includes:

```text
:TeamCode:removeSlothRemote
```

as a Before Launch task.

This ensures that when you perform a normal installation, the existing Sloth deployment is removed first.

---

# Troubleshooting

## `deploySloth` fails with a Gradle error

First make sure you are using the Gradle version committed with the repository.

Do **not** manually upgrade Gradle unless you know that the Sloth version supports it.

The current project uses a Gradle version compatible with the installed Sloth version.

---

## Normal Run button is gray

Check the Android App run configuration.

It should have:

```text
Module: HVSummer26.TeamCode
Launch: Nothing
Deploy: Default APK
```

The most common issue is having:

```text
Launch: Default Activity
```

FTC does not have a normal launcher Activity for TeamCode, so use:

```text
Launch: Nothing
```

---

## Sloth deployment succeeds but your changes don't appear

Try:

1. Stop the running OpMode.
2. Run **Deploy Sloth** again.
3. Start the OpMode again.

If the problem persists, perform a **normal Android Studio installation**.

---

## Gradle dependencies were changed

If you added, removed, or changed a dependency, use the normal Android App installation.

For example, after changing:

```gradle
implementation 'some.library:example:1.0.0'
```

do **not** rely on Sloth. Perform a full APK installation.

---

# Important

### Do not modify the Sloth/NextFTC Gradle configuration unless necessary.

The repository is already configured for the team.

If you encounter a Gradle or dependency error, **ask the software lead before changing the versions of NextFTC, Sloth, Gradle, or the FTC SDK**.

Changing one of these versions can cause compatibility issues across the entire project.

---

# Quick Reference

### Fast deployment

```text
Run Configuration → Deploy Sloth
```

Task:

```text
:TeamCode:deploySloth
```

### Full deployment

```text
Run Configuration → TeamCode
```

Module:

```text
HVSummer26.TeamCode
```

Launch:

```text
Nothing
```

Deploy:

```text
Default APK
```

Before Launch:

```text
:TeamCode:removeSlothRemote
```

### In short

> **Code change → Deploy Sloth**
>
> **Dependency/configuration change → Normal Android Studio install**
