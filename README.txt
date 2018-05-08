Benjamin Yu and Alicia Hyland
CSE353 Spring 2018

# cse353proj

Name:

   program

Synopsis:

   Trains 3 models to predict the outcome of dota2 games.

To use:
   Run the Java program. User should follow the prompts to use either the perceptron or the linear regression learning model.
   The user should select whether to train the model or use the already-trained model to make a prediction on a new data set.
   Then the user should input the name (or path) of a data file.
   The program will output the hypothesis (a vector of weights) and the accuracy of the training, if applicable.
   To run the SVM model, run the “predict” class from the liblinear-java library with program arguments (dota2Test dota2Train.model output),
   where “dota2Test” is the test data configured for liblinear, “dota2Train.model” is the testing model and
   “output” stores the output from the “predict” class.
