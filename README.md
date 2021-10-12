
# about

the goal of this project is to contain multiple implementations of ML algorithms in java

# pre requisites

* java 11
* maven

# install

Just run:

```
mvn clean install
```

# run it

TO-DO

To be honest, I haven't integrated any easy way to run it from the terminal, however, you can call directly the test classes from your IDE :'v

# algorithms

## simple linear regression

1 dimensional problem for linear regression with a dataset of bitcoin prices

## multi linear regression

n-dimensional implementation for handling linear regression

# structure

## org.learning.ml.algorithms

Contains all the models to be used in a high level: regresssion, classification, etc.

## org.learning.ml.algorithms.impl

Contains the actual implementations for the interfaces (for example, simple linear regression)

## org.learning.ml.dtos

All the dtos to be used within the problems so that it looks familiar to an existing system

## org.learning.ml.tests

Contains all the tests for training or using previously trained models. Generally speaking, it:

* reads a CSV
* transforms the CSV data into a collection of Dtos
* starts a model
* trains the model / loads a previously trained model
* evaluates a few cases (existing and non-existing)

## java resources

The resources folder contain all the datasets used for this
