#!/bin/sh

echo ======================Execute sonarqube======================
gradle sonarqube;
echo;

echo ======================Compile app / upload to jcenter======================
gradle clean build bintrayUpload -PbintrayUser=aorise -PbintrayKey=219c9dafc270156a778386fef141654c0e1a7a4e -PdryRun=false;
echo;

#echo ======================Move app to tomcat======================
#mkdir /tomcat-downloads/android-petition/;
#\cp -r -a ${WORKSPACE}/platform/build/outputs/apk/* /tomcat-downloads/android-petition
#echo;

#echo ======================upload to pgyer======================
#curl -F "file=@${WORKSPACE}/platform/build/outputs/apk/aorise-petition-release.apk" \
#    -F "uKey=a04fb657256887f1f7a28f09bad93856" \
#    -F "_api_key=f8b02896cfce9e52814172a6d8f267e7" \
#    http://www.pgyer.com/apiv1/app/upload
#echo;

echo ======================jenkins-build finish======================
echo;