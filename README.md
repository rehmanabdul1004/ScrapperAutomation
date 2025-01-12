# Web Scraping Automation using Selenium and TestNG

This project demonstrates how to automate web scraping using **Selenium WebDriver** and **TestNG**. It scrapes data from two different pages of the website [scrapethissite.com](https://www.scrapethissite.com/pages/) and stores the data into JSON files. The data includes:

1. **Hockey Teams Data**: Information about various hockey teams.
2. **Oscar Winning Films Data**: Details about Oscar-winning films, including their titles, awards, and nominations.

The extracted data is stored in two separate JSON files:

- `hockey-team-data.json`
- `oscar-winner-data.json`

## Table of Contents

- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Running the Tests](#running-the-tests)
- [Test Cases](#test-cases)
- [Test Data Output](#test-data-output)
- [Wrappers Utility](#wrappers-utility)
- [License](#license)

---

## Technologies Used

- **Java**: The main programming language used for writing the tests.
- **Selenium WebDriver**: For browser automation and web scraping.
- **TestNG**: For managing and executing tests.
- **ChromeDriver**: Used for automating the Chrome browser.
- **Jackson**: For serializing Java objects to JSON format.
- **Gradle**: For managing dependencies and building the project.
- **Logging**: Used for logging activities and errors.

---

## Setup

### Prerequisites

Before running the project, ensure the following are installed:

- **Java 8+**
- **Gradle** for dependency management.
- **Google Chrome** browser installed.
- **ChromeDriver** installed and path set up properly.
- **TestNG** for test management.

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/rehmanabdul1004/ScrapperAutomation
   cd ME_QA_XSCRAPE_DATA_MODULE_L1
   ```
