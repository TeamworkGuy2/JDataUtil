# Change Log
All notable changes to this project will be documented in this file.
This project does its best to adhere to [Semantic Versioning](http://semver.org/).


--------
###[0.4.0](N/A) - 2016-08-27
####Removed
* Removed twg2.dataUtil.dateTime package (moved to new JDateTimes project)


--------
###[0.3.0](https://github.com/TeamworkGuy2/JavaMortar/commit/428e34f2a4eb750062b78c73b32c563fa0b35d0a) - 2016-08-18
####Changed
* Moved DateTimeConverter and TimeUnitUtil to new twg2.dataUtil.dateTime package
* Renamed EnumUtil -> EnumError
* Switched versions.md format to CHANGELOG.md, see http://keepachangelog.com/


--------
###[0.2.0](https://github.com/TeamworkGuy2/JavaMortar/commit/d75f06b8f0b776d2e3cdff77faee16ffffa16007) - 2016-02-24
####Changed
* Renamed packages to begin with 'twg2.dataUtil' prefix
* Moved twg2.dataUtil.test package to separate test directory


--------
###[0.1.0](https://github.com/TeamworkGuy2/JavaMortar/commit/6e6e7664fd4ce289d1508d914a36e1c174274136) - 2016-01-26
####Added
* Initial versioning of existing code, including date-time parsing/formatting, time unit conversion, running units of work in parallel, calculating hashes, etc.