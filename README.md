JavaMortar
==========
version: 0.1.0

Utility classes that don't fit into other JCollectionExtensions, JCollectionFiller, JPrimitiveCollection, JTextFluff, JFunc, JStreamish, JTextParser, etc. 
Includes DateTime and TimeUnit helpers, common Enum operations, some simple hash generators for java.security.MessageDigest, and a few other minor helpers.

Packages:
####dataUtils/
catch-all bucket for remaining classes
  * DateTimeConverter - static date time parsing and formatting methods for common date formats
  * EnumUtil - for common enum operations, such as throwing unknown enum value or enum name errors
  * ParallelWork - static methods to execute a task in parallel on chunks of a data set
  * RunTaskInterval - a Runnable that can be called as frequently as required, but only runs when enough time has elapsed since the last run to match the required frequency
  * TimeUnitUtil - convert between time units using 'double', instead of 'long' like java.util.concurrent.TimeUnit uses

####hash/
methods for creating cryptographic hash values for byte arrays, strings, etc. using common hash algorithms

####numeric/
numeric functions used by previous projects, includes:
 * RollingAverage - static methods for calculating weighted averages and adding values to the existing averages

####tests/
JUnit tests and example code
