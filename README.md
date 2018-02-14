# Project Radio
A small project to build out a data product based on track play information from the radio station 89.3 The Current (http://www.thecurrent.org).

## Requirements
You must have the following installed to run the project:
* Intellij IDEA CE 2017.3
* git
* Java 1.8

## Data
The data is located in the project at `data/playlist.csv`. The CSV file contains the following data:

| Column | Type | Description |
|--------|------|-------------|
|id| GUID | Unique identifier of the track play |
|datetime|Date/Time|The date and time the track was played|
|artist|String| The name of the artist|
|title|String|The name of the title of the track|
|year|Integer|The year the track was played|
|month|Integer|The month the track was played|
|day|Integer|The day of the month the track was played|
|day_of_week|String|The name of the day of the week the track was played|
|week|String|The week of the year the track was played|
|hour|String|The hour of the day the track was played|


## Problem 1: Find Popular Tracks
For the day March 20, 2014, find the most popular track played on that day.
To solve this problem you will need to:

1. Parse the CSV data contained within the `data/playlist.csv` file
1. Filter the rows in the file to March 20, 2014
1. Count the unique instances of an artist and title
1. Return the artist/title with the highest count




