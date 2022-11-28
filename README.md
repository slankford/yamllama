# yamllama
Specifically for converting unique MCMMO yaml objects to Zrips Jobs yaml objects

It adds the 3 jobs lines:
```
nether_gold_ore: 44
```
converts to
```
nether_gold_ore:  
  income: 1  
  points: 1  
  experience: 1
```

It also compares the MCMMO file (a custom file that only has the key/values you're comparing, not the whole experience.yaml) that you compare with the Jobs file to only put the unique ones (the ones that Jobs doesn't have) in a new file. 

# Build
Run a mvn clean install in the root directory to build.
```
mvn clean install
```

# Use
Place relavant files in the src/main/resources folder (follow the examples). Run the packaged jar with relevant classpath dependencies and 3 arguments:
```
java *classpath* -jar llama-1.jar MCMMOFileName JobsFileName DefaultBigDecimalJobsValue
```
- MCMMOFileName: String name of a file containing list of MCMMO key value pairs in yaml format (don't include .yaml)
- JobsFileName: String name of a file containing list of Jobs objects in yaml format  (don't include .yaml)
- DefaultBigDecimalJobsValue: BigDecimal of value to provide across all new Jobs objects

Two new yamls will be generated in the root directory. One contains a list of the MCMMO unique values and the other contains a list of them converted to Jobs objects.
