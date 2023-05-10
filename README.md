# 51Degrees Geo-Location Engines

![51Degrees](https://51degrees.com/DesktopModules/FiftyOne/Distributor/Logo.ashx?utm_source=github&utm_medium=repository&utm_content=readme_main&utm_campaign=java-open-source "Data rewards the curious") **Pipeline API**

[Developer Documentation](https://51degrees.com/location-java/index.html?utm_source=github&utm_medium=repository&utm_content=documentation&utm_campaign=java-open-source "developer documentation")

## Introduction
 
This repository contains the geo-location engines for the Java implementation of the Pipeline API.

## Dependencies

The [tested versions](https://51degrees.com/documentation/_info__tested_versions.html) page shows 
the JDK versions that we currently test against. The software may run fine against other versions, 
but additional caution should be applied.

### Data

You will require a [resource key](https://51degrees.com/documentation/_info__resource_keys.html)
to use the Cloud API. You can create resource keys using our 
[configurator](https://configure.51degrees.com/), see our 
[documentation](https://51degrees.com/documentation/_concepts__configurator.html) on how to use this.

## Installation

You can either reference the projects in this repository or you can reference the [Maven][maven] packages in your project:

### Maven

The 51Degrees Java Geo Location package is available on maven. Make sure to select
the [latest version](https://mvnrepository.com/artifact/com.51degrees/geo-location).

```xml
<!-- Make sure to select the latest version from https://mvnrepository.com/artifact/com.51degrees/pipeline.geo-location -->
<dependency>
    <groupId>com.51degrees</groupId>
    <artifactId>pipeline.geo-location</artifactId>
    <version>4.3.9</version>
    <type>pom</type>
</dependency>
```

## Packages

- **pipeline.geo-location** - Geo-location engines and related projects.
- **geo-location.core** - Shared classes used by the geo-location engines.
- **geo-location.cloud** - A Java engine which retrieves geo-location results by consuming data from the 51Degrees cloud service.
- **geo-location.digital-element** - A Java engine which retrieves geo-location results by consuming data from a DigitalElement endpoint.
- **geo-location.nominatim** - A Java engine which retrieves geo-location results by consuming data from a Nominatim endpoint.
- **geo-location** - Contains the geo-location engine builders.

The following examples are "no longer" distributed as maven jars or installed by the default build, and need to be built by you:

- **geo-location.shell.examples** - Geo location examples to be run from the command line.
- **geo-location.web.examples** - Geo location examples to be run via Tomcat, servlets etc.

## Tests

Tests can be run in the project source by calling `mvn test`

## Examples

Examples can be found in the `geo-location.shell.examples` and `geo-location.web.examples` folders. 
See below for a list of the examples available.

|Example           |Description|
|------------------|-----------|
|GettingStarted    |This example uses the 51Degrees cloud to determine the country from a longitude and latitude.|
|CombiningServices |This example uses geo-location alongside device detection to determine the country and device.|
|Servlet           |Demonstrates a simple servlet-based website that uses latitude and longitude gathered from the client device to perform a location lookup.|
|MVC               |Demonstrates a simple mvc-based website that uses latitude and longitude gathered from the client device to perform a location lookup.|

## Project documentation

For complete documentation on the Pipeline API and associated engines, see the [51Degrees documentation site][Documentation].

[Documentation]: https://51degrees.com/documentation/index.html
[maven]: https://search.maven.org/artifact/com.51degrees/geo-location

