# Introduction

Technologies:
Selenium, Java, Maven, Cucumber & intellij idea

After analyzing the client's application and various automation tools, we selected Selenium which is one of the most
popular open‐source functional and regression testing tools and very well suited for the client's needs. We went through
the application, understood its functionality and work‐flow, and prepared our automation plan. We designed a high level
hybrid framework which made the scripts very easy to run even for non-technical users. We developed sufficient sets of
scripts to be simply run whenever there was a change in the application. Also, we have introduced cucumber with selenium
for test case writing in gherken formet. So tech and non-tech both parties understand the test details.

# Getting Started

TODO: Guide users through getting your code up and running on their own system. In this section we can talk about:

1. Installation process
2. Software dependencies
3. Latest releases
4. API references

# Environments Name

#### For now, This project have configured with three environments properties. You can add more as needed.

1. Local(For Local machine)
2. Dev(For dev server)
3. Qa(For test server)

# Test Casses

#### For now, This project have below test casses

1. TC00_MasterRunner
2. TC01_LoginRunner

# Build and run test with different environment

#### Below are command to run automation script with different environment:

    mvn test -Denv=<environment name>
    (ex: mvn test -Denv=Dev)

# Contribute

TODO: Explain how other users and developers can contribute to make your code better.