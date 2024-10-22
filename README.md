# Breast Cancer Classifier

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Contributions](https://img.shields.io/badge/contributions-welcome-brightgreen)

**Breast Cancer Classifier** is a Java-based machine learning project that classifies breast cancer cases based on provided datasets. The classifier is trained using a dataset of breast cancer cases and can predict whether a new case is malignant or benign based on various input features.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Training on Custom Data**: The classifier can be trained using a custom dataset (`train_data.csv`) and tested on a separate dataset (`test_data.csv`).
- **Graphing Capability**: Visualizes the performance of the classifier using the `Grapher` class.
- **CSV Input Handling**: Reads and processes input data (CSV files) via the `InputHandler.java`.
- **Test Framework**: Includes a test class (`BreastCancerClassifyTest.java`) to validate the classifier's performance.

## Project Structure

BreastCancerClassify/
├── .classpath              # Eclipse-specific classpath file
├── .gitignore              # Specifies files to ignore in Git
├── .project                # Eclipse project configuration
├── datasets/
│   ├── test_data.csv       # Test dataset (CSV format)
│   ├── train_data.csv      # Training dataset (CSV format)
├── src/                    # Source folder containing Java code
│   ├── BreastCancerClassify.java   # Main classifier logic
│   ├── BreastCancerClassifyTest.java # Tests for the classifier
│   ├── Grapher.java        # Class that handles graphing data (possibly for visualization)
│   ├── InputHandler.java   # Class for handling and processing input data (CSV)
├── .settings/
    └── org.eclipse.jdt.core.prefs  # Eclipse project settings

## License

This project is licensed under the MIT License.
