# wage-calculator

##  PROBLEM
Factory employees work two shifts, day and night
You want to calculate the daily wage according to the following points:

- a) The rate for the daily hours is $ 15000
- b) The rate for night hours is $ 20,000
- c) If it is Sunday, the rate will increase by $ 500 pesos on
day shift and $ 750 pesos the night shift

### How to start

**Note** that this seed project requires **java >=v1.8 and Maven >=3.6.1**.

In order to start the project use:

```bash
$ git clone https://github.com/uniremington-projects/wage-calculator.git
$ cd wage-calculator
# install the project's dependencies
$ mvn clean package
# to start the application run:
$ mvn clean compile exec:java
# prod build, will generate the production application in `target` in case it is required to directly execute the jar file
```
