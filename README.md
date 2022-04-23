# yamllama
Specifically for converting unique MCMMO yaml objects to Zrips Jobs yaml objects

It adds the 3 jobs lines.

nether_gold_ore: 44
to
nether_gold_ore:
  income: 1
  points: 1
  experience: 1 

It also compares the MCMMO file (a custom file that only has the key/values you're comparing, not the whole experience.yaml) that you compare with the Jobs file to only put the unique ones (the ones that Jobs doesn't have) in a new file. 
