# Cron Parser

A command line application to parse cron expressions and display the results in a table. It will, for example, take the following string:

```
*/15 0 1-21 * 1-5 /usr/bin/find
```

And spit out a table which breaks down the cron expression into its composite parts:

```
minute: 0 15 30 45
hour: 0
dayOfMonth: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
month: 1 2 3 4 5 6 7 8 9 10 11 12
dayOfWeek: 1 2 3 4 5
command: /usr/bin/find
```

## Getting Started
Install [homebrew](https://brew.sh/) first.

Install java, sbt and scala with brew.

```
brew cask install java 
brew install sbt
brew install scala
```

After cloning the project, start the sbt console in the root directory of the project.
Package the project with ```package```, copy the jar out of the target directory, make executable and run like this:

```
scala cronparser '*/15 0 1,5 * * /usr/bin/find'
```
Where cronparser is the name of the jar.

Alternatively, create alias in bash_profile:
 
 ```
 cron-parser() {
   scala ~/path/to/directory/cronparser "$1"
 }
 ```
 
 and run with ```cron-parser '*/15 0 1,5 * * /usr/bin/find'```