# Automation Testing Project

This is a Selenium-based test automation framework built with Java, TestNG, and Maven.

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/automation/
│   │   │       ├── pages/          # Page Object Models
│   │   │       └── utility/        # Utility Classes
│   │   └── resources/
│   │       └── config.properties   # Configuration file
│   └── test/
│       ├── java/
│       │   ├── basetest/           # Base test class
│       │   └── testcases/          # Test cases
│       └── resources/
│           └── testing.xml         # TestNG configuration
├── pom.xml                         # Maven configuration
└── README.md
```

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- Git

## Setup Instructions

### Clone the Repository
```bash
git clone <repository-url>
cd untitled
```

### Install Dependencies
```bash
mvn clean install
```

### Running Tests
```bash
mvn test
```

## Collaboration Workflow

### For Team Members:

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd untitled
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make Your Changes**
   - Write your test cases in `src/test/java/testcases/`
   - Add page objects in `src/main/java/org/automation/pages/`
   - Add utilities in `src/main/java/org/automation/utility/`

4. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "Brief description of changes"
   ```

5. **Push to Remote**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**
   - Go to GitHub and create a pull request
   - Get it reviewed before merging to main

## Technologies Used

- **Selenium WebDriver** - Browser automation
- **TestNG** - Test framework
- **Maven** - Build automation
- **Apache POI** - Excel file handling

## Dependencies

- Selenium: 4.39.0
- TestNG: 7.11.0
- Apache POI: 5.2.3

## Best Practices

- Follow the existing code structure
- Use Page Object Model pattern for page interactions
- Write descriptive test names
- Always test locally before pushing
- Keep commits atomic and well-documented

## Contact

For issues or questions, contact the project maintainer.
