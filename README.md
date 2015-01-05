JavaMortar
==========

Boilerplate code commonly used in small Java applications. Includes array searching and aggregation, primitive/tri/quad Functions, string searching, splitting, and replacing, char conditions and ranges, and builders for maps, lists, etc...

Packages:

####annotation/
basic annotations that Java lacks, such as Immutable and NotNull

####arrayUtils/
array manipulation methods such as concat, indexOf, sum, average, min, max, etc.

####checks/
methods for assertions and checks for conformance of methods like equals(), hashCode(), etc...

####dataType/
data type enums and data type converters

####dataUtils/
builders for Map.Entry, Map, List, Set, etc. Also conversion of property-file style strings

####functionUtils/
functions that take a primitive and return a boolean, as well as Tri-Function and Consumer

####hash/
methods for creating cryptographic hash values for byte arrays, strings, etc. using common hash algorithms

####numeric/
specific numeric functions used by some previous projects

####ranges/
Classes for representing, searching, and manipulating integer and char ranges

####stringUtils/
string indexOf, comparison, replace, and transformation methods

####templates/
ANTLR StringTemplate templates for generating many of the ranges/, arrayUtils/, and stringUtils/ classes

####templates/generators/
Generator classes for generating many of the ranges/, arrayUtils/, and stringUtils/ classes using the templates in 'templates/'

####tests/
JUnit tests and example code
