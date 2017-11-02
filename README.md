# JWoWJPA [![Build Status](https://travis-ci.org/welle/JWoWJPA.svg?branch=master)](https://travis-ci.org/welle/JWoWJPA) [![Quality Gate](https://sonarcloud.io/api/badges/gate?key=JWoWJPA:JWoWJPA)](https://sonarcloud.io/dashboard/index/JWoWJPA:JWoWJPA) #

## Quick summary ##

## How to use it ##
### Maven config ###
Just add
```<repository>
	<id>welle-maven-repository</id>
	<url>https://github.com/welle/maven-repository/raw/master/JWoWJPA/</url>
</repository>```

in your repositories.

Then add 
```<dependency>
	<groupId>aka.jwowjpa</groupId>
	<artifactId>JWoWJPA</artifactId>
	<version>0.0.1</version>
</dependency>```

in your dependencies.

## Notes ##
Need the eclipse-external-annotations-m2e-plugin: 

p2 update site to install this from: http://www.lastnpe.org/eclipse-external-annotations-m2e-plugin-p2-site/ (The 404 is normal, just because there is no index.html; it will work in Eclipse.)