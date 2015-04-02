# Overview #

## **class loadDb** ##

class **loadDb**

The **loadDb** class loads the database from a text file and can save any changes back to the original text file.

### Data Members ###

None.

### Constructor Summary ###

None.

### Method Summary ###

static boolean **load**(String _fileName_) - Reads in the text file _fileName_ and then creates matching tables and populates values in the database.

static boolean **saveDatabase**(String _fileName_) - Writes out any changes made via the **Interface** to the original file _fileName_.