# 51Degrees Geo-Location Engines

![51Degrees](https://51degrees.com/DesktopModules/FiftyOne/Distributor/Logo.ashx?utm_source=github&utm_medium=repository&utm_content=readme_main&utm_campaign=java-open-source "Data rewards the curious") **Pipeline API**

[Developer Documentation](https://docs.51degrees.com?utm_source=github&utm_medium=repository&utm_content=documentation&utm_campaign=java-open-source "developer documentation")

## Introduction

This repository contains the geo-location engines for the Java implementation of the Pipeline API.

## Pre-requesites

The Pipeline engines are written in Java and target Java 7.

## Packages
- **pipeline.geo-location** - Geo-location engines and related projects.
  - **geo-location.core** - Shared classes used by the geo-location engines.
  - **geo-location.cloud** - A Java engine which retrieves geo-location results by consuming data from the 51Degrees cloud service.
  - **geo-location.digital-element** - A Java engine which retrieves geo-location results by consuming data from a DigitalElement endpoint.
  - **geo-location.nominatim** - A Java engine which retrieves geo-location results by consuming data from a Nominatim endpoint.
  - **geo-location** - Contains the geo-location engine builders.
  - **geo-location.examples** - Contains examples of how to use the geo-location engines.
  
## Installation

You can either reference the projects in this repository or you can reference the [Maven][maven] packages in your project:

```
<dependency>
    <groupId>com.51degrees</groupId>
    <artifactId>geo-location</artifactId>
    <version>4.1.3</version>
</dependency>
```

Make sure to select the latest version from [Maven.][maven]

## Examples

Examples can be found in the `geo-location.examples/` folder, there are separate sources for cloud, DigitalElement and Nominatim implementations and solutions for Java. See below for a list of examples.

|Example|Description|Implemtation|
|-------|-----------|------------|
|GettingStarted|This example uses geo-location to determine the country from a longitude and latidude.|Cloud / DigitalElement / Nominatim|
|CombiningServices|This example uses geo-location alongside device detection to determine the country and device.|Cloud|

## Tests

Tests can be run in the project source by calling `mvn test`

## Project documentation

For complete documentation on the Pipeline API and associated engines, see the [51Degrees documentation site][Documentation].

[Documentation]: https://docs.51degrees.com
[maven]: https://search.maven.org/artifact/com.51degrees/geo-location

