@echo off

echo ======================Execute sonarqube======================
call gradle sonarqube
echo;

echo ======================Compile app / upload to jcenter======================
call gradle clean build bintrayUpload -PbintrayUser=aorise -PbintrayKey=219c9dafc270156a778386fef141654c0e1a7a4e -PdryRun=false
echo;

@echo off
echo ======================jenkins-build finish======================
echo;