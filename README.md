# JWoWJPA [![Build Status](https://travis-ci.org/welle/JWoWJPA.svg?branch=master)](https://travis-ci.org/welle/JWoWJPA) [![Quality Gate](https://sonarcloud.io/api/badges/gate?key=aka.jwowjpa:JWoWJPA)](https://sonarcloud.io/dashboard/index/aka.jwowjpa:JWoWJPA)

## Quick summary

## How to use it
### Maven config
Just add:

	<repository>
		<id>welle-maven-repository</id>
		<url>https://github.com/welle/maven-repository/raw/master/JWoWJPA/</url>
	</repository>

in your repositories.
Then add:

	<dependency>
		<groupId>aka.jwowjpa</groupId>
		<artifactId>JWoWJPA</artifactId>
		<version>0.0.1</version>
	</dependency>

in your dependencies.

### Version

Go to [my maven repository](https://github.com/welle/maven-repository/tree/master/aka/jwowjpa/JWoWJPA) to get the latest version.

## Notes
Need the eclipse-external-annotations-m2e-plugin: 

p2 update site to install this from: http://www.lastnpe.org/eclipse-external-annotations-m2e-plugin-p2-site/ (The 404 is normal, just because there is no index.html; it will work in Eclipse.)