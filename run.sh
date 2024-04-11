#!/bin/bash
mvn clean install -DskipTests=true
cd target
java -jar MyFootballClub-0.0.1-SNAPSHOT.war
