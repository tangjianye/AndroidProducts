#!/bin/sh

echo "===========发布到蒲公英==========="
curl -F "file=@${WORKSPACE}/platform/build/outputs/apk/aorise-petition-release.apk" \
    -F "uKey=a04fb657256887f1f7a28f09bad93856" \
    -F "_api_key=f8b02896cfce9e52814172a6d8f267e7" \
    http://www.pgyer.com/apiv1/app/upload
echo "\n\n"